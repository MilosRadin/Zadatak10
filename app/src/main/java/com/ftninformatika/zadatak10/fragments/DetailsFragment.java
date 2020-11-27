package com.ftninformatika.zadatak10.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ftninformatika.zadatak10.R;
import com.ftninformatika.zadatak10.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private Movie movie;

    public DetailsFragment() {
    }


    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        setHasOptionsMenu(true);

        ImageView imPoster = view.findViewById(R.id.imageView_Details);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvYear = view.findViewById(R.id.tvYear);
        TextView tvRuntime = view.findViewById(R.id.tvRuntime);
        TextView tvGenre = view.findViewById(R.id.tvGenre);
        TextView tvPlot = view.findViewById(R.id.tvPlot);
        TextView tvLanguage = view.findViewById(R.id.tvLanguage);
        TextView tvAwards = view.findViewById(R.id.tvAwards);
        TextView tvDate = view.findViewById(R.id.tvDate);

        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setRating(movie.getRating());

        tvTitle.setText(movie.getTitle());
        tvYear.setText(movie.getYear());
        tvRuntime.setText(movie.getRuntime());
        tvGenre.setText(movie.getGenre());
        tvPlot.setText(movie.getPlot());
        tvLanguage.setText(movie.getLanguage());
        tvAwards.setText(movie.getAwards());
        tvDate.setText(movie.getDateWatched());

        Picasso.get().load(movie.getPoster()).into(imPoster);

        return view;
    }
}
