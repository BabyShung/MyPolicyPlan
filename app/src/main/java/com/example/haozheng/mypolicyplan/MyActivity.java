package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import Objects.TestUser;
import Objects.User;
import validator.TestValidator;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //User.register("maggie","12345","173267624@qq.com");
        //TestUser tester = new TestUser();
        //tester.testRegister("Jack","12345","173@yahoo.com");
        //User.logIn("Jack", "12345");
        User.changeUserName("Jerry");

        //User.changeEmail("maggieyang829@gmail.com");
        //User.resetPassword("maggieyang829@gmail.com");
        User.description();
        
        /* test Validator:
        TestValidator tester = new TestValidator();
        tester.test("maggie23@gmail.com");
        tester.test("");
        tester.test("m@gg@gma.cn"); */

        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

//
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
}
