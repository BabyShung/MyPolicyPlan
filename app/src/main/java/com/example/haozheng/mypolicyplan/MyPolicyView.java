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
    private Date userDateOnServer;
    private Date userDateInDevice;
    private Boolean isSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*ParseUser currUser = ParseUser.getCurrentUser();

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
                    System.out.println("date on server: "+userDateOnServer);
                    if (userDateInDevice.compareTo(userDateOnServer)!=0){

                        Intent goToLogin = new Intent(getBaseContext(),LogInActivity.class);
                        startActivity(goToLogin);
                        finish();
                    } else {

                        setContentView(R.layout.my_plan_view);
                        User.fetchPlan(new FetchPlanCallBack() {
                            @Override
                            public void finish(Boolean success, List<ParseObject> lst, ParseException ex) {
                                if (success) {
                                    //ParseObject.pinAllInBackground(queryResult,lst);

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

                } else {System.out.println("fetch user failed: " + e.getMessage());}
            }
        });*/

        Intent activityCalled = getIntent();
        isSecond = activityCalled.getExtras().getBoolean("secondLoginInfo");
        System.out.println("issecond: "+ isSecond);

        setContentView(R.layout.my_plan_view);

        User.fetchPlan(new FetchPlanCallBack() {
            @Override
            public void finish(Boolean success, final List<ParseObject> lst, ParseException ex) {
                if (success) {
                    /*if (isSecond) {
                        ParseObject.unpinAllInBackground(queryResult,lst,
                                new DeleteCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e != null) {
                                            System.out.println("unpin failed: "+e.getMessage());
                                            return;
                                        } else {
                                            System.out.println("unpin success");
                                            ParseObject.pinAllInBackground(queryResult,lst);
                                        }
                                    }
                                });
                    } else {ParseObject.pinAllInBackground(queryResult,lst);}*/
                    //ParseObject.pinAllInBackground(queryResult,lst);

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
