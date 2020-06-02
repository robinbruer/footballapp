package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.HelperClass;

public class ConfigureMatchFragment extends Fragment {

    private String TAG = "ConfigureMatchFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configure_match_fragment, container, false);
        view.findViewById(R.id.startMatchBtn).setOnClickListener(this::startMatch);
        return view;
    }

    private void startMatch(View view){
        Log.d(TAG, "startMatch: ");
        Bundle bundle = new Bundle();
        bundle.putString("team1", HelperClass.getinputToString(view,R.id.team1));
        bundle.putString("team2",HelperClass.getinputToString(view, R.id.team2));
        Fragment fragment = new MatchFragment();
        fragment.setArguments(bundle);

        requireFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

}
