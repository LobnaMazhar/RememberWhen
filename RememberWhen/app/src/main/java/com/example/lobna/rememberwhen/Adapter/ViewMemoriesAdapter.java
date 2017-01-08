package com.example.lobna.rememberwhen.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lobna.rememberwhen.Activity.MemoryActivity;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

import java.util.ArrayList;

/**
 * Created by Lobna on 08-Jan-17.
 */

public class ViewMemoriesAdapter extends RecyclerView.Adapter<ViewMemoriesAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<Memory> memories;

    public ViewMemoriesAdapter(Activity activity, ArrayList<Memory> memories){
        this.activity = activity;
        this.memories = memories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = activity.getLayoutInflater().inflate(R.layout.item_view_memories, parent, false);
        return new ViewMemoriesAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.memoryDescription.setText(memories.get(position).getDescription());

        ((LinearLayout)holder.memoryDescription.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, MemoryActivity.class).putExtra("memory",memories.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return memories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView memoryDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            memoryDescription = (TextView) itemView.findViewById(R.id.memoryViewItemText);
        }


    }
}
