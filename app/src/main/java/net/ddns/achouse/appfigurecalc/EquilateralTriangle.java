package net.ddns.achouse.appfigurecalc;


import android.os.Parcel;
import android.os.Parcelable;

import static java.lang.Math.*;

public class EquilateralTriangle extends Figure {

    //(1)
    // dodatkowe POLA potrzebne do okreslenia stanu Kwadratu;
    //(2)
    //KONSTRUKTOR - sposob inicjalizowania obiektu
    public EquilateralTriangle(double linearDimension){
        // zawartosc konstruktora - operacje wykonane podczas tworzenia obiektu
        setLinearDimension(linearDimension);
        setArea(0);
        setPerimeter(0);
        setType("Equilateral Triangle");
    }

    // dodatkowe METODY  - kt�re nie by�y zdefiniowane w klasie bazowej (Figure), w tym setters i getters
    //(3)
    //definicje METOD zadeklarowanych w klasie bazowej jako abstrakcyjne
    @Override // ten znacznik moze byc pominiety; sluzy tylko do poinformownia ze ponizsza metoda jest metoda nadpisana
    public void calculateArea() {
        // zawartosc metody - operacje ktore trzeba wykonac aby wyznaczyc area kwadratu
        double area = (((getLinearDimension()*getLinearDimension())*sqrt(3))/4);
        setArea(area);
    }
    public void calculatePerimeter() {
        double perimeter = 3*getLinearDimension();
        setPerimeter(perimeter);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(getLinearDimension());
        dest.writeDouble(getArea());
        dest.writeDouble(getPerimeter());
        dest.writeString(getType());
    }

    public static final Parcelable.Creator<EquilateralTriangle> CREATOR = new Parcelable.Creator<EquilateralTriangle>() {
        public EquilateralTriangle createFromParcel (Parcel source) {
            return new EquilateralTriangle(source);
        }

        @Override
        public EquilateralTriangle[] newArray(int size) {
            return new EquilateralTriangle[size];
        }
    };

    public EquilateralTriangle(Parcel in) {
        linearDimension = in.readDouble();
        area = in.readDouble();
        perimeter = in.readDouble();
        type = in.readString();
    }
    // wszystkie metody abstrakcyjne klasy bazowej musza byc zdefiniowane w tej klasie
    //itd.
}
