package com.example.lobna.rememberwhen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lobna.rememberwhen.R;
import com.example.lobna.rememberwhen.Menu.*;

public class HomeActivity extends Menu{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addMemory(View view){
        startActivity(new Intent(HomeActivity.this, AddMemoryActivity.class)); // TODO go to add memory activity
    }
}
