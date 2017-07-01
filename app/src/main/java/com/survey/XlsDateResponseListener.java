package com.survey;

import com.survey.service.AllDateResponse;
import com.survey.service.DateList;

/**
 * Created by Purnendu on 7/1/2017.
 */

public interface XlsDateResponseListener {
    void onSuccess(AllDateResponse list);
    void onFailure(String message);

}
