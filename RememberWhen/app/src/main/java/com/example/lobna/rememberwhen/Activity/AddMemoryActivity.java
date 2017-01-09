package com.example.lobna.rememberwhen.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lobna.rememberwhen.Database.DBSource;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

public class AddMemoryActivity extends AppCompatActivity {

    private boolean addMemory;
    private Memory memory;
    private static int day = 1, month = 1, year = 1996;

    //  private TextView memoryDescriptionError;
    private AutoCompleteTextView memoryDescription;
    private DatePicker memoryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getIntent().getExtras() != null){
            toolbar.setTitle("Edit memory");
            addMemory = false;
        }else{
            toolbar.setTitle("Add memory");
            addMemory = true;
        }
        setSupportActionBar(toolbar);

  //      memoryDescriptionError = (TextView) findViewById(R.id.memoryDescriptionError);
        memoryDescription = (AutoCompleteTextView) findViewById(R.id.memoryDescription);
        memoryDate = (DatePicker) findViewById(R.id.memoryDate);

        if(!addMemory){
            fillDateToEdit();
        }
    }

    private void fillDateToEdit() {
        memory = getIntent().getExtras().getParcelable("Memory");

        memoryDescription.setText(memory.getDescription());

        getDate();
        memoryDate.init(year,month,day,null);
    }

    private void getDate() {
        String memoryDate = memory.getDate();

        // Check if day is 1 digit *slash is at index 1* ,, or 2 digits *slash is at index 2*
        if(memoryDate.charAt(1) == '/') { // 1/...
            day = Integer.valueOf(memoryDate.substring(0, 1));

            // Check if month is 1 digit *slash is at index 3* ,, or 2 digits *slash is at index 4*
            if(memoryDate.charAt(3) == '/'){ // 1/1/...
                month = Integer.valueOf(memoryDate.substring(2,3));
                --month; // 3shan lama b3mlu read b3d l save bykun 0-based fa bzwdu 1 ,, w hna brg3u tany ll asly -1

                // Year starts at index 4 till the end
                year = Integer.valueOf(memoryDate.substring(4));
            }else if(memoryDate.charAt(4) == '/'){ // 1/11/...
                month = Integer.valueOf(memoryDate.substring(2,4));
                --month; // 3shan lama b3mlu read b3d l save bykun 0-based fa bzwdu 1 ,, w hna brg3u tany ll asly -1

                // Year starts at index 5 till the end
                year = Integer.valueOf(memoryDate.substring(5));
            }
        }else if(memoryDate.charAt(2) == '/') { // 11/...
            day = Integer.valueOf(memoryDate.substring(0, 2));

            // Check if month is 1 digit *slash is at index 4* ,, or 2 digits *slash is at index 5*
            if(memoryDate.charAt(4) == '/'){ // 11/1/...
                month = Integer.valueOf(memoryDate.substring(3,4));
                --month; // 3shan lama b3mlu read b3d l save bykun 0-based fa bzwdu 1 ,, w hna brg3u tany ll asly -1

                // Year starts at index 5 till the end
                year = Integer.valueOf(memoryDate.substring(5));
            }else if(memoryDate.charAt(5) == '/'){ // 11/11/...
                month = Integer.valueOf(memoryDate.substring(3,5));
                --month; // 3shan lama b3mlu read b3d l save bykun 0-based fa bzwdu 1 ,, w hna brg3u tany ll asly -1

                // Year starts at index 6 till the end
                year = Integer.valueOf(memoryDate.substring(6));
            }
        }
    }

    public void saveMemory(View view){
      //  memoryDescriptionError.setVisibility(View.INVISIBLE);

        if(memoryDescription.getText().toString().length() == 0){
         //   memoryDescriptionError.setVisibility(View.VISIBLE);
            memoryDescription.setError(getString(R.string.missingDescriptionError));
         //   Toast.makeText(this, "Fill in the description, please", Toast.LENGTH_SHORT).show();
            return;
        }
        String description = memoryDescription.getText().toString();

        String date = memoryDate.getDayOfMonth() + "/" + (memoryDate.getMonth()+1) + "/" + memoryDate.getYear();

        if(addMemory) {
            if (DBSource.getInstance().addMemory(new Memory(description, date))) {
                Toast.makeText(this, "Memory was added successfully ;)", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "NOT ADDED, Report to Lobna, please B|", Toast.LENGTH_LONG).show();
            }
        }else if(!addMemory){
            memory.setDescription(description);
            memory.setDate(date);
            if (DBSource.getInstance().editMemory(memory)) {
                Toast.makeText(this, "Memory was edited successfully ;)", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "NOT EDITED, Report to Lobna, please B|", Toast.LENGTH_LONG).show();
            }
        }
    }

}
