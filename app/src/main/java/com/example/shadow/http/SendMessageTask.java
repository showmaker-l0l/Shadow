package com.example.shadow.http;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.shadow.db.Message;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class SendMessageTask extends AsyncTask<Message,Void,Boolean> {

    private Context mContext;
    public SendMessageTask(Context context){
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(Message... messages) {

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Toast.makeText(mContext,"Message Send！",Toast.LENGTH_SHORT).show();
    }
}
