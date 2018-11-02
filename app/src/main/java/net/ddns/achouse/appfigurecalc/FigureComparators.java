package net.ddns.achouse.appfigurecalc;

import java.util.Comparator;

public class FigureComparators {

    private FigureComparators() {

    }

    public static Comparator<String[]> getFigureSymbolComparator() {
        return new FigureSymbolComparator();
    }

    public static Comparator<String[]> getFigureLinearDimensionComparator() {
        return new FigureLinearDimensionComparator();
    }

    public static Comparator<String[]> getFigureAreaComparator() {
        return new FigureAreaComparator();
    }

    public static Comparator<String[]> getFigurePerimeterComparator() {
        return new FigurePerimeterComparator();
    }

    private static class FigureSymbolComparator implements Comparator<String[]> {

        @Override
        public int compare(final String car1[], final String car2[]) {
            return car1[0].compareTo(car2[0]);
        }
    }


    private static class FigureLinearDimensionComparator implements Comparator<String[]> {


        @Override
        public int compare(final String car1[], final String car2[]) {
            double car1D = Double.parseDouble(car1[1]);
            double car2D = Double.parseDouble(car2[1]);
            if (car1D < car2D) return -1;
            if (car1D > car2D) return 1;
            return 0;
        }
    }


    private static class FigureAreaComparator implements Comparator<String[]> {

        @Override
        public int compare(final String car1[], final String car2[]) {
            double car1D = Double.parseDouble(car1[2]);
            double car2D = Double.parseDouble(car2[2]);
            if (car1D < car2D) return -1;
            if (car1D > car2D) return 1;
            return 0;
        }
    }


    private static class FigurePerimeterComparator implements Comparator<String[]> {

        @Override
        public int compare(final String car1[], final String car2[]) {
            double car1D = Double.parseDouble(car1[3]);
            double car2D = Double.parseDouble(car2[4]);
            if (car1D < car2D) return -1;
            if (car1D > car2D) return 1;
            return 0;
        }
    }
}
