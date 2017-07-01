
package com.survey.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("LINE_ID")
    @Expose
    private String lINEID;
    @SerializedName("DATEVAL")
    @Expose
    private String dATEVAL;
    @SerializedName("ME_D")
    @Expose
    private String mED;
    @SerializedName("ME_M")
    @Expose
    private String mEM;
    @SerializedName("ME_Y")
    @Expose
    private String mEY;
    @SerializedName("PE_D")
    @Expose
    private String pED;
    @SerializedName("PE_M")
    @Expose
    private String pEM;
    @SerializedName("PE_Y")
    @Expose
    private String pEY;
    @SerializedName("PR_D")
    @Expose
    private String pRD;
    @SerializedName("PR_M")
    @Expose
    private String pRM;
    @SerializedName("PR_Y")
    @Expose
    private String pRY;
    @SerializedName("REMK")
    @Expose
    private String rEMK;
    @SerializedName("ITC")
    @Expose
    private String iTC;
    @SerializedName("USER_NAME")
    @Expose
    private String uSERNAME;
    @SerializedName("COMPUTER")
    @Expose
    private String cOMPUTER;
    @SerializedName("IPADD")
    @Expose
    private String iPADD;
    @SerializedName("LINE_NAME")
    @Expose
    private String lINENAME;
    @SerializedName("ITEM_NAME")
    @Expose
    private String iTEMNAME;
    @SerializedName("TARGET_ME")
    @Expose
    private String tARGETME;
    @SerializedName("TARGET_PE")
    @Expose
    private String tARGETPE;
    @SerializedName("TARGET_PR")
    @Expose
    private String tARGETPR;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLINEID() {
        return lINEID;
    }

    public void setLINEID(String lINEID) {
        this.lINEID = lINEID;
    }

    public String getDATEVAL() {
        return dATEVAL;
    }

    public void setDATEVAL(String dATEVAL) {
        this.dATEVAL = dATEVAL;
    }

    public String getMED() {
        return mED;
    }

    public void setMED(String mED) {
        this.mED = mED;
    }

    public String getMEM() {
        return mEM;
    }

    public void setMEM(String mEM) {
        this.mEM = mEM;
    }

    public String getMEY() {
        return mEY;
    }

    public void setMEY(String mEY) {
        this.mEY = mEY;
    }

    public String getPED() {
        return pED;
    }

    public void setPED(String pED) {
        this.pED = pED;
    }

    public String getPEM() {
        return pEM;
    }

    public void setPEM(String pEM) {
        this.pEM = pEM;
    }

    public String getPEY() {
        return pEY;
    }

    public void setPEY(String pEY) {
        this.pEY = pEY;
    }

    public String getPRD() {
        return pRD;
    }

    public void setPRD(String pRD) {
        this.pRD = pRD;
    }

    public String getPRM() {
        return pRM;
    }

    public void setPRM(String pRM) {
        this.pRM = pRM;
    }

    public String getPRY() {
        return pRY;
    }

    public void setPRY(String pRY) {
        this.pRY = pRY;
    }

    public String getREMK() {
        return rEMK;
    }

    public void setREMK(String rEMK) {
        this.rEMK = rEMK;
    }

    public String getITC() {
        return iTC;
    }

    public void setITC(String iTC) {
        this.iTC = iTC;
    }

    public String getUSERNAME() {
        return uSERNAME;
    }

    public void setUSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    public String getCOMPUTER() {
        return cOMPUTER;
    }

    public void setCOMPUTER(String cOMPUTER) {
        this.cOMPUTER = cOMPUTER;
    }

    public String getIPADD() {
        return iPADD;
    }

    public void setIPADD(String iPADD) {
        this.iPADD = iPADD;
    }

    public String getLINENAME() {
        return lINENAME;
    }

    public void setLINENAME(String lINENAME) {
        this.lINENAME = lINENAME;
    }

    public String getITEMNAME() {
        return iTEMNAME;
    }

    public void setITEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public String getTARGETME() {
        return tARGETME;
    }

    public void setTARGETME(String tARGETME) {
        this.tARGETME = tARGETME;
    }

    public String getTARGETPE() {
        return tARGETPE;
    }

    public void setTARGETPE(String tARGETPE) {
        this.tARGETPE = tARGETPE;
    }

    public String getTARGETPR() {
        return tARGETPR;
    }

    public void setTARGETPR(String tARGETPR) {
        this.tARGETPR = tARGETPR;
    }

}
