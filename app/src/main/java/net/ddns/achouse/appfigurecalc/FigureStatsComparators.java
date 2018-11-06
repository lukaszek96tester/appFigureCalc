package net.ddns.achouse.appfigurecalc;

import java.util.Comparator;

public class FigureStatsComparators {

    private FigureStatsComparators() {

    }

    public static Comparator<String[]> getFigureTypeComparator() {
        return new FigureStatsComparators.FigureTypeComparator();
    }

    public static Comparator<String[]> getFigureNumberComparator() {
        return new FigureStatsComparators.FigureNumberComparator();
    }

    public static Comparator<String[]> getFigureAverageAreaComparator() {
        return new FigureStatsComparators.FigureAverageAreaComparator();
    }

    public static Comparator<String[]> getFigureAveragePerimeterComparator() {
        return new FigureStatsComparators.FigureAveragePerimeterComparator();
    }

    private static class FigureTypeComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figureStat1[], final String figureStat2[]) {
            return figureStat1[0].compareTo(figureStat2[0]);
        }
    }

    private static class FigureNumberComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figureNumber1[], final String figureNumber2[]) {
            double figure1D = Double.parseDouble(figureNumber1[1]);
            double figure2D = Double.parseDouble(figureNumber2[1]);
            if (figure1D < figure2D) return -1;
            if (figure1D > figure2D) return 1;
            return 0;
        }
    }

    private static class FigureAverageAreaComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figureAverageArea1[], final String figureAverageArea2[]) {
            double figure1D = Double.parseDouble(figureAverageArea1[2]);
            double figure2D = Double.parseDouble(figureAverageArea2[2]);
            if (figure1D < figure2D) return -1;
            if (figure1D > figure2D) return 1;
            return 0;
        }
    }

    private static class FigureAveragePerimeterComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figureAveragePerimeter1[], final String figureAveragePerimeter2[]) {
            double figure1D = Double.parseDouble(figureAveragePerimeter1[3]);
            double figure2D = Double.parseDouble(figureAveragePerimeter2[3]);
            if (figure1D < figure2D) return -1;
            if (figure1D > figure2D) return 1;
            return 0;
        }
    }
}
