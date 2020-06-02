package iths.robin.fifaapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import iths.robin.fifaapp.Pages.ConfigureMatchFragment;
import iths.robin.fifaapp.R;

public class HelperClass {


    public static int getinput(View view, int displayId){
        EditText teamResult = view.findViewById(displayId);
        return Integer.parseInt(teamResult.getText().toString());
    }

    public static String getinputToString(View view, int displayId){
        EditText teamResult = view.findViewById(displayId);
        return teamResult.getText().toString();
    }

    public static String setTexttoView(View view, String text, int displayId) {
        TextView team = view.findViewById(displayId);
        team.setText(text);
        return text;
    }



}
