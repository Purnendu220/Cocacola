package com.survey;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.survey.service.Datum;
import com.survey.service.XlsData;
import com.survey.service.XlsResponse;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,XlsResponse,DatePickerDialog.OnDateSetListener {

    private RecyclerView mRVJobListing;
    private TextView text_date;
    private JobListingAdapter mJobListAdapter;
    private Button mDateSelect;
    private String mSelectedDate="";
    private XlsPageTableDAO dao;
    private String str=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRVJobListing = (RecyclerView) findViewById(R.id.jobListingRV);
        text_date =(TextView) findViewById(R.id.text_date);
        dao=new XlsPageTableDAO(DatabaseHelper.getDatabase());
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            mSelectedDate=DateUtils.getCurrentDate();
            getData(DateUtils.getNumberOfDays(mSelectedDate)+"");

        } else {
            str= extras.getString("date");
            mSelectedDate=DateUtils.getdateFromnumberofDays(str);
            getData(str);


        }
    }
    private void setJobListAdapter(List<Datum> list) {
        if(list.size()>0){
            mRVJobListing.setVisibility(View.VISIBLE);
            text_date.setVisibility(View.GONE);
        mJobListAdapter = new JobListingAdapter(MainActivity.this, list,mSelectedDate);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRVJobListing.setLayoutManager(layoutManager);
        mRVJobListing.setAdapter(mJobListAdapter);}
        else{
            mRVJobListing.setVisibility(View.GONE);
            text_date.setVisibility(View.VISIBLE);
            text_date.setText("No Data Available for "+mSelectedDate);
        }
    }
    private void getData(String numberofdays){
       List<Datum> list= dao.getList(numberofdays);
        if(list.size()>0){
            setJobListAdapter(list);
        }
        else{
        if(checkInternet()) {
            RefrenceWrapper.getRefrenceWrapper(MainActivity.this).getmServiceCallHandler().getalldata(MainActivity.this, numberofdays, MainActivity.this);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(Constants.CHECK_INTERNET)
                    .setCancelable(false)
                    .setPositiveButton(Constants.GO_TO_SETTINGS, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            try {
                                setMobileDataEnabled(MainActivity.this,true);

                            }catch (Exception e){
                                AlertUtils.getInstance().showToast(MainActivity.this,"Please Go To Setting To Turn on Internet");

                            }
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.date_selected:
                selecteDate();
                break;*/

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
                  AlertUtils.getInstance().showToast(MainActivity.this,"Please Go To Setting To Turn on Internet");
              }
          });
      }


    }
private void selecteDate(){
    Calendar now = Calendar.getInstance();
    DatePickerDialog dpd = DatePickerDialog.newInstance(
            MainActivity.this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
    );
    dpd.show(getFragmentManager(), "Datepickerdialog");
}


    @Override
    public void onBackPressed() {
        if(str!=null){
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(Constants.ARE_YOU_SURE)
                    .setCancelable(false)
                    .setPositiveButton(Constants.YES, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //finish();
                            MainActivity.this.onSuperBackPressed();
                            //super.onBackPressed();
                        }
                    })
                    .setNegativeButton(Constants.NO, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
    public void onSuperBackPressed(){
        super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(str!=null){
           return false;

        }else{
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.date:
          selecteDate();
                break;
            case R.id.date_list:
                Intent i =new Intent(MainActivity.this,GetListOfDates.class);
                startActivity(i);
                break;
        }
        return true;

    }

    @Override
    public void onSuccess(XlsData data) {
        setJobListAdapter(data.getData());
    }

    @Override
    public void onFailure(String Message) {
AlertUtils.getInstance().showToast(MainActivity.this,Message);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mSelectedDate=dayOfMonth+" "+(monthOfYear+1)+" "+year;
        getData(DateUtils.getNumberOfDays(mSelectedDate)+"");
    }



    public void requestPermission(final Context context, final String permission, String mess) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, Constants.REQUEST_ENABLE);
            ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
        }
    }
    private void openContacts() {
        if (checkPermissionFor(Constants.READ_PHONE_STATE)) {
            requestPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE, "Allow Survey to " + "\n" + "access your phone?");
        }
    }
    public boolean checkPermissionFor(String permission) {
        if (permission.equalsIgnoreCase(Constants.READ_PHONE_STATE)) {
            return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 && checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED);
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == Constants.REQUEST_ENABLE) {


        }

    }

    public boolean checkInternet() {
        try {
            if (NetworkUtils.isConnectingToInternet(MainActivity.this)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Syso.error(e);
            return false;
        }
    }
}
