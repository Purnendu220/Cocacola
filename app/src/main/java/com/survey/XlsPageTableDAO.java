package com.survey;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.survey.service.Datum;
import com.survey.service.XlsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Purnendu on 6/30/2017.
 */

public class XlsPageTableDAO extends AbsDAO<Datum> {
    public XlsPageTableDAO(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public List<Datum> createList(Cursor cursor) {
        List<Datum> list = new ArrayList<Datum>();
        Syso.debug(TAG, "cursor size = " + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            list.add(createObject(cursor));
            cursor.moveToNext();
        }
        return list;
    }

    @Override
    public Datum createObject(Cursor cursor) {
        Datum data = new Datum();
        if (cursor != null) {
            data.setId(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns._ID)));
            data.setLINEID(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.lINEID)));
            data.setDATEVAL(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.dATEVAL)));
            data.setMED(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.mED)));
            data.setMEM(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.mEM)));
            data.setMEY(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.mEY)));
            data.setPED(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.pED)));
            data.setPEM(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.pEM)));
            data.setPEY(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.pEY)));
            data.setPRD(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.pRD)));
            data.setPRM(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.pRM)));
            data.setPRY(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.pRY)));
            data.setREMK(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.rEMK)));
            data.setITC(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.iTC)));
            data.setUSERNAME(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.uSERNAME)));
            data.setCOMPUTER(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.cOMPUTER)));
            data.setIPADD(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.iPADD)));
            data.setLINENAME(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.lINENAME)));
            data.setITEMNAME(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.iTEMNAME)));
            data.setTARGETME(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.tARGETME)));
            data.setTARGETPE(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.tARGETPE)));
            data.setTARGETPR(cursor.getString(cursor.getColumnIndex(XlsDataTable.XlsDataTableColumns.tARGETPR)));

        }
        return data;
    }

    @Override
    public void delete() {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Cursor getCursor(String date) {
        Cursor cursor = db.query(XlsDataTable.NAME,
                null, XlsDataTable.XlsDataTableColumns.dATEVAL + "=?", new String[] { date }, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    @Override
    public Cursor getCursor() {
        Cursor cursor = db.query(XlsDataTable.NAME, null, null, null,
                null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
    @Override
    public List<Datum> getList() {
        Cursor cursor = getCursor();
        return createList(cursor);
    }
    @Override
    public List<Datum> getList(String date) {
        Cursor cursor = getCursor(date);
        return createList(cursor);
    }

    @Override
    public int getSize() {
        return getCursor().getCount();
    }

    @Override
    public long insert(Datum data) {
        try{
        ContentValues cv = new ContentValues();

        //cv.put(XlsDataTable.XlsDataTableColumns._ID, data.getId());
        cv.put(XlsDataTable.XlsDataTableColumns.lINEID,
                data.getLINEID());
        cv.put(XlsDataTable.XlsDataTableColumns.dATEVAL,
                data.getDATEVAL());
        cv.put(XlsDataTable.XlsDataTableColumns.mED,
                data.getMED());

        cv.put(XlsDataTable.XlsDataTableColumns.mEM,
                data.getMEM());
        cv.put(XlsDataTable.XlsDataTableColumns.mEY,
                data.getMEY());
        cv.put(XlsDataTable.XlsDataTableColumns.pED,
                data.getPED());
        cv.put(XlsDataTable.XlsDataTableColumns.pEM,
                data.getPEM());
        cv.put(XlsDataTable.XlsDataTableColumns.pEY,
                data.getPEY());
        cv.put(XlsDataTable.XlsDataTableColumns.pRD,
                data.getPRD());
        cv.put(XlsDataTable.XlsDataTableColumns.pRM,
                data.getPRM());
        cv.put(XlsDataTable.XlsDataTableColumns.pRY,
                data.getPRY());
        cv.put(XlsDataTable.XlsDataTableColumns.rEMK,
                data.getREMK());
        cv.put(XlsDataTable.XlsDataTableColumns.iTC,
                data.getITC());
        cv.put(XlsDataTable.XlsDataTableColumns.uSERNAME,
                data.getUSERNAME());
        cv.put(XlsDataTable.XlsDataTableColumns.cOMPUTER,
                data.getCOMPUTER());
        cv.put(XlsDataTable.XlsDataTableColumns.iPADD,
                data.getIPADD());
        cv.put(XlsDataTable.XlsDataTableColumns.lINENAME,
                data.getLINENAME());
        cv.put(XlsDataTable.XlsDataTableColumns.iTEMNAME,
                data.getITEMNAME());
        cv.put(XlsDataTable.XlsDataTableColumns.tARGETME,
                data.getTARGETME());
        cv.put(XlsDataTable.XlsDataTableColumns.tARGETPE,
                data.getTARGETPE());
        cv.put(XlsDataTable.XlsDataTableColumns.tARGETPR,
                data.getTARGETPR());
        long result = db.insert(XlsDataTable.NAME, null, cv);
        return result;}
        catch (Exception e){
            Syso.error(TAG, "insert error = " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void insertBulk(List<Datum> list) {
        for (int i=0;i<list.size();i++){

            insert(list.get(i));
        }
    }

    @Override
    public void insertOrUpdate(Datum data) {

    }


}
