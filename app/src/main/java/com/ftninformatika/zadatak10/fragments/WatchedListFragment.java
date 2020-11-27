package com.ftninformatika.zadatak10.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ftninformatika.zadatak10.R;
import com.ftninformatika.zadatak10.adapters.WatchedListAdapter;
import com.ftninformatika.zadatak10.model.Movie;

import java.util.List;

public class WatchedListFragment extends Fragment {

    private List<Movie> watched;
    private onItemClickListener listener;

    public WatchedListFragment() {
        // Required empty public constructor
    }

    public void setWatched(List<Movie> watched) {
        this.watched = watched;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watched_list, container, false);

        ListView lvWatched = view.findViewById(R.id.lvWatched);

        if (watched != null) {
            WatchedListAdapter adapter = new WatchedListAdapter(getActivity(), watched);
            lvWatched.setAdapter(adapter);

            lvWatched.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listener.onWatchedItemClicked(adapter.getItem(position));
                }
            });
        }
        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onItemClickListener) {
            listener = (onItemClickListener) context;
        } else {
            Toast.makeText(getActivity(), "Morate implementirati intefrace", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface onItemClickListener {
        void onWatchedItemClicked(Movie movie);
    }
}
