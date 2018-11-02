package net.ddns.achouse.appfigurecalc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }



    public void onButtonClicked(View v) {
        //znajdywanie pola tekstowaego na wpisywanie rozmiaru
        EditText editText_dimension = (EditText) findViewById(R.id.AA_editText_dimension);
        //radio group czyli grupa tych trzeb buttonow
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        // Get the checked Radio Button ID from Radio Group
        int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();

        System.out.println(selectedRadioButtonID);
        //String editTextDimensionText = editText_dimension.getText().toString().trim();
        // If nothing is selected from Radio Group, then it return -1
        if (editText_dimension.getText().toString().equals("")) {
            //piec ponizszych linijek to wyświetlenie po prostu tego komunikatu ten alert nazywa sie toastem
            Context context = getApplicationContext();
            CharSequence text = "Choose type and linear dimension for the figure first";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        } else if (selectedRadioButtonID == -1) {
            // dialog "Nie wybrano typu figury"
            Context context = getApplicationContext();
            CharSequence text = "Choose type and linear dimension for the figure first";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
         } else {
            //pobiera selected radio button robi rzutowanie a wcześniej find view by id
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
            String selectedRadioButtonText = selectedRadioButton.getText().toString();
            String editTextDimensionText = editText_dimension.getText().toString();
            //tworzy intent czyli komunikat opis czynności które trzeba wykonać lub informację do wyświetlenia
            Intent intent = new Intent();
            intent.putExtra("type", selectedRadioButtonText); //dodaje jakąś informację o nazwie type czyli typ figury wybranej
            intent.putExtra("linearDimension", editTextDimensionText);// dodaje ten wymiar charakterystyczny
            //setResult ta metoda zwroci dane do aktywności która ja wywołała
            setResult(RESULT_OK, intent);
            System.out.println(selectedRadioButtonText);
            System.out.println(editText_dimension.getText());
            finish();
        }
    }
}
