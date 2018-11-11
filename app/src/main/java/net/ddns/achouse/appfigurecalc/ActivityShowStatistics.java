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

public class ActivityShowStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistics);
        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        final ArrayList figuresList = bundle.getParcelableArrayList("figuresData");
        final ListView lv = (ListView) findViewById(R.id.statistics_list);

        //comparators
        final Comparator<Statistic> compareType = new Comparator<Statistic>() {
            @Override
            public int compare(Statistic s1, Statistic s2) {
                return s1.getType().compareTo(s2.getType());
            }
        };
        final Comparator<Statistic> compareNumber = new Comparator<Statistic>() {
            @Override
            public int compare(Statistic s1, Statistic s2) {
                return Double.compare(s1.getNumber(), s2.getNumber());
            }
        };
        final Comparator<Statistic> compareAverageArea = new Comparator<Statistic>() {
            @Override
            public int compare(Statistic s1, Statistic s2) {
                return Double.compare(s1.getAverageArea(), s2.getAverageArea());
            }
        };
        final Comparator<Statistic> compareAveragePerimeter = new Comparator<Statistic>() {
            @Override
            public int compare(Statistic s1, Statistic s2) {
                return Double.compare(s1.getAveragePerimeter(), s2.getAveragePerimeter());
            }
        };

        // header
        LayoutInflater inflater = getLayoutInflater();
        View header = getLayoutInflater().inflate(R.layout.table_header_statistics, null);

        final ArrayList<Statistic> listStatistics = new ArrayList<Statistic>();

        listStatistics.add(new Statistic("Square", figuresList));
        listStatistics.add(new Statistic("Circle", figuresList));
        listStatistics.add(new Statistic("Equilateral Triangle", figuresList));


        lv.addHeaderView(header);
        final ListAdapter adap = new StatisticListAdapter(this, listStatistics);
        lv.setAdapter(adap);

        //sortowanie
        ImageButton c0 = (ImageButton) findViewById(R.id.c0sort_stats);
        c0.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c0 = (ImageButton) findViewById(R.id.c0sort_stats);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(listStatistics, compareType);
                    c0.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(listStatistics, Collections.reverseOrder(compareType));
                    c0.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((StatisticListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c1 = (ImageButton) findViewById(R.id.c1sort_stats);
        c1.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort_stats);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(listStatistics, compareNumber);
                    c1.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(listStatistics, Collections.reverseOrder(compareNumber));
                    c1.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((StatisticListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c2 = (ImageButton) findViewById(R.id.c2sort_stats);
        c2.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c2 = (ImageButton) findViewById(R.id.c2sort_stats);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(listStatistics, compareAverageArea);
                    c2.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(listStatistics, Collections.reverseOrder(compareAverageArea));
                    c2.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((StatisticListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c3 = (ImageButton) findViewById(R.id.c3sort_stats);
        c3.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c3 = (ImageButton) findViewById(R.id.c3sort_stats);
            public void onClick(View v) {
                if(asc) {
                    Collections.sort(listStatistics, compareAveragePerimeter);
                    c3.setImageResource(R.drawable.sort_up);
                    asc = false;
                } else {
                    Collections.sort(listStatistics, Collections.reverseOrder(compareAveragePerimeter));
                    c3.setImageResource(R.drawable.sort_down);
                    asc = true;
                }
                ((StatisticListAdapter) adap).notifyDataSetChanged();
            }
        });
    }
}
