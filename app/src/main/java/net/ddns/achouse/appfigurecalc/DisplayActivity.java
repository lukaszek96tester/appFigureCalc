package net.ddns.achouse.appfigurecalc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Toast;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class DisplayActivity extends AppCompatActivity {
    // Tablica z nagłówkami do tabeli
    String[] tableHeaders = {"Type", "Linear Dimension" , "Area" , "Perimeter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);



        //pobieranie intencji czyli tych informacji lub opisu czynności pomiędzy aktywościami
        Intent passed = getIntent();
        Bundle bundle = passed.getExtras();
        String[][] tableFigures = (String[][]) bundle.getSerializable("figuresData");

        final SortableTableView<String[]> tb = (SortableTableView<String[]>) findViewById(R.id.tableView);
        tb.addDataClickListener(new CarClickListener());
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
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableFigures));

        //COMPARATORS
        tb.setColumnComparator(0, FigureComparators.getFigureSymbolComparator());
        tb.setColumnComparator(1, FigureComparators.getFigureLinearDimensionComparator());
        tb.setColumnComparator(2, FigureComparators.getFigureAreaComparator());
        tb.setColumnComparator(3, FigureComparators.getFigurePerimeterComparator());

        //menu kontekstowe

        //registerForContextMenu(tb);
    }

    private class CarClickListener implements TableDataClickListener<String[]> {
        @Override
        public void onDataClicked(int rowIndex, String[] clickedCar) {
            //String clickedCarString = clickedCar.toString();
            System.out.print(rowIndex);
            //System.out.print(clickedCarString);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Options");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "New Random");
    }
}
