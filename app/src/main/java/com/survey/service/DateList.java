
package com.survey.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateList {

    @SerializedName("DATEVAL")
    @Expose
    private String dATEVAL;

    public String getDATEVAL() {
        return dATEVAL;
    }

    public void setDATEVAL(String dATEVAL) {
        this.dATEVAL = dATEVAL;
    }

}
