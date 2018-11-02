// klasa utworzona tylko dla jednej metody - main
// metoda ta moze byc umieszczona w innej klasie, nie ma koniecznosci tworzenia specjalnej klasy jak w tym przypadku 
// zostalo to zrobione dla zwiekszenia czytelnosci projektu

package net.ddns.achouse.appfigurecalc;


import java.util.*;


public class Program {
	public static void main(String[] args) {
	
		Random generator = new Random(); //obiekt generatora liczb pseudolosowych
		//losowanie  liczb pseudolosowych dla okreslenia typu figury i wymiaru liniowego 
		// wyniki umiescic w tablicy	

		List<Figure> listFigures = new ArrayList<Figure>();
		//listStrings.add("One");
		//System.out.println(listStrings);
		
		int numberOfFigures = 6;
		int type;
		float linearDimension;
		// wypelnianie tablicy  referencjami do wygenerowanych obiektow figur (type figury okresla wczesniej wylosowana liczba)
		for (int i = 0; i < numberOfFigures; i++)
		{
			// określanie typu figury oraz wymiaru liniowego
			type = generator.nextInt(3);
			linearDimension = generator.nextFloat();
			switch(type)
			{
				case 0:
				{
					listFigures.add(new Circle(linearDimension));
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

		//wypisywanie figur
		for(int nr = 0; nr < numberOfFigures; nr = nr + 1){
			String typeDisplay = listFigures.get(nr).getType();
			// odczyt wlasnosci figury z tablicy figur (tablica zawiera referencje do obiekt�w kt�rymi s� figury)
			System.out.println("Type: " + typeDisplay);// wyswietlenie sformatowanego wiersza w terminalu
			System.out.println("\t Linear Dimension: " + listFigures.get(nr).getLinearDimension() + "\t Area: " + listFigures.get(nr).getArea() + "\t Perimter: " + listFigures.get(nr).getPerimeter());
		}
	}
}
