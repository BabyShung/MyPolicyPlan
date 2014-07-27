package Objects;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

//Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");

public class User {
    private static User instance;
    private String email;
    private String password;

    private User(String email, String psw){
        this.email = email;
        this.password = psw;
    }

    public static User getInstance(String email, String psw){
        if (instance == null){
            User instance = new User(email, psw);
        }
        return instance;
    }

    private static void register(String userName, String psw, String confirmPsw, String em){
        /*we need to add logic to check the userName,
        psw matches confirmPsw, and turn on email verifying.*/

        /* this code is not used here
        Md5Hash myHash = new Md5Hash();
        String hashedEmail = myHash.hash(em);
        String hashedPsw = myHash.hash(psw);
        */

        ParseUser user = new ParseUser();
        user.setUsername(userName);
        user.setEmail(em);
        user.setPassword(psw);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    System.out.println("Sign up success!Let the user use the app!");

                }else {
                    System.out.println("Sign up failed! check exception.");
                }
            }
        });
    }

    private static void logIn(String em, String psw){
        ParseUser.logInInBackground(em, psw, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null){
                    System.out.println("login success!Let the user use the app!");
                } else {
                    System.out.println("login failed! check exception.");
                }
            }
        });
    }
    private void logOut(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
    }

    private void changeUserName(String newName){
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            user.setUsername(newName);
            user.saveInBackground();
        }
    }

    private void resetPassword(String email) {
        //we first asks the user to input the registered email
        String userEmail = email;

        //we need turn on the email verification on from the dashboard on Parse.com.
        ParseUser.requestPasswordResetInBackground(userEmail, new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    System.out.println("email is sent with reset instruction");
                } else {
                    System.out.println("something is wrong, check e");
                }
            }
        });
    }

    private void fetchPlan() {
    }

}
