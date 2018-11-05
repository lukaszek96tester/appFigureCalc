package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.text.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Figure> listFigures = new ArrayList<Figure>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //saved instance state w tym bundle savedInstanceState są przechowywane wiadomości w przypadku reinicjalizacji aktywności
        if (savedInstanceState != null)
        {
            //tutaj jest przechowywana lista figur jest zapisywana w metodzie onSaveInstanceSave
            listFigures = savedInstanceState.getParcelableArrayList("listFigures");
        } else {
            Random generator = new Random();
            int numberOfFigures = 6;
            int type;
            float linearDimension;
            // wypelnianie tablicy  referencjami do wygenerowanych obiektow figur (typee figury okresla wczesniej wylosowana liczba)
            for (int i = 0; i < numberOfFigures; i++)
            {
                // określanie typeu figury oraz wymiaru liniowego
                type = generator.nextInt(3);
                linearDimension = generator.nextFloat();
                switch(type)
                {
                    case 0:
                    {
                        listFigures.add(new Square(linearDimension));
                    }
                    break;
                    case 1:
                    {
                        listFigures.add(new Circle(linearDimension));
                    }
                    break;
                    case 2:
                    {
                        listFigures.add(new EquilateralTriangle(linearDimension));
                    }
                }
            }

            // Policz pola i obwody
            for (int j = 0; j < numberOfFigures; j++)
            {
                listFigures.get(j).calculatePerimeter();
                listFigures.get(j).calculateArea();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //tutaj jest zapisywana informacja o figurach w przypadku zamknięcia aktywności
        outState.putParcelableArrayList("listFigures", listFigures);
    }

    protected int calculateNumberOfFigures(String figureType) {
        int sum = 0;

        for (int rows = 0; rows < listFigures.size(); rows++ ) {
            if(listFigures.get(rows).getType() == figureType) {
                sum++;
            }
        }

        return sum;
    }

    protected double calculateAverageArea(String figureType) {

        double averageArea = 0;
        double sum = 0;

        for (int rows = 0; rows < listFigures.size(); rows++ ) {
            if(listFigures.get(rows).getType() == figureType) {
                sum = sum + listFigures.get(rows).getArea();
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

        for (int rows = 0; rows < listFigures.size(); rows++ ) {
            if(listFigures.get(rows).getType() == figureType) {
                sum = sum + listFigures.get(rows).getPerimeter();
            }
        }
        if(calculateNumberOfFigures(figureType) != 0) {
            averagePerimeter = sum / calculateNumberOfFigures(figureType);
        }

        return averagePerimeter;
    }

    public void goToAddActivity(View v) {
        Intent Intent = new Intent(getBaseContext(),AddActivity.class);
        // Start AddActivity waiting for result
        startActivityForResult(Intent, 1);
    }

    public void goToDisplayActivity(View v) {
        Intent intent = new Intent(this, DisplayActivity.class);
        Bundle bundle = new Bundle();
        String type = "";
        int numberOfFigures = listFigures.size();
        String [][] figuresData = new String[numberOfFigures][4];
        for (int rows = 0; rows < numberOfFigures; rows++)
        {
            type = listFigures.get(rows).getType();
            switch(type) {
                case "Square":
                    figuresData[rows][0] = "■";
                    break;
                case "Equilateral Triangle":
                    figuresData[rows][0] = "▲";
                    break;
                case "Circle":
                    figuresData[rows][0] = "●";
                    break;
            }

            //figuresData[rows][1] = Double.toString(listFigures.get(rows).getLinearDimension());
            //figuresData[rows][2] = Double.toString(listFigures.get(rows).getArea());
            //figuresData[rows][3] = Double.toString(listFigures.get(rows).getPerimeter());

            //FORMATOWANIE - OBCIECIE DO TRZECH MIEJSC PO PRZECINKU
            DecimalFormat df = new DecimalFormat("#.###");

            figuresData[rows][1] = df.format(listFigures.get(rows).getLinearDimension());
            figuresData[rows][2] = df.format(listFigures.get(rows).getArea());
            figuresData[rows][3] = df.format(listFigures.get(rows).getPerimeter());
        }

        bundle.putSerializable("figuresData", figuresData);
        bundle.putSerializable("figuresDataLength", Integer.toString(numberOfFigures));
        bundle.putSerializable("figuresDataAverageArea", Double.toString(calculateAverageArea("Square")));
        bundle.putSerializable("figuresDataAveragePerimeter", Double.toString(calculateAveragePerimeter("Square")));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToDisplayStatsActivity(View v) {
        Intent intent = new Intent(this, DisplayStatsActivity.class);
        Bundle bundle = new Bundle();
        String type = "";
        int numberOfFigures = listFigures.size();
        String [][] figuresStatsData = new String[3][4];

        String [] figuresTypes = {"Square", "Circle", "Equilateral Triangle"};

        for(int i = 0; i < figuresTypes.length; i++) {
            type = figuresTypes[i];
            switch(type) {
                case "Square":
                    figuresStatsData[i][0] = "■";
                    break;
                case "Equilateral Triangle":
                    figuresStatsData[i][0] = "▲";
                    break;
                case "Circle":
                    figuresStatsData[i][0] = "●";
                    break;
            };
            figuresStatsData[i][1] = Integer.toString(calculateNumberOfFigures(type));
            figuresStatsData[i][2] = Double.toString(calculateAverageArea(type));
            figuresStatsData[i][3] = Double.toString(calculateAveragePerimeter(type));
        }

        //figuresStatsData[0][0] = "■";
        //figuresStatsData[0][1] = Integer.toString(calculateNumberOfFigures("Square"));
        //figuresStatsData[0][2] = Double.toString(calculateAverageArea("Square"));
        //figuresStatsData[0][3] = Double.toString(calculateAveragePerimeter("Square"));


        bundle.putSerializable("figuresStatsData", figuresStatsData);

        intent.putExtras(bundle);
        startActivity(intent);
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
                            Figure figure = new Circle(linearDimensionValue);
                            figure.calculateArea();
                            figure.calculatePerimeter();
                            listFigures.add(figure);
                        }
                        break;
                        case "Equilateral Triangle": {
                            Figure figure = new EquilateralTriangle(linearDimensionValue);
                            figure.calculateArea();
                            figure.calculatePerimeter();
                            listFigures.add(figure);
                        }
                        break;
                        case "Square": {
                            Figure figure = new Square(linearDimensionValue);
                            figure.calculateArea();
                            figure.calculatePerimeter();
                            listFigures.add(figure);
                        }
                        break;
                    }
                    //wypisywanie figur
                    for (int nr = 0; nr < listFigures.size(); nr = nr + 1) {
                        String typ = listFigures.get(nr).getType();
                        // odczyt wlasnosci figury z tablicy figur (tablica zawiera referencje do obiekt�w kt�rymi s� figury)
                        System.out.println("Type: " + typ);// wyswietlenie sformatowanego wiersza w terminalu
                        System.out.println("\t Linear Dimension: " + listFigures.get(nr).getLinearDimension() + "\t Area: " + listFigures.get(nr).getArea() + "\t Perimeter: " + listFigures.get(nr).getPerimeter());
                    }
                }
            }
            else {
                return;
            }
        }
    }
}
