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
        public int compare(final String figure1[], final String figure2[]) {
            return figure1[0].compareTo(figure2[0]);
        }
    }

    private static class FigureLinearDimensionComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figure1[], final String figure2[]) {
            double figure1D = Double.parseDouble(figure1[1]);
            double figure2D = Double.parseDouble(figure2[1]);
            if (figure1D < figure2D) return -1;
            if (figure1D > figure2D) return 1;
            return 0;
        }
    }

    private static class FigureAreaComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figure1[], final String figure2[]) {
            double figure1D = Double.parseDouble(figure1[2]);
            double figure2D = Double.parseDouble(figure2[2]);
            if (figure1D < figure2D) return -1;
            if (figure1D > figure2D) return 1;
            return 0;
        }
    }


    protected static class FigurePerimeterComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figure1[], final String figure2[]) {
            double figure1D = Double.parseDouble(figure1[3]);
            double figure2D = Double.parseDouble(figure2[3]);
            if (figure1D < figure2D) return -1;
            if (figure1D > figure2D) return 1;
            return 0;
        }
    }
}
