package com.example.ian_sibner.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ian_sibner.flicks.R;
import com.example.ian_sibner.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView posterImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    private String imageForOrientation(Movie movie) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        switch(orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                return movie.getPosterPath();
            case Configuration.ORIENTATION_LANDSCAPE:
                return movie.getBackdropPath();
            default:
                throw new RuntimeException("WHAT OTHER ORIENTATION IS THERE?!?!?");
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);


        ViewHolder viewHolder;
        // check if existing view is being reused
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.posterImage =
                    (ImageView) convertView.findViewById(R.id.moviePosterImageView);
            viewHolder.title = (TextView) convertView.findViewById(R.id.movieTitleTextView);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.movieOverviewTextView);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // populate data
        viewHolder.posterImage.setImageResource(0);
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());
        Picasso
            .with(getContext())
            .load(this.imageForOrientation(movie))
            .fit().centerCrop().noFade()
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.posterImage);
        return convertView;
    }
}
