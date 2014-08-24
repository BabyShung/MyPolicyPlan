package com.example.haozheng.mypolicyplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Objects.FetchPlanCallBack;
import Objects.Plan;
import Objects.User;

public class MyPolicyView extends Activity {
    private ArrayList<Plan> myPlanList = new ArrayList<Plan>();
    final String queryResult = "planQueryResult";

    private Boolean isSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activityCalled = getIntent();
        isSecond = activityCalled.getExtras().getBoolean("secondLoginInfo");

        setContentView(R.layout.my_plan_view);

        User.fetchPlan(new FetchPlanCallBack() {
            @Override
            public void finish(Boolean success, final List<ParseObject> lst, ParseException ex) {
                if (success) {
                    for (ParseObject po : lst) {
                        myPlanList.add(new Plan(po));
                    }

                    String [] myPlanArray = convertToStringArray(myPlanList);

                    ListAdapter theAdapter = new ArrayAdapter<String>(
                            MyPolicyView.this,android.R.layout.simple_list_item_1, myPlanArray);

                    ListView theListView = (ListView) findViewById(R.id.myPlanListView);

                    theListView.setAdapter(theAdapter);
                }
                else {
                    System.out.println("fetch plan failed, the exception is " + ex.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_LogOut) {
            ParseUser currUser = ParseUser.getCurrentUser();
            currUser.logOut();

            Intent goToLogin = new Intent(this, LogInActivity.class);
            startActivity(goToLogin);
            finish();
        }
        return true;
    }

    public void returnToLogin(View view) {
        Intent goingBack = new Intent(this,LogInActivity.class);
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
