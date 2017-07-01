package com.survey;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Purnendu on 6/25/2017.
 */

public class XlsDataTable {

    public static final String NAME = "XlsDataTable";

    public interface XlsDataTableColumns extends BaseColumns {

         String lINEID="LINEID";
         String dATEVAL="dATEVAL";
         String mED="mED";
         String mEM="mEM";
         String mEY="mEY";
         String pED="pED";
         String pEM="pEM";
         String pEY="pEY";
         String pRD="pRD";
         String pRM="pRM";
         String pRY="pRY";
         String rEMK="rEMK";
         String iTC="iTC";
         String uSERNAME="uSERNAME";
         String cOMPUTER="cOMPUTER";
         String iPADD="iPADD";
         String lINENAME="lINENAME";
         String iTEMNAME="iTEMNAME";
         String tARGETME="tARGETME";
         String tARGETPE="tARGETPE";
         String tARGETPR="tARGETPR";
    }



    public static void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + XlsDataTable.NAME
                + '('
                + XlsDataTable.XlsDataTableColumns._ID
                + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + XlsDataTable.XlsDataTableColumns.lINEID
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.dATEVAL
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.mED
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.mEM
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.mEY
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.pED
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.pEM
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.pEY
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.pRD
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.pRM
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.rEMK
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.iTC
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.uSERNAME
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.cOMPUTER
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.iPADD
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.lINENAME
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.iTEMNAME
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.tARGETME
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.tARGETPE
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.tARGETPR
                + " TEXT, "
                + XlsDataTable.XlsDataTableColumns.pRY
                + " TEXT "+ ");");
    }

    public static void dropTable(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + XlsDataTable.NAME);
    }

}
