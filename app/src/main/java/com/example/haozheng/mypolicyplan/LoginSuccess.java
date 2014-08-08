package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginSuccess extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success_screen);
    }

    public void returnToLogin(View view) {
        Intent goingBack = new Intent(this,MyActivity.class);
        startActivity(goingBack);
    }
}
