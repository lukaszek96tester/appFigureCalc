package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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

    public DrawerLayout mDrawerLayout;

    ArrayList<Figure> figuresList = new ArrayList<Figure>();
    ListAdapter adap;

    int min = 0;
    int max = 5;
    int numberOfFigures = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_figures);
        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        //figuresList = bundle.getParcelableArrayList("figuresData");
        if (savedInstanceState != null)
        {
            //tutaj jest przechowywana lista figur jest zapisywana w metodzie onSaveInstanceSave
            figuresList = savedInstanceState.getParcelableArrayList("listFigures");
        } else {
            generateFigures(numberOfFigures,min,max);
        }
        //generateFigures(5,1,5);
        final ListView lv = (ListView) findViewById(R.id.figures_list);

        onCreateDrawer();

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //tutaj jest zapisywana informacja o figurach w przypadku zamknięcia aktywności
        outState.putParcelableArrayList("listFigures", figuresList);
    }

    protected void onCreateDrawer() {
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
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        switch(menuItem.getItemId()) {
                            case R.id.nav_add_new_figure:
                                goToAddActivity();
                                break;
//TODO dokonczyc przejscia do pozostalych aktywnosci
//                            case R.id.nav_display_statistics:
//                                fragmentClass = CatalogFragment.class;
//                                break;
                            case R.id.nav_settings:
                                goToSettingsActivity();
                                break;
                        }
                        return false;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

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
            generateFigures(1, min, max);
        } else if(item.getTitle() == "DELETE ALL AND GENERATE") {
            figuresList.clear();
            generateFigures(numberOfFigures, min, max);

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
        for (int j = (figuresList.size() - 1); j >= (figuresList.size() - numberOfFigures); j--)
        {
            figuresList.get(j).calculatePerimeter();
            figuresList.get(j).calculateArea();
        }
    }

    protected int calculateNumberOfFigures(String figureType) {
        int sum = 0;

        for (int rows = 0; rows < figuresList.size(); rows++ ) {
            if(figuresList.get(rows).getType() == figureType) {
                sum++;
            }
        }
        return sum;
    }

    protected double calculateAverageArea(String figureType) {

        double averageArea = 0;
        double sum = 0;

        for (int rows = 0; rows < figuresList.size(); rows++ ) {
            if(figuresList.get(rows).getType() == figureType) {
                sum = sum + figuresList.get(rows).getArea();
            }
        }
        if(calculateNumberOfFigures(figureType) != 0) {
            averageArea = sum / calculateNumberOfFigures(figureType);
        }

        return averageArea;
    }

    protected double calculateAveragePerimeter(String figureType) {

        double averagePerimeter = 0;
        double sum = 0;

        for (int rows = 0; rows < figuresList.size(); rows++ ) {
            if(figuresList.get(rows).getType() == figureType) {
                sum = sum + figuresList.get(rows).getPerimeter();
            }
        }
        if(calculateNumberOfFigures(figureType) != 0) {
            averagePerimeter = sum / calculateNumberOfFigures(figureType);
        }
        return averagePerimeter;
    }

    public void goToAddActivity() {
        Intent Intent = new Intent(getBaseContext(),AddActivity.class);
        // Start AddActivity waiting for result
        startActivityForResult(Intent, 1);
    }

    public void goToSettingsActivity() {
        Intent Intent = new Intent(getBaseContext(),SettingsActivity.class);
        // Start AddActivity waiting for result
        startActivityForResult(Intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //odczytywanie zwróconcyh danych z aktywności wywołanych
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && null!=data) {
                //odczytywanie typu
                String type = data.getStringExtra("type");
                //odczytywanie wymiaru liniowego
                String linearDimension = data.getStringExtra("linearDimension");
                if (!TextUtils.isEmpty(linearDimension)) {
                    Double linearDimensionValue = Double.valueOf(linearDimension);
                    // przetwarzanie wymiaru liniowego i dodanie go do listy figur
                    switch (type) {
                        case "Circle": {
                            Circle figure = new Circle(linearDimensionValue);
                            figure.calculateArea();
                            figure.calculatePerimeter();
                            figuresList.add(figure);
                        }
                        break;
                        case "Equilateral Triangle": {
                            EquilateralTriangle figure = new EquilateralTriangle(linearDimensionValue);
                            figure.calculateArea();
                            figure.calculatePerimeter();
                            figuresList.add(figure);
                        }
                        break;
                        case "Square": {
                            Square figure = new Square(linearDimensionValue);
                            figure.calculateArea();
                            figure.calculatePerimeter();
                            figuresList.add(figure);
                        }
                        break;
                    }
                    //wypisywanie figur
                    for (int nr = 0; nr < figuresList.size(); nr = nr + 1) {
                        String typ = figuresList.get(nr).getType();
                        // odczyt wlasnosci figury z tablicy figur (tablica zawiera referencje do obiekt�w kt�rymi s� figury)
                        System.out.println("Type: " + typ);// wyswietlenie sformatowanego wiersza w terminalu
                        System.out.println("\t Linear Dimension: " + figuresList.get(nr).getLinearDimension() + "\t Area: " + figuresList.get(nr).getArea() + "\t Perimeter: " + figuresList.get(nr).getPerimeter());
                    }
                    ((FigureListAdapter) adap).notifyDataSetChanged();
                }
            }
            else {
                return;
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK && null!=data) {
                String resultNumberOfFigures = data.getStringExtra("numberOfFigures");
                String resultMin = data.getStringExtra("min");
                String resultMax = data.getStringExtra("max");
                if (!TextUtils.isEmpty(resultNumberOfFigures) && !TextUtils.isEmpty(resultMin) && !TextUtils.isEmpty(resultMax)) {
                    numberOfFigures = Integer.parseInt(resultNumberOfFigures);
                    min = Integer.parseInt(resultMin);
                    max = Integer.parseInt(resultMax);
                    figuresList.clear();
                    generateFigures(numberOfFigures, min, max);
                    ((FigureListAdapter) adap).notifyDataSetChanged();
                }
            }
            else {
                return;
            }
        }
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
