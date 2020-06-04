package iths.robin.fifaapp.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;

import java.util.List;

import iths.robin.fifaapp.Data.Repositories.UserRepository;
import iths.robin.fifaapp.Ui.Auth.LoginActivity;
import iths.robin.fifaapp.R;

public class HomeFragment extends Fragment {

    private String TAG = "HomeFragment";
    private UserRepository userRepository = new UserRepository();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        view.findViewById(R.id.logoutbtn).setOnClickListener(this::logOut);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void logOut(View view){
        Tasks.whenAllSuccess(userRepository.logOut(getContext())).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> list) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
