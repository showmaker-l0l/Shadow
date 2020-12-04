package com.example.shadow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shadow.db.Message;
import com.example.shadow.http.HttpUtil;
import com.example.shadow.http.SendMessageTask;
import com.example.shadow.util.MsgAdapter;
import com.example.shadow.util.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "post";
    private EditText inputMsg;
    private Button send;
    private List<Message> msgList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initMsgs();

        inputMsg = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        adapter = new MsgAdapter(msgList);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.getMessage(new okhttp3.Callback(){
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.d(TAG, "onResponse: "+responseData);
                    }
                });
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputMsg.getText().toString();
                if (!"".equals(content)){
                    Message message = new Message();
                    message.setContent(content);
                    message.setUserID(1);
                    long time = System.currentTimeMillis();
                    message.setTime(time);
                    message.setDate(Utility.longToDate(time));
                    message.save();
                    JsonObject json = Utility.messageToJson(message);
                    Log.d(TAG, "onClick: " + json);
                    HttpUtil.sendOKHttpRequest("http://192.168.42.65:8080/Shadow/message", json, new okhttp3.Callback(){
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String responseData = response.body().string();
                            Log.d(TAG, "onResponse: "+responseData);
                        }
                    });
                    msgList.add(message);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    inputMsg.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        msgList.clear();
        List<Message> messageList = LitePal.order("time").find(Message.class);
        for (Message message : messageList){
            msgList.add(message);
        }
    }
}
