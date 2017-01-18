package com.example.lobna.rememberwhen.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lobna.rememberwhen.Adapter.ViewMemoriesAdapter;
import com.example.lobna.rememberwhen.Database.DBSource;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

import java.util.ArrayList;

/**
 * Created by Lobna on 08-Jan-17.
 */

public class ViewMemoriesFragment extends Fragment {

    private View rootView;
    private RecyclerView viewMemoriesRecyclerView;
    private ViewMemoriesAdapter viewMemoriesAdapter;
    private ArrayList<Memory> memories;
    private ImageView noMemoriesBackgroundImage;
    private TextView noMemoriesBackgroundText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_memories, container, false);

        viewMemoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.allMemories);
        viewMemoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewMemoriesRecyclerView.addItemDecoration(new DividerItemDecoration(viewMemoriesRecyclerView.getContext(), new LinearLayoutManager(getActivity()).getOrientation()));

        noMemoriesBackgroundImage = (ImageView) rootView.findViewById(R.id.noMemoriesBackgroundImage);
        noMemoriesBackgroundText = (TextView) rootView.findViewById(R.id.noMemoriesBackgroundText);

        getMemories();

        return rootView;
    }

    @Override
    public void onResume() {
        getMemories();
        super.onResume();
    }

    private void getMemories() {
        memories = DBSource.getInstance().getMemories();
        if(memories.size() != 0){
            noMemoriesBackgroundImage.setVisibility(View.INVISIBLE);
            noMemoriesBackgroundText.setVisibility(View.INVISIBLE);
        }else{
            noMemoriesBackgroundImage.setVisibility(View.VISIBLE);
            noMemoriesBackgroundText.setVisibility(View.VISIBLE);
        }
        viewMemoriesAdapter = new ViewMemoriesAdapter(getActivity(), memories);
        viewMemoriesRecyclerView.setAdapter(viewMemoriesAdapter);
    }
}
