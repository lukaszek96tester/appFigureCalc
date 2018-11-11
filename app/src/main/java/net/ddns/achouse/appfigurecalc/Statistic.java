package net.ddns.achouse.appfigurecalc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Statistic implements Parcelable {
    protected int number;
    protected double averagePerimeter;
    protected double averageArea;
    protected String type;

    public double getNumber() {
        return number;
    }
    public double getAveragePerimeter() {
        return averagePerimeter;
    }
    public double getAverageArea() {
        return averageArea;
    }
    public String getType() { return type; }

    public void setNumber(int numberToSet) { number = numberToSet;}
    public void setAveragePerimeter(double averagePerimeterToSet) { averagePerimeter = averagePerimeterToSet;}
    public void setAverageArea(double averageAreaToSet) { averageArea = averageAreaToSet;}
    public void setType(String typeToSet) { type = typeToSet; }

    public Statistic(String type, ArrayList<Figure> listStatistics){
        setType(type);
        calculateNumber(type, listStatistics);
        calculateAverageArea(type, listStatistics);
        calculateAveragePerimeter(type, listStatistics);
    }

    public void calculateNumber(String figureType, ArrayList<Figure> listFigures) {

            int sum = 0;

            for (int rows = 0; rows < listFigures.size(); rows++ ) {
                System.out.println("gettype " + listFigures.get(rows).getType());
                System.out.println("figure type" + figureType);
                if(listFigures.get(rows).getType().equals(figureType)) {
                    System.out.println("wszedl " + sum);
                    sum++;
                }
            }
            System.out.println("suma " + sum);

        setNumber(sum);
    }
    public void calculateAverageArea(String figureType, ArrayList<Figure> listFigures) {

        double averageArea = 0;
        double sum = 0;

        for (int rows = 0; rows < listFigures.size(); rows++ ) {
            if(listFigures.get(rows).getType().equals(figureType)) {
                sum = sum + listFigures.get(rows).getArea();
            }
        }
        if(getNumber() != 0) {
            averageArea = sum / getNumber();
        }

        setAverageArea(averageArea);
    }
    public void calculateAveragePerimeter(String figureType, ArrayList<Figure> listFigures) {

        double averagePerimeter = 0;
        double sum = 0;

        for (int rows = 0; rows < listFigures.size(); rows++ ) {
            if(listFigures.get(rows).getType().equals(figureType)) {
                sum = sum + listFigures.get(rows).getPerimeter();
            }
        }
        if(getNumber() != 0) {
            averagePerimeter = sum / getNumber();
        }

        setAveragePerimeter(averagePerimeter);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(getNumber());
        dest.writeDouble(getAveragePerimeter());
        dest.writeDouble(getAverageArea());
        dest.writeString(getType());
    }

    public Statistic(Parcel in){
        in.readArray(Statistic.class.getClassLoader());
    }



    public static final Parcelable.Creator<Statistic> CREATOR = new Parcelable.Creator<Statistic>(){
        @Override
        public Statistic createFromParcel(Parcel source){
            return new Statistic(source);
        }

        @Override
        public Statistic[] newArray(int size){
            return new Statistic[size];
        }
    };
}
