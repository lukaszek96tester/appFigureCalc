package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ActivityShowFigures extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    ArrayList<Figure> figuresList;
    ListAdapter adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_figures);
        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        figuresList = bundle.getParcelableArrayList("figuresData");
        final ListView lv = (ListView) findViewById(R.id.figures_list);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);


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
        adap = new FigureListAdapter(this, figuresList);
        lv.setAdapter(adap);
/*        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(ActivityShowFigures.this, Integer.toString(position) + " " +Long.toString(id), Toast.LENGTH_SHORT).show();
                //Figure figure = (Figure) lv.getItemAtPosition(position);
                //Toast.makeText(ActivityShowFigures.this, "Selected :" + " " + figure.getType() + ", "+ figure.getLinearDimension(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

        registerForContextMenu(lv);

        //sortowanie
        ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
        c0.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
            ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
            ImageButton c3 = (ImageButton) findViewById(R.id.c3sort);
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
                c1.setImageResource(R.drawable.sort_basic);
                c2.setImageResource(R.drawable.sort_basic);
                c3.setImageResource(R.drawable.sort_basic);
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
        c1.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
            ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
            ImageButton c3 = (ImageButton) findViewById(R.id.c3sort);
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
                c0.setImageResource(R.drawable.sort_basic);
                c2.setImageResource(R.drawable.sort_basic);
                c3.setImageResource(R.drawable.sort_basic);
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
        c2.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
            ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
            ImageButton c3 = (ImageButton) findViewById(R.id.c3sort);
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
                c0.setImageResource(R.drawable.sort_basic);
                c1.setImageResource(R.drawable.sort_basic);
                c3.setImageResource(R.drawable.sort_basic);
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        ImageButton c3 = (ImageButton) findViewById(R.id.c3sort);
        c3.setOnClickListener(new View.OnClickListener() {
            boolean asc = true;
            ImageButton c0 = (ImageButton) findViewById(R.id.c0sort);
            ImageButton c1 = (ImageButton) findViewById(R.id.c1sort);
            ImageButton c2 = (ImageButton) findViewById(R.id.c2sort);
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
                c0.setImageResource(R.drawable.sort_basic);
                c1.setImageResource(R.drawable.sort_basic);
                c2.setImageResource(R.drawable.sort_basic);
                ((FigureListAdapter) adap).notifyDataSetChanged();
            }
        });
        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Options");
        menu.add(0, v.getId(), 0, "DELETE");
        menu.add(0, v.getId(), 0, "DUPLICATE");
        menu.add(0, v.getId(), 0, "DELETE ALL AND GENERATE");
        menu.add(0, v.getId(), 0, "ADD RANDOM");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int activeListPosition = info.position - 1;
        Toast.makeText(ActivityShowFigures.this, Integer.toString(activeListPosition), Toast.LENGTH_SHORT).show();
        //list.get(listPosition).getTitle();//list item title
        if(item.getTitle() == "DUPLICATE") {
            switch (figuresList.get(activeListPosition).getType()) {
                case "Square":
                    Square square = new Square(figuresList.get(activeListPosition).getLinearDimension());
                    square.calculateArea();
                    square.calculatePerimeter();
                    figuresList.add(square);
                    break;
                case "Equilateral Triangle":
                    EquilateralTriangle triangle = new EquilateralTriangle(figuresList.get(activeListPosition).getLinearDimension());
                    triangle.calculateArea();
                    triangle.calculatePerimeter();
                    figuresList.add(triangle);
                    break;
                case "Circle":
                    Circle circle = new Circle(figuresList.get(activeListPosition).getLinearDimension());
                    circle.calculateArea();
                    circle.calculatePerimeter();
                    figuresList.add(circle);
                    break;
            }
        } else if(item.getTitle() == "DELETE") {
            figuresList.remove(activeListPosition);
        } else if(item.getTitle() == "ADD RANDOM") {
            figuresList.add(generateFigure());
        } else if(item.getTitle() == "DELETE ALL AND GENERATE") {
            figuresList.clear();
            generateFigures(4, 0, 5);

        }
        ((FigureListAdapter) adap).notifyDataSetChanged();
        return true;
    }

    //TODO generowanie figur (tutaj podobnie jak w Main Activity)
    private void generateFigures(int numberOfFigures, float min, float max) {
        Random generator = new Random();

        int type;
        float linearDimension;
        for (int i = 0; i < numberOfFigures; i++)
        {
            type = generator.nextInt(3);
            linearDimension = min + generator.nextFloat() * (max - min);
            switch(type)
            {
                case 0:
                {
                    figuresList.add(new Square(linearDimension));
                }
                break;
                case 1:
                {
                    figuresList.add(new Circle(linearDimension));
                }
                break;
                case 2:
                {
                    figuresList.add(new EquilateralTriangle(linearDimension));
                }
            }
        }

        // Policz pola i obwody
        for (int j = 0; j < numberOfFigures; j++)
        {
            figuresList.get(j).calculatePerimeter();
            figuresList.get(j).calculateArea();
        }
    }


    public Figure generateFigure() {

        Random generator = new Random();
        int min = 0;
        int max = 5;
        int type;
        float linearDimension;
        type = generator.nextInt(3);
        linearDimension = min + generator.nextFloat() * (max - min);

        Figure figure = new Square(linearDimension);

        switch(type)
        {
            case 0:
            {
                figure = new Square(linearDimension);
            }
            break;
            case 1:
            {
                figure = new Circle(linearDimension);
            }
            break;
            case 2:
            {
                figure = new EquilateralTriangle(linearDimension);
            }
        }

        figure.calculateArea();
        figure.calculatePerimeter();
        return figure;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
