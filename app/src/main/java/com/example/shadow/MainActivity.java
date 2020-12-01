package com.example.shadow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.shadow.fragment.ChatFragment;
import com.example.shadow.fragment.LocationFragment;
import com.example.shadow.fragment.SettingFragment;

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

        replaceFragment(new ChatFragment());
        setButtonColor(chat_frag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_chat:
                replaceFragment(new ChatFragment());
                setButtonColor(chat_frag);
                break;
            case R.id.main_location:
                replaceFragment(new LocationFragment());
                setButtonColor(location_frag);
                break;
            case R.id.main_setting:
                replaceFragment(new SettingFragment());
                setButtonColor(setting_frag);
                break;
            default:
                break;

        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commit();
    }

    private void setButtonColor(ImageView view){
        chat_frag.setBackgroundColor(Color.parseColor("#FFFFFF"));
        location_frag.setBackgroundColor(Color.parseColor("#FFFFFF"));
        setting_frag.setBackgroundColor(Color.parseColor("#FFFFFF"));
        view.setBackgroundColor(Color.parseColor("#C0C0C0"));
    }
}