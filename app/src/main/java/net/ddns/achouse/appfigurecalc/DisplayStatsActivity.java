package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class DisplayStatsActivity extends AppCompatActivity {

    String[] tableHeaders = {"Type", "Number of figures" , "Average area" , "Average perimeter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_stats);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        String[][] tableFiguresStats = (String[][]) bundle.getSerializable("figuresStatsData");

        final SortableTableView<String[]> tb = (SortableTableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(4);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(4);
        columnModel.setColumnWeight(0, 1);
        columnModel.setColumnWeight(1, 2);
        columnModel.setColumnWeight(2, 2);
        columnModel.setColumnWeight(3, 2);
        tb.setColumnModel(columnModel);

        //ROW STYLE
        int colorEvenRows = getResources().getColor(R.color.colorPrimary);
        int colorOddRows = getResources().getColor(R.color.accent_material_dark_1);
        tb.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));

        //ADAPTERS
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,tableHeaders));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableFiguresStats));

        //COMPARATORS
        tb.setColumnComparator(0, FigureStatsComparators.getFigureTypeComparator());
        tb.setColumnComparator(1, FigureStatsComparators.getFigureNumberComparator());
        tb.setColumnComparator(2, FigureStatsComparators.getFigureAverageAreaComparator());
        tb.setColumnComparator(3, FigureStatsComparators.getFigureAveragePerimeterComparator());
    }
}
