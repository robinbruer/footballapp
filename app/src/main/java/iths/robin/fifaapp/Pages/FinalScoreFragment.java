package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.Constants;
import iths.robin.fifaapp.Utils.HelperClass;

public class FinalScoreFragment extends Fragment {
    private String TAG = "MatchFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.final_score_fragment, container, false);
       view.findViewById(R.id.overviewBtn).setOnClickListener(this::goToOverview);
       String team1, team2, result1, result2;
       Bundle bundle = this.getArguments();
        if(bundle != null){
            team1 = bundle.getString(Constants.BUNDLE_KEY_TEAM1);
            team2 = bundle.getString(Constants.BUNDLE_KEY_TEAM2);
            result1 = bundle.getString(Constants.BUNDLE_KEY_RESULTAT1);
            result2 = bundle.getString(Constants.BUNDLE_KEY_RESULTAT2);
            HelperClass.setTexttoView(view,team1, R.id.displayTeam1Final);
            HelperClass.setTexttoView(view,team2, R.id.displayTeam2Final);
            HelperClass.setTexttoView(view,result1, R.id.finalResultsTeam1Match);
            HelperClass.setTexttoView(view,result2,R.id.finalResultsTeam2Match);
        }
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        bundle = this.getArguments();
        if(bundle != null){
            team1 = bundle.getString(Constants.BUNDLE_KEY_TEAM1);
            team2 = bundle.getString(Constants.BUNDLE_KEY_TEAM2);
            result1 = bundle.getString(Constants.BUNDLE_KEY_RESULTAT1);
            result2 = bundle.getString(Constants.BUNDLE_KEY_RESULTAT2);
            HelperClass.setTexttoView(getView(),team1, R.id.displayTeam1Final);
            HelperClass.setTexttoView(getView(),team2, R.id.displayTeam2Final);
            HelperClass.setTexttoView(getView(),result1, R.id.finalResultsTeam1Match);
            HelperClass.setTexttoView(getView(),result2,R.id.finalResultsTeam2Match);
        }


         */
    }

    private void goToOverview(View view){
        Fragment fragment = new LeagueTableFragment();
        requireFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


}