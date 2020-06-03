package iths.robin.fifaapp.Ui.Match;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

public class AwaySpinnerClass implements AdapterView.OnItemSelectedListener {
    private int position;

    public int getPosition() {
        return position;
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
        Log.d("ConfigureMatchFragment", "onItemSelected: Away team" + position);
        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

