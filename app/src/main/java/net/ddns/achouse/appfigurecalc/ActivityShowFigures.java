package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

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
        final Comparator<Figure> compareType = new Comparator<Figure>() {
            @Override
            public int compare(Figure f1, Figure f2) {
                return f1.getType().compareTo(f2.getType());
            }
        };
        final Comparator<Figure> compareLinearDimension = new Comparator<Figure>() {
            @Override
            public int compare(Figure f1, Figure f2) {
                return Double.compare(f1.getLinearDimension(), f2.getLinearDimension());
            }
        };
        final Comparator<Figure> compareArea = new Comparator<Figure>() {
            @Override
            public int compare(Figure f1, Figure f2) {
                return Double.compare(f1.getArea(), f2.getArea());
            }
        };
        final Comparator<Figure> comparePerimeter = new Comparator<Figure>() {
            @Override
            public int compare(Figure f1, Figure f2) {
                return Double.compare(f1.getPerimeter(), f2.getPerimeter());
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
        ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
        c0.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(figuresList, compareType);
                    c0.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(figuresList, Collections.reverseOrder(compareType));
                    c0.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
        c1.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(figuresList, compareLinearDimension);
                    c1.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(figuresList, Collections.reverseOrder(compareLinearDimension));
                    c1.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
        c2.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(figuresList, compareArea);
                    c2.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(figuresList, Collections.reverseOrder(compareArea));
                    c2.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c3 = (ImageButton) findViewById(R.id.c3sort);
        c3.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c3 = (ImageButton) findViewById(R.id.c3sort);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(figuresList, comparePerimeter);
                    c3.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(figuresList, Collections.reverseOrder(comparePerimeter));
                    c3.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        }
}
