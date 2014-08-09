package Objects;

import android.content.Intent;

import com.example.haozheng.mypolicyplan.LoginSuccess;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import validator.Validator;

//Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");

public class User {
    private static User instance;
    private String email;
    private String password;
    private ParseUser user;

    private User(){
    }

    public static User getInstance(){
        if (instance == null){
            User instance = new User();
        }
        return instance;
    }

    public static void register(String userName, String psw, String em){
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
                if (e == null) {
                    System.out.println("Sign up success!Let the user use the app!");

                } else {
                    System.out.println("Sign up failed! check exception.");
                }
            }
        });
    }

    public static void logIn(String userName, String psw, final LoginCallBack lcb){
        Validator validator = new Validator();
        Boolean done = validator.verifyAll(userName, psw);
        if (!done) {
            System.out.println("Login failed");
            return;
        } else {
            ParseUser.logInInBackground(userName, psw, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser != null) {
                        System.out.println("login success!Let the user use the app!");
                        lcb.finish(true, null);
                    } else {
                        System.out.println("login failed! check exception.");
                        lcb.finish(false, e);
                    }
                }
            });
        }

    }
    public static void logOut(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
    }

    public static void changeUserName(String newName){
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            user.setUsername(newName);
            user.saveInBackground();
        }
    }
    public static void changeEmail(String newEmail){
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            user.setEmail(newEmail);
            user.saveInBackground();
        }
    }
    public static void resetPassword(String email) {
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

    public static String description(){
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            System.out.println(user.getUsername());
            return "the user's name is " + user.getUsername() + "\n"+
                    "the user's email is " + user.getEmail();
        } else {return "No current user!";}
    }

}
