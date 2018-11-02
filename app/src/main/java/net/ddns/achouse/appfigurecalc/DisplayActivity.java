package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public class DisplayActivity extends AppCompatActivity {
    // Tablica z nagłówkami do tabeli
    String[] tableHeaders = {"Type", "Linear Dimension" , "Area" , "Perimeter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //pobieranie intencji czyli tych informacji lub opisu czynności pomiędzy aktywościami
        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        String[][] tableFigures = (String[][]) bundle.getSerializable("figuresData");




        final SortableTableView<String[]> tb = (SortableTableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(4);



        TableColumnWeightModel columnModel = new TableColumnWeightModel(4);
        columnModel.setColumnWeight(0, 1);
        columnModel.setColumnWeight(1, 2);
        columnModel.setColumnWeight(2, 2);
        columnModel.setColumnWeight(3, 2);
        tb.setColumnModel(columnModel);


//                            //ROW STYLE
//                            int colorEvenRows = getResources().getColor(R.color.colorPrimary);
//                            int colorOddRows = getResources().getColor(R.color.accent_material_dark_1);
//                            tb.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));

        //ADAPTERS
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,tableHeaders));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableFigures));

        //COMPARATORS
        tb.setColumnComparator(0, FigureComparators.getFigureSymbolComparator());
        tb.setColumnComparator(1, FigureComparators.getFigureLinearDimensionComparator());
        tb.setColumnComparator(2, FigureComparators.getFigureAreaComparator());
        tb.setColumnComparator(3, FigureComparators.getFigurePerimeterComparator());
        //COLUMN WEIGHT
    }

    // do sortowania - nie działa
//    public static class elo implements Comparator<String> {
//        @Override
//        public int compare(final String value1, final String value2) {
////            if(Double.parseDouble(value1) < Double.parseDouble(value2)) return -1;
////            if(Double.parseDouble(value1) > Double.parseDouble(value2)) return 1;
////            return 0;
//            return value1.compareTo(value2);
//        }
//
//
////        public static Comparator<String> getCarProducerComparator() {
////            return new CarProducerComparator();
////        }
////        public static Comparator<String> getStringFigureComparator() {
////            return new FigureComparator();
////        }
//    }


//        public Comparator<String> thenComparingDouble(ToDoubleFunction<? super String> keyExtractor) {
//            return null;
//        }
}
