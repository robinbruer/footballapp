package iths.robin.fifaapp.Pages;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.Data.Models.TeamUserSorter;
import iths.robin.fifaapp.Data.Repositories.DatabaseRepository;
import iths.robin.fifaapp.R;

public class LeagueTableFragment extends Fragment {
    private TextView team, playedMatches, won, draw, lost, gf, ga, gd, points ;
    private String TAG = "LeagueTableFragment";
    private DatabaseRepository databaseRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseRepository = new DatabaseRepository();
        return inflater.inflate(R.layout.league_table_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupTable();
    }

    private void setupTable() {
        Tasks.whenAllSuccess(databaseRepository.setLeagueTable()).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> list) {
                TeamUserSorter teamUserSorter = new TeamUserSorter(databaseRepository.getTeamUserArrayList());
                ArrayList<TeamUser> sortedTeamUsers = teamUserSorter.getSortedTeamUserByPoints();

                for (TeamUser userTeam : sortedTeamUsers) {
                    TableLayout table = getView().findViewById(R.id.table);

                    TableRow row=new TableRow(getActivity());
                    //row.setLayoutParams(layoutParamsRow);

                    team = new TextView(getActivity());
                    LinearLayout.LayoutParams lp_l3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (LinearLayout.LayoutParams.WRAP_CONTENT));
                    team.setLayoutParams(lp_l3);
                    team.setText(userTeam.getTeamName());
                    team.setPadding(5,5,5,5);
                    team.setTextSize(20);
                    team.setTypeface(null, Typeface.BOLD);

                    // team.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    //team.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    //team.requestLayout();

                    playedMatches = new TextView(getActivity());
                    playedMatches.setText(String.valueOf(userTeam.getPlayed()));
                    playedMatches.setPadding(5,5,5,5);
                    playedMatches.setTextSize(20);

                    won = new TextView(getActivity());
                    won.setText(String.valueOf(userTeam.getWon()));
                    won.setTextSize(20);
                    won.setPadding(5,5,5,5);

                    draw = new TextView(getActivity());
                    draw.setText(String.valueOf(userTeam.getDrawn()));
                    draw.setTextSize(20);
                    draw.setPadding(5,5,5,5);

                    lost = new TextView(getActivity());
                    lost.setText(String.valueOf(userTeam.getLost()));
                    lost.setTextSize(20);
                    lost.setPadding(5,5,5,5);

                    gf = new TextView(getActivity());
                    gf.setText(String.valueOf(userTeam.getGf()));
                    gf.setTextSize(20);
                    gf.setPadding(5,5,5,5);

                    ga = new TextView(getActivity());
                    ga.setText(String.valueOf(userTeam.getGa()));
                    ga.setTextSize(20);
                    ga.setPadding(5,5,5,5);

                    gd = new TextView(getActivity());
                    gd.setText(String.valueOf(userTeam.getGd()));
                    gd.setTextSize(20);
                    gd.setPadding(5,5,5,5);

                    points = new TextView(getActivity());
                    points.setText(String.valueOf(userTeam.getPoints()));
                    points.setTextSize(20);
                    points.setPadding(5,5,5,5);
                    points.setTypeface(null, Typeface.BOLD);

                    row.addView(team, new TableRow.LayoutParams(0));
                    row.addView(playedMatches);
                    row.addView(won);
                    row.addView(draw);
                    row.addView(lost);
                    row.addView(gf);
                    row.addView(ga);
                    row.addView(gd);
                    row.addView(points);
                    table.addView(row);
                }
            }
        });
    }


}


