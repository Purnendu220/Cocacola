package com.survey.service;

/**
 * Created by Purnendu on 6/24/2017.
 */

public interface XlsResponse {
    public void onSuccess(XlsData data);
    public void onFailure(String Message);

}
