package com.example.shadow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView chat_frag;
    private ImageView location_frag;
    private ImageView setting_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat_frag = findViewById(R.id.main_chat);
        location_frag = findViewById(R.id.main_location);
        setting_frag = findViewById(R.id.main_setting);

        chat_frag.setOnClickListener(this);
        location_frag.setOnClickListener(this);
        setting_frag.setOnClickListener(this);

        setButtonColor(chat_frag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_chat:
                Intent intent1 = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent1);
                setButtonColor(chat_frag);
                break;
            case R.id.main_location:
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
                setButtonColor(location_frag);
                break;
            case R.id.main_setting:

                setButtonColor(setting_frag);
                break;
            default:
                break;

        }
    }

    private void setButtonColor(ImageView view){
        chat_frag.setBackgroundColor(Color.parseColor("#FFFFFF"));
        location_frag.setBackgroundColor(Color.parseColor("#FFFFFF"));
        setting_frag.setBackgroundColor(Color.parseColor("#FFFFFF"));
        view.setBackgroundColor(Color.parseColor("#C0C0C0"));
    }
}