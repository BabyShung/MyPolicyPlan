package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import Objects.TestUser;
import Objects.User;
import validator.TestValidator;
import validator.Validator;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //User.register("12345@today.cn","12345","12345@today.cn");
        //TestUser tester = new TestUser();
        //tester.testRegister("Jack","12345","173@yahoo.com");
        //User.logIn("Jack", "12345");
        //User.changeUserName("Jerry");
        //User.changeEmail("maggieyang829@gmail.com");
        //User.resetPassword("maggieyang829@gmail.com");
        //User.description();

        //Validator v = new Validator();
        //System.out.println(v.validateEmailRequire(""));
        //System.out.println(v.validatePswRequire(""));
        //v.verifyAll("maggi@yah.com","56");

        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();

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
        EditText userEmailET = (EditText)findViewById(R.id.user_email);
        EditText userPswET = (EditText)findViewById(R.id.password);
        String userEmail = String.valueOf(userEmailET.getText());
        String userPsw = String.valueOf(userPswET.getText());
        User.logIn(userEmail,userPsw);
    }
}
