package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import iths.robin.fifaapp.R;

public class KickoffFragment extends Fragment implements View.OnClickListener{
    private String TAG = "KickoffFragment";
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kickoff_fragment, container, false);

        Button one = view.findViewById(R.id.configureMatchBtn);
        one.setOnClickListener(this);
        Button two = view.findViewById(R.id.creatTeamBtn);
        two.setOnClickListener(this);
        Button three = view.findViewById(R.id.editTeamBtn);
        three.setOnClickListener(this);
        Button four = view.findViewById(R.id.createLeugueBtn);
        four.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.configureMatchBtn:
                fragment = new ConfigureMatchFragment();
                break;
            case R.id.creatTeamBtn:
                fragment = new CreatTeamFragment();
                break;
            case R.id.editTeamBtn:
                fragment = new EditTeamNameFragment();
                break;
            case R.id.createLeugueBtn:
                fragment = new AddLeagueFragment();
            default:
                break;
        }
        requireFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }
}
