package com.survey.fcm;
import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyInstanceIdListenerService extends FirebaseInstanceIdService
{
    @Override
    public void onTokenRefresh()
    {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
