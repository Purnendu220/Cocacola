package com.survey;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.survey.service.AllDateResponse;
import com.survey.service.DateList;
import com.survey.service.Datum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetListOfDates extends AppCompatActivity implements XlsDateResponseListener {
    private RecyclerView mRVJobListing;
    private XlsPageTableDAO dao;
    private AllDateListAdapter mAdapter;
    private TextView text_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list_of_dates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRVJobListing = (RecyclerView) findViewById(R.id.jobListingRV);
        text_date=(TextView)findViewById(R.id.text_date);
        getData();
    }
    private void setJobListAdapter(List<DateList> list) {
        ArrayList<Long> mylist=getSortedList(list);

        if(mylist.size()>0){
            mRVJobListing.setVisibility(View.VISIBLE);
            text_date.setVisibility(View.GONE);
            mAdapter = new AllDateListAdapter(GetListOfDates.this, mylist,"");
           // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRVJobListing.setLayoutManager(new GridLayoutManager(this, 3));
            mRVJobListing.setAdapter(mAdapter);}
        else{
            mRVJobListing.setVisibility(View.GONE);
            text_date.setVisibility(View.VISIBLE);
            text_date.setText("No Data Available for ");
        }
    }

    private ArrayList<Long> getSortedList(List<DateList> list){
        List<DateList> list_remove = new ArrayList<DateList>();
        ArrayList<Long> mylist=new ArrayList<Long>();
        for (DateList list1:list) {
            Syso.error(list1.getDATEVAL());
            if(isEmpty(list1.getDATEVAL())){
                list_remove.add(list1);
            }
        }
        list.removeAll(list_remove);
        for (DateList list1:list) {
            mylist.add(Long.parseLong(list1.getDATEVAL()));
        }
        Collections.sort(mylist);
        Collections.reverse(mylist);
        return mylist;
    }
    private void getData(){

            if(checkInternet()) {
                RefrenceWrapper.getRefrenceWrapper(GetListOfDates.this).getmServiceCallHandler().getalldates(GetListOfDates.this, GetListOfDates.this);
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(Constants.CHECK_INTERNET)
                        .setCancelable(false)
                        .setPositiveButton(Constants.GO_TO_SETTINGS, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                try {
                                    setMobileDataEnabled(GetListOfDates.this,true);

                                }catch (Exception e){
                                    AlertUtils.getInstance().showToast(GetListOfDates.this,"Please Go To Setting To Turn on Internet");

                                }
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }

    }
    private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        try{
            final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final Class conmanClass = Class.forName(conman.getClass().getName());
            final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
            connectivityManagerField.setAccessible(true);
            final Object connectivityManager = connectivityManagerField.get(conman);
            final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(connectivityManager, enabled);

        }
        catch (Exception e){
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertUtils.getInstance().showToast(GetListOfDates.this,"Please Go To Setting To Turn on Internet");
                }
            });
        }


    }
    public boolean checkInternet() {
        try {
            if (NetworkUtils.isConnectingToInternet(GetListOfDates.this)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Syso.error(e);
            return false;
        }
    }

    @Override
    public void onSuccess(AllDateResponse list) {

        setJobListAdapter(list.getData());
    }
    private boolean isEmpty(String data){
        if(data.equalsIgnoreCase("0")||data.equalsIgnoreCase("")||data.equalsIgnoreCase("DATEVAL")){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
