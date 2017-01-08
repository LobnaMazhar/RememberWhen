package com.example.lobna.rememberwhen.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lobna.rememberwhen.Adapter.ViewMemoriesAdapter;
import com.example.lobna.rememberwhen.Database.DBSource;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

import java.util.ArrayList;

/**
 * Created by Lobna on 08-Jan-17.
 */

public class ViewMemoriesFragment extends Fragment {

    private RecyclerView viewMemoriesRecyclerView;
    private ViewMemoriesAdapter viewMemoriesAdapter;
    private ArrayList<Memory> memories;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_memories, container, false);

        viewMemoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.allMemories);
        viewMemoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        viewMemoriesAdapter = new ViewMemoriesAdapter(getActivity(), memories);
        viewMemoriesRecyclerView.setAdapter(viewMemoriesAdapter);
    }
}
