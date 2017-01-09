package com.example.lobna.rememberwhen.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lobna.rememberwhen.Activity.HomeActivity;
import com.example.lobna.rememberwhen.Database.DBSource;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

/**
 * Created by Lobna on 09-Jan-17.
 */

public class Menu extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        // Set delete option to visible with memory activity
        if(getClass().getName().contains("MemoryActivity")) {
            menu.findItem(R.id.action_delete).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Menu.this, HomeActivity.class)); // TODO go to settings activity
            return true;
        }else if(id == R.id.action_delete){
            Memory memory = getIntent().getExtras().getParcelable("memory");
            if(DBSource.getInstance().deleteMemory(memory.getId())){
                finish();
                Toast.makeText(Menu.this, "Deleted.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Menu.this, "NOT DELETED, Report to Lobna, please B|", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
