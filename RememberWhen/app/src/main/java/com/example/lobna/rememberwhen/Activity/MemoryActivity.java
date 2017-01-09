package com.example.lobna.rememberwhen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.lobna.rememberwhen.Database.DBSource;
import com.example.lobna.rememberwhen.Menu.Menu;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

public class MemoryActivity extends Menu {

    private Memory memory;

    private TextView memoryDescription;
    private TextView memoryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set delete option to visible
       // findViewById(R.id.action_delete).setVisibility(View.VISIBLE);

        memory = getIntent().getExtras().getParcelable("memory");

        memoryDescription = (TextView) findViewById(R.id.memoryDescription);
        memoryDate = (TextView) findViewById(R.id.memoryDate);

        fillData();
    }

    private void fillData() {
        memoryDescription.setText(memory.getDescription());
        memoryDate.setText(memory.getDate());
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    private void getData() {
        memory = DBSource.getInstance().getMemory(memory.getId());
        fillData();
    }

    public void editMemory(View view){
        Intent editMemory = new Intent(MemoryActivity.this, AddMemoryActivity.class);
        editMemory.putExtra("Memory", memory);
        startActivity(editMemory);
    }
}
