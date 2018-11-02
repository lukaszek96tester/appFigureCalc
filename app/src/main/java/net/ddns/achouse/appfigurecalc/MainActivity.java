package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;

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
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //tutaj jest zapisywana informacja o figurach w przypadku zamknięcia aktywności
        outState.putParcelableArrayList("listFigures", listFigures);
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

            figuresData[rows][1] = Double.toString(listFigures.get(rows).getLinearDimension());
            figuresData[rows][2] = Double.toString(listFigures.get(rows).getArea());
            figuresData[rows][3] = Double.toString(listFigures.get(rows).getPerimeter());
        }
        bundle.putSerializable("figuresData", figuresData);
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
