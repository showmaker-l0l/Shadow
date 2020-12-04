package com.example.shadow.http;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetMessageTask extends AsyncTask<Void,Void,Boolean> {

    private Context mContext;
    public GetMessageTask(Context context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Toast.makeText(mContext,"Message get！",Toast.LENGTH_SHORT).show();
    }
}
