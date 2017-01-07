package com.example.lobna.rememberwhen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

public class MemoryActivity extends AppCompatActivity {

    Memory memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        memory = new Memory();
        memory.setId(getIntent().getExtras().getInt("memoryID"));
        memory.setDescription(getIntent().getExtras().getString("memoryDescription"));
        memory.setDate(getIntent().getExtras().getString("memoryDate"));
    }

    public void editMemory(View view){
        Intent editMemory = new Intent(MemoryActivity.this, AddMemoryActivity.class);
        editMemory.putExtra("Memory", memory);
        startActivity(editMemory);
    }
}
