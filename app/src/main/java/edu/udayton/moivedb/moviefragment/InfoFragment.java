package edu.udayton.moivedb.moviefragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.udayton.moivedb.R;
import com.squareup.picasso.Picasso;


public class InfoFragment extends Fragment {

    private TextView txt_name, txt_review ;
    private ImageView movieImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info,container,false);

        txt_name = (TextView) v.findViewById(R.id.movie_name_2);
        txt_review = (TextView) v.findViewById(R.id.movie_review);
        movieImage = (ImageView) v.findViewById(R.id.movie_image_2);

        Bundle bundle = getArguments();

        if (bundle != null) {
            String name = bundle.getString("NAME");
            String revive = bundle.getString("REVIVE");
            String imagePath = bundle.getString("IMAGEPATH");

            String imageUrl = "http://image.tmdb.org/t/p/w185"+ imagePath;

            Picasso.with(getActivity()).load(imageUrl).resize(185,280).into(movieImage);

            txt_name.setText(name);
            txt_review.setText(revive);
        }

        return v;

    }



}
