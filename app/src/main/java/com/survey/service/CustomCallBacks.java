package com.survey.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Project : Mobikasa Retrofit Lib
 * Author : Balwinder Singh Madaan
 * Creation Date : 26-feb-2016
 * Description :
 */
public abstract class CustomCallBacks<T> implements Callback<T> {
    Context context;
    ProgressDialog mProgressDialog;

    public CustomCallBacks(Context context, boolean showProgress) {
        this.context = context;
        if (showProgress) {
            setDialog();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        stopDialog();
        int errorCode=0;
        if(t instanceof UnknownHostException){
            Log.e("CALL ERROR","UnknownHostException");
        }else if(t instanceof IOException){
            Log.e("CALL ERROR","IOException");
        }else if(t instanceof ConnectTimeoutException){
            Log.e("CALL ERROR","ConnectTimeoutException");
        }
        this.onFailure(t);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            stopDialog();
        } catch (Exception e) {
           e.printStackTrace();
        }
        this.onSucess(call, response);
    }

    public abstract void onSucess(Call<T> call, Response<T> response);

    public abstract void onFailure(Throwable arg0);

    private void setDialog() {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading.......");
        mProgressDialog.show();

    }

    void stopDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {

            }
        }
    }
}