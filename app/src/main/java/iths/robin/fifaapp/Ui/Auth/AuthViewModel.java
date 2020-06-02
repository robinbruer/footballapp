package iths.robin.fifaapp.Ui.Auth;

import iths.robin.fifaapp.Data.Repositories.UserRepository;

public class AuthViewModel {

    private AuthListener authListener = null;

    private UserRepository userRepository;

    void login() {

        authListener.onStarted();


    }

}
