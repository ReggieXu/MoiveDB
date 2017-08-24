package edu.udayton.moivedb.retrofit;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.udayton.moivedb.MainActivity;
import edu.udayton.moivedb.R;
import edu.udayton.moivedb.moviefragment.InfoFragment;
import edu.udayton.moivedb.movieinfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xurengui on 06/07/2017.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;
    private Context mContext;

    public RvAdapter(List<ListItem> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        mContext =c;
        this.listData = listData;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new RvHolder(view);
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        ListItem listItem = listData.get(position);
        holder.name.setText(listItem.getMovieName());
        String path = listItem.getMovieImagePath();
        String imageUrl = "http://image.tmdb.org/t/p/w185"+ path;
        Picasso.with(mContext).load(imageUrl).resize(185,280).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class RvHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private ImageView image;
        private final Context context;

        private RvHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            name = (TextView)itemView.findViewById(R.id.movie_name);
            image = (ImageView)itemView.findViewById(R.id.movie_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            MainActivity myActivity = (MainActivity)view.getContext();


            Intent intent = new Intent(context,movieinfo.class);

            intent.putExtra(movieinfo.NAME_KEY,listData.get(getAdapterPosition()).getMovieName());
            intent.putExtra(movieinfo.REVIVE_KEY,listData.get(getAdapterPosition()).getMovieReview());
            intent.putExtra(movieinfo.IMAGE_KEY,listData.get(getAdapterPosition()).getMovieImagePath());

            context.startActivity(intent);

//            Bundle bundle = new Bundle();
//            bundle.putString("NAME", listData.get(getAdapterPosition()).getMovieName());
//            bundle.putString("REVIVE", listData.get(getAdapterPosition()).getMovieReview());
//            bundle.putString("IMAGEPATH", listData.get(getAdapterPosition()).getMovieImagePath());
//
//            InfoFragment infoFragment = new InfoFragment();
//            infoFragment.setArguments(bundle);
//
//            myActivity.getFragmentManager().beginTransaction().replace(R.id.fragment2, infoFragment).commit();


        }


    }

}



