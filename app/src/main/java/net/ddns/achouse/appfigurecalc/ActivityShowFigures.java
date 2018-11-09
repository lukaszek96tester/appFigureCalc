package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityShowFigures extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_figures);
        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        final ArrayList figuresList = bundle.getParcelableArrayList("figuresData");
        final ListView lv = (ListView) findViewById(R.id.figures_list);


        //comparators
        final Comparator<Figure> compareByLinearDimension = new Comparator<Figure>() {
            @Override
            public int compare(Figure f1, Figure f2) {
                return Double.compare(f1.getLinearDimension(), f2.getLinearDimension());
            }
        };

        // header
        LayoutInflater inflater = getLayoutInflater();
        View header = getLayoutInflater().inflate(R.layout.table_header, null);

        lv.addHeaderView(header);
        final ListAdapter adap = new FigureListAdapter(this, figuresList);
        lv.setAdapter(adap);
/*        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(ActivityShowFigures.this, Integer.toString(position) + " " +Long.toString(id), Toast.LENGTH_SHORT).show();
                //Figure figure = (Figure) lv.getItemAtPosition(position);
                //Toast.makeText(ActivityShowFigures.this, "Selected :" + " " + figure.getType() + ", "+ figure.getLinearDimension(), Toast.LENGTH_SHORT).show();
            }
        });*/

        //sortowanie
        ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
        c1.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(figuresList, compareByLinearDimension);
                    c1.setImageResource(R.drawable.icons8_sort_up);
                    asc = false;
                } else {
                    Collections.sort(figuresList, Collections.reverseOrder(compareByLinearDimension));
                    c1.setImageResource(R.drawable.icons8_sort_down);
                    asc = true;
                }
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        }
/*
    private ArrayList getListData() {
        ArrayList<Figure> results = new ArrayList<>();
        Circle user1 = new Circle(4);
        user1.calculateArea();
        user1.calculatePerimeter();
        results.add(user1);
        Square user2 = new Square(3);
        user2.calculateArea();
        user2.calculatePerimeter();
        results.add(user2);
        return results;
    }*/
}
