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
            return figureNumber1[1].compareTo(figureNumber2[1]);
        }
    }

    private static class FigureAverageAreaComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figureAverageArea1[], final String figureAverageArea2[]) {
            return figureAverageArea1[2].compareTo(figureAverageArea2[2]);
        }
    }

    private static class FigureAveragePerimeterComparator implements Comparator<String[]> {

        @Override
        public int compare(final String figureAveragePerimeter1[], final String figureAveragePerimeter2[]) {
            return figureAveragePerimeter1[3].compareTo(figureAveragePerimeter2[3]);
        }
    }
}
