package net.ddns.achouse.appfigurecalc;


import android.os.Parcel;
import android.os.Parcelable;

public abstract class Figure implements Parcelable {

	//(1)
	// POLA charakteryzujace wspolne wlasnosci wszystkich figur
	protected double linearDimension;
	protected double perimeter;
	protected double area;
	protected String type;
	
	//(2)
	// METODY definiujace dostep do pol klasy Figure tzw. getters i setters
	//np.
	public double getLinearDimension() {
		return linearDimension;
	}
	public double getPerimeter() {
		return perimeter;
	}
	public double getArea() {
		return area;
	}
	public String getType() { return type; }
	public void setLinearDimension(double linearDimensionToSet) { linearDimension = linearDimensionToSet; }
	public void setPerimeter(double perimeterToSet) { perimeter = perimeterToSet;}
	public void setArea(double areaToSet) { area = areaToSet;}
	public void setType(String typeToSet) { type = typeToSet; }

	// itd.
	//
	
	//(3)
	// deklaracje METOD ABSTRAKCYJNYCH (tylko nazwa i argumenty)
	// sa to metody, ktore sa wykorzystywane dla wszystkich typow figur, ale zaleznie od rodzaju figury nalezy zastosowac inna definicje metody
	// metody abstrakcyjne sa definiowane w klasach pochodnych (klasach dziedziczacych z klasy Figure)
	//np.
	public abstract void calculateArea();
	public abstract void calculatePerimeter();

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
	 // itd.
	 //
}
