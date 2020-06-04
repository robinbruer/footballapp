package iths.robin.fifaapp.Database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.Task;


public interface UserDatabaseSource {

    void authProviders(Activity context);

    void onResult(int requestCode, int resultCode, Intent data);

    boolean checkAuthState();

    Task logOut(Context context);

    String getUserEmail();

    String getuserId();
}
