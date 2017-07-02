package com.survey.service;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;


import com.survey.AppPreference;
import com.survey.Constants;
import com.survey.DatabaseHelper;
import com.survey.DeviceUtils;
import com.survey.RefrenceWrapper;
import com.survey.XlsDateResponseListener;
import com.survey.XlsPageTableDAO;
import com.survey.fcm.FabricEventTracker;
import com.survey.fcm.FcmResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cpu505 on 17/9/15.
 */
public class ServiceCallsUtils {
    private RefrenceWrapper refrenceWrapper;
    private XlsPageTableDAO dao;

    public void getalldata(final Context mContext, final String datevalue,final XlsResponse responseListener) {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);
        dao=new XlsPageTableDAO(DatabaseHelper.getDatabase());

        Call<XlsData> userSignUpCall = refrenceWrapper.getService().getallDetail(datevalue);
        userSignUpCall.enqueue(new CustomCallBacks<XlsData>(mContext, true) {
            @Override
            public void onSucess(Call<XlsData> call, Response<XlsData> response) {
                Log.e("TAG","Service---Success->"+response.body().getMsg());
                Log.e("TAG","Service---Success->"+response.body().getData().size());
                if(!response.body().getError()){
                    dao.insertBulk(response.body().getData());
                    responseListener.onSuccess(response.body());
                }
                else{
                    responseListener.onFailure(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
                Log.e("TAG","Service---failure->");
                arg0.printStackTrace();
                responseListener.onFailure(" Try after some time.");
            }
        });

    }
    public void getalldates(final Context mContext,final XlsDateResponseListener responseListener) {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);

        Call<AllDateResponse> userSignUpCall = refrenceWrapper.getService().getallDates("23 05 2017");
        userSignUpCall.enqueue(new CustomCallBacks<AllDateResponse>(mContext, true) {
            @Override
            public void onSucess(Call<AllDateResponse> call, Response<AllDateResponse> response) {
                Log.e("TAG","Service---Success->"+response.body().getMsg());
                Log.e("TAG","Service---Success->"+response.body().getData().size());
                if(!response.body().getError()){
                    responseListener.onSuccess(response.body());
                }
                else{
                    responseListener.onFailure(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
                Log.e("TAG","Service---failure->");
                arg0.printStackTrace();
                responseListener.onFailure(" Try after some time.");
            }
        });

    }

    public void savefcm(final Context mContext,final String fcm_id) {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);

        Call<FcmResponse> userSignUpCall = refrenceWrapper.getService().savefcmid(fcm_id, DeviceUtils.getDeviceID(mContext),DeviceUtils.getDeviceName());
        userSignUpCall.enqueue(new CustomCallBacks<FcmResponse>(mContext, false) {
            @Override
            public void onSucess(Call<FcmResponse> call, Response<FcmResponse> response) {
                Log.e("TAG","Service---Success->"+response.body().getMsg());

                if(!response.body().getError()){
                    AppPreference.getInstance().setTokenValue(fcm_id);
                    FabricEventTracker.getInstance().trackFCMEvents(response.body().getUser().getId()+"");
                }

            }

            @Override
            public void onFailure(Throwable arg0) {
                Log.e("TAG","Service---failure->");
                arg0.printStackTrace();
            }
        });

    }


}
