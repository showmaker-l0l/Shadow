package com.example.shadow.util;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.shadow.db.Message;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.Date;

public class Utility {

    public static JsonObject messageToJson(Message message) {
        JsonObject json = new JsonObject();
        json.addProperty("content", message.getContent());
        json.addProperty("userID", message.getUserID());
        json.addProperty("time", message.getTime());
        json.addProperty("date", message.getDate());
        return json;
    }

    //传入long型时间，返回格式化日期
    public static String longToDate(long time){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(date);
    }

    //传入yyyy年MM月dd日String时间，转为long型毫秒值
    public static long stringDateToLong(String date)  {
        Calendar c = Calendar. getInstance();
        try {
            c.setTime( new SimpleDateFormat("yyyy年MM月dd日" ).parse(date ));
        }catch (ParseException e){
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }
}
