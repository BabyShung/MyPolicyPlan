package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Objects.FetchPlanCallBack;
import Objects.Plan;
import Objects.User;

public class LoginSuccess extends Activity {
    static ArrayList<Plan> myPlanList = new ArrayList<Plan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_plan_view);
        User.fetchPlan(new FetchPlanCallBack() {
            @Override
            public void finish(Boolean success, List<ParseObject> lst, ParseException ex) {
                if (success) {

                    for (ParseObject po : lst) {
                        myPlanList.add(new Plan(po));
                    }

                    String [] myPlanArray = convertToStringArray(myPlanList);

                    ListAdapter theAdapter = new ArrayAdapter<String>(
                            LoginSuccess.this,android.R.layout.simple_list_item_1, myPlanArray);

                    ListView theListView = (ListView) findViewById(R.id.myPlanListView);

                    theListView.setAdapter(theAdapter);
                }
                else {
                    System.out.println("fetch plan failed, the exception is " + ex.getMessage());
                }
            }
        });
    }

    public void returnToLogin(View view) {
        Intent goingBack = new Intent(this,MyActivity.class);
        startActivity(goingBack);
    }

    public String[] convertToStringArray(ArrayList<Plan> lst){
        ArrayList<String> myLst = new ArrayList<String>();
        for (Plan a : lst){
            myLst.add(a.toString());
        }
        return myLst.toArray(new String[myLst.size()]);
    }

}
