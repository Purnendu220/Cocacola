package com.survey.fcm;

import android.app.IntentService;
import android.content.Intent;
import com.google.firebase.iid.FirebaseInstanceId;
import com.survey.AppPreference;
import com.survey.RefrenceWrapper;
import com.survey.Syso;


public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegistrationIntentService";
    private String token;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        FirebaseInstanceId instanceID = FirebaseInstanceId.getInstance();
        try {
            token = instanceID.getToken();
            RefrenceWrapper.getRefrenceWrapper(RegistrationIntentService.this).getmServiceCallHandler().savefcm(RegistrationIntentService.this, token);
            Syso.error(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}