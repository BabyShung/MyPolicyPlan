package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;

import Objects.AlertMessage;
import Objects.LoginCallBack;
import Objects.User;

public class LogInActivity extends Activity {

    private EditText userEmailET;
    private EditText userPswET;
    private Date deviceDate;
    private Boolean isSecondLogin;
    private Date userDateInDevice;
    private Date userDateOnServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApp app = (MyApp) getApplication();

        if (app.hasCurrent()){

            ParseUser currUser = ParseUser.getCurrentUser();
            userDateInDevice = currUser.getDate("checkDeviceDate");

            String currUserObjectId = currUser.getObjectId();
            System.out.println("date on device: "+userDateInDevice);

            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("objectId",currUserObjectId);
            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> parseUsers, ParseException e) {
                    if (e == null) {
                        userDateOnServer = parseUsers.get(0).getDate("checkDeviceDate");
                        System.out.println("date on server: " + userDateOnServer);
                        if (userDateInDevice.compareTo(userDateOnServer) == 0) {
                            isSecondLogin = true;
                            Intent goToPlan = new Intent(getBaseContext(), MyPolicyView.class);
                            goToPlan.putExtra("secondLoginInfo",true);
                            startActivity(goToPlan);
                            finish();
                        } else {isSecondLogin = false;}
                    } else {System.out.println("query user failed: "+ e.getMessage());}
                }
            });

        } else {

            setContentView(R.layout.activity_my);
            initiateComponents();
        }

    }

    private void initiateComponents(){
        userEmailET = (EditText)findViewById(R.id.user_email);
        userPswET = (EditText)findViewById(R.id.password);
        //....
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLoginClick(View view) {
        final String userEmail = String.valueOf(userEmailET.getText());
        String userPsw = String.valueOf(userPswET.getText());

        User.logIn(userEmail,userPsw,new LoginCallBack() {
            @Override
            public void finish(Boolean success, ParseException error) {
                if (success){
                    System.out.println("login111 OK");
                    ParseUser currUser = ParseUser.getCurrentUser();
                    currUser.put("checkDeviceDate", new Date());
                    currUser.saveInBackground();

                    Intent getLoginOKScreen = new Intent(getBaseContext(), MyPolicyView.class);
                    getLoginOKScreen.putExtra("secondLoginInfo",false);

                    startActivity(getLoginOKScreen);
                    LogInActivity.this.finish();
                } else {
                    AlertMessage.showAlertMessage("Login Error",
                            "User name or password incorrect.",
                            LogInActivity.this);
                }
            }
        });

    }

    //encapsulate this method into another related class (maybe called GeneralControl.java,
    // its methods should be static

    /*private void showAlertMessage(String alertTitle, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)this);
        builder.setTitle(alertTitle)
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }*/

    public void onClickForgetPsw(View view) {
        Intent getEmailScreen = new Intent(getBaseContext(),GetUserEmail.class);
        startActivity(getEmailScreen);
    }

}
