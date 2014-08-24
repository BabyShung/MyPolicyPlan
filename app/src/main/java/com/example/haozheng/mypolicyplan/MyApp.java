package com.example.haozheng.mypolicyplan;

import android.app.Application;

import com.parse.Parse;

import java.util.Date;

import Objects.User;

public class MyApp extends Application{
    private Boolean hasCurrentUser;

    private Date userDateOnServer;
    private Date userDateInDevice;

    @Override
    public void onCreate(){
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "StSG8dSOBAuifnwNj4nSh22ppKK6smU3Ayh8864t", "lYw5JetgqAcRM12QOURKjlguO0szzE52nBLP1Gdb");
        super.onCreate();

    }

    public Boolean hasCurrent(){
        return User.hasCurrentUser();

    }


}
