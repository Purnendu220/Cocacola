package com.survey.fcm;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.survey.Constants;

/**
 * Created by Purnendu on 7/2/2017.
 */

public class FabricEventTracker {
    private static final FabricEventTracker ourInstance = new FabricEventTracker();

    public static FabricEventTracker getInstance() {
        return ourInstance;
    }

    private FabricEventTracker() {


    }

    public void sendServiceEvent(String eventname,String apiname,int size){
        Answers.getInstance().logCustom(new CustomEvent(eventname)
                .putCustomAttribute("Api Name", apiname)
                .putCustomAttribute("Length", size));
    }
    public void sendServiceEvent(String eventname,String apiname,String reason){
        Answers.getInstance().logCustom(new CustomEvent(eventname)
                .putCustomAttribute("Api Name", apiname)
                .putCustomAttribute("Reason", reason));
    }
    public void trackScreen(String ScreenName){
        Answers.getInstance().logCustom(new CustomEvent(ScreenName));
    }
    public void trackCustomEvents(String eventName){
        Answers.getInstance().logCustom(new CustomEvent(eventName));
    }
    public void trackFCMEvents(String fcmid){
        Answers.getInstance().logCustom(new CustomEvent(Constants.FABRIC_EVENTS.FCM_REGISTERED)

        .putCustomAttribute(Constants.FABRIC_EVENTS.FCM_ID,fcmid));
    }
}
