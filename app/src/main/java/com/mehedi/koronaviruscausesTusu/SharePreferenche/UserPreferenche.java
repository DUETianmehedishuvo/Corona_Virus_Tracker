package com.mehedi.koronaviruscausesTusu.SharePreferenche;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferenche {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    private static final String PREFERENCHE_NAME="corona Preferenche";
    private static final String TMCases="TMCases";
    private static final String TMDeads="TMDeads";
    private static final String TMRecorvered="TMRecorvered";

    private static final String TACases="TACases";
    private static final String TADeads="TADeads";

    private static final String TBCases="TBCases";
    private static final String TBDeads="TBDeads";
    private static final String TBRecorvered="TBRecorved";
    private static final String TBTCases="TBTCases";

    private static final String TTime="TTime";


    public UserPreferenche(Context context) {
        sharedPreferences=context.getSharedPreferences(PREFERENCHE_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }


    public void saveTotalData(String cases, String deads, String recorvered, String time){
        editor.putString(TMCases,cases);
        editor.putString(TMDeads,deads);
        editor.putString(TMRecorvered,recorvered);
        editor.putString(TTime,time);
        editor.commit();
    }

    public void saveTodayData(String cases,String deads){
        editor.putString(TACases,cases);
        editor.putString(TADeads,deads);
        editor.commit();
    }

    public void saveBangladeshiData(String cases,String deads,String recorved,String todayCases){
        editor.putString(TBCases,cases);
        editor.putString(TBDeads,deads);
        editor.putString(TBRecorvered,recorved);
        editor.putString(TBTCases,todayCases);
        editor.commit();
    }


    public  String getTTime() {
        return sharedPreferences.getString(TTime,"0");
    }

    public  String getTMCases() {
        String data=sharedPreferences.getString(TMCases,"0");
        return data;
    }

    public  String getTMDeads() {
        return sharedPreferences.getString(TMDeads,"0");
    }

    public  String getTMRecorvered() {
        return sharedPreferences.getString(TMRecorvered,"0");
    }

    public  String getTACases() {
        return sharedPreferences.getString(TACases,"0");
    }

    public  String getTADeads() {
        return sharedPreferences.getString(TADeads,"0");
    }

    public  String getTBCases() {
        return sharedPreferences.getString(TBCases,"0");
    }

    public  String getTBDeads() {
        return sharedPreferences.getString(TBDeads,"0");
    }

    public  String getTBRecorvered() {
        return sharedPreferences.getString(TBRecorvered,"0");
    }

    public  String getTBTCases() {
        return sharedPreferences.getString(TBTCases,"0");
    }
}
