package Objects;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import java.util.List;

import validator.Validator;

//Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");

public class User {
    private static User instance;
    private String email;
    private String password;
    private ParseUser user;
    static final String queryResult = "planQueryResult";

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
    public static void resetPassword(String email, final LoginCallBack cb) {
        String userEmail = email;
        ParseUser.requestPasswordResetInBackground(userEmail, new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    System.out.println("email is sent with reset instruction");
                    cb.finish(true,null);
                } else {
                    System.out.println("Reset password failed, check e");
                    cb.finish(false,e);
                }
            }
        });
    }

    public static void fetchPlan(final FetchPlanCallBack fcb) {

        ParseUser user = ParseUser.getCurrentUser();
        String userID = user.getObjectId();

        ParseQuery<ParseObject> innerQuery = ParseQuery.getQuery("UserPolicy");
        innerQuery.whereEqualTo("UserObjectID", userID);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Policy");
        query.whereMatchesKeyInQuery("objectId","PolicyObjectID",innerQuery);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> policyObjects, ParseException e) {
                if (e == null) {
                    /*ParseObject.unpinAllInBackground(queryResult,policyObjects,
                            new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e!= null) {
                                        System.out.println("unpin query failed");
                                        return;
                                    } else {
                                        ParseObject.pinAllInBackground(queryResult, policyObjects);
                                        System.out.println("pin query success");
                                    }
                                }
                            });*/
                    //ParseObject.pinAllInBackground(queryResult, policyObjects);
                    fcb.finish(true, policyObjects, null);

                } else {
                    fcb.finish(false, null, e);
                    System.out.println("fetch plan failed with exception:  " + e.getMessage());
                }
            }
        });
    }

    public static Boolean hasCurrentUser() {
        ParseUser currUser = ParseUser.getCurrentUser();
        if (currUser != null) { return true;}

        return false;
    }

    @Override
    public String toString(){
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            System.out.println(user.getUsername());
            return "the user's name is " + user.getUsername() + "\n"+
                    "the user's email is " + user.getEmail();
        } else {return "No current user!";}
    }

}
