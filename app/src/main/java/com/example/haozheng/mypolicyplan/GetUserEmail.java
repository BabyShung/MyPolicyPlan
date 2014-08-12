package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;

import Objects.AlertMessage;
import Objects.LoginCallBack;
import Objects.User;

public class GetUserEmail extends Activity {
    EditText inputEmailET;
    String inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_user_email_screen);
    }

    private void initiateComponents(){
        inputEmailET = (EditText) findViewById(R.id.userEmailInput);
    }

    public void goGetPsw(View view) {

        inputEmail = String.valueOf(inputEmailET.getText());
        User.resetPassword(inputEmail, new LoginCallBack() {
            @Override
            public void finish(Boolean success, ParseException error) {
                if (success) {
                    AlertMessage.showAlertMessage("Sent Success",
                            "Reset instruction has been sent to the email provided!",
                            GetUserEmail.this);
                } else {
                    AlertMessage.showAlertMessage("Reset Failed",
                            "The email provided doesn't exist in our data.",
                            GetUserEmail.this);
                }
            }
        });
    }
}