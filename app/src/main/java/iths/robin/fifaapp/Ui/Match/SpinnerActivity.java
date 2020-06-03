package iths.robin.fifaapp.Ui.Match;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import iths.robin.fifaapp.R;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        // Spinner Drop down elements
        List<String> listArray = new ArrayList<String>();
        listArray.add("Test1");
        listArray.add("Test2 ");
        listArray.add("Test3");



        Spinner spinner = (Spinner) findViewById(R.id.spinner); // Our element id we gave in the first step

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listArray); //simple_spinner_item denotes the layout when the drop down occurs


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Drop down layout style - list view with radio button


        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Default Methods
        //Perform some activity when the specific item is clicked
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}