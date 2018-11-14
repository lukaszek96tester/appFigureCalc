package net.ddns.achouse.appfigurecalc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onButtonClicked(View v) {
        EditText editText_numberOfFigures = (EditText) findViewById(R.id.SA_numberOfFigures);
        EditText editText_min = (EditText) findViewById(R.id.SA_min);
        EditText editText_max = (EditText) findViewById(R.id.SA_max);
        if (editText_numberOfFigures.getText().toString().equals("") || editText_min.getText().toString().equals("") || editText_max.getText().toString().equals("")) {
            //piec ponizszych linijek to wyświetlenie po prostu tego komunikatu ten alert nazywa sie toastem
            Context context = getApplicationContext();
            CharSequence text = "Fill all settings fields!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        } else {
            double minValue = Double.valueOf(editText_min.getText().toString());
            double maxValue = Double.valueOf(editText_max.getText().toString());
            if (minValue >= maxValue) {
                Context context = getApplicationContext();
                CharSequence text = "min >= max !!!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }

            String numberOfFigures = editText_numberOfFigures.getText().toString();
            String min = editText_min.getText().toString();
            String max = editText_max.getText().toString();
            //tworzy intent czyli komunikat opis czynności które trzeba wykonać lub informację do wyświetlenia
            Intent intent = new Intent();
            intent.putExtra("numberOfFigures", numberOfFigures);
            intent.putExtra("max", max);
            intent.putExtra("min", min);
            //setResult ta metoda zwroci dane do aktywności która ja wywołała
            setResult(RESULT_OK, intent);
            System.out.println(numberOfFigures);
            System.out.println(min);
            System.out.println(max);
            finish();
        }
    }
}
