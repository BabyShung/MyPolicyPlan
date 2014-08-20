package com.example.haozheng.mypolicyplan;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.Date;

public class MyApp extends Application{
    private Boolean hasCurrentUser;

    private Date userDateOnServer;
    private Date userDateInDevice;

    @Override
    public void onCreate(){
        Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");
        super.onCreate();

        hasCurrent();
    }

    public Boolean hasCurrent(){
        ParseUser currUser = ParseUser.getCurrentUser();
        if (currUser != null) {
            hasCurrentUser = true;
        } else {
            hasCurrentUser = false;
        }
        return hasCurrentUser;
    }


}
