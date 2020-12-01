package com.example.shadow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shadow.db.Message;
import com.example.shadow.util.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

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

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputMsg.getText().toString();
                if (!"".equals(content)){
                    Message message = new Message(content,Message.TYPE_SENT);
                    msgList.add(message);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    inputMsg.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Message msg1 = new Message("Hello guy.",Message.TYPE_RECEIVED);
        msgList.add(msg1);
        Message msg2 = new Message("Hello. Who`s that?",Message.TYPE_SENT);
        msgList.add(msg2);
        Message msg3 = new Message("This is TOM.",Message.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
