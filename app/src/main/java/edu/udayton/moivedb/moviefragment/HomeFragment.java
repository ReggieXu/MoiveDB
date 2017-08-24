package edu.udayton.moivedb.moviefragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.udayton.moivedb.MainActivity;
import edu.udayton.moivedb.R;
import edu.udayton.moivedb.login.LoginActivity;
import edu.udayton.moivedb.login.Session;
import edu.udayton.moivedb.retrofit.Bean;
import edu.udayton.moivedb.retrofit.ListItem;
import edu.udayton.moivedb.retrofit.RequestService;
import edu.udayton.moivedb.retrofit.RvAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    private EditText txtSearch;
    private RecyclerView recyclerView;
    private Session session;

    final List<ListItem> AllData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        session = new Session(getActivity());

        txtSearch = (EditText) view.findViewById(R.id.txt_search);
        recyclerView = (RecyclerView)view.findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button btnSearch = (Button) view.findViewById(R.id.btn_search);
        Button btnLogout = (Button) view.findViewById(R.id.btn_logout);

        final List<ListItem> AllData = new ArrayList<>();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunRetrofit();
            }
        });

        return view;

    }

    private void logout() {
        session.setLoggedin(false);
        getActivity().finish();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    private void RunRetrofit(){

        Retrofit retrofit = new Retrofit.
                Builder().baseUrl("https://api.themoviedb.org/3/").
                addConverterFactory(GsonConverterFactory.create()).build();

        RequestService requestService = retrofit.create(RequestService.class);
        Map<String,String> params = new HashMap<>();
        params.put("query",txtSearch.getText().toString());
        params.put("api_key", "28791d80ad154260fe80188b966a2530");

        Call<Bean> call = requestService.getKey(params);

        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> beanResponse) {
                if(beanResponse.isSuccessful()){

                    if (!AllData.isEmpty()){
                        AllData.clear();
                    }

                    for (int i = 0; i<beanResponse.body().getResults().size();i++){

                        Bean.ResultsBean bean = beanResponse.body().getResults().get(i);

                        ListItem movieData = new ListItem();

                        movieData.setMovieName(bean.getOriginal_title());
                        movieData.setMovieImagePath(bean.getPoster_path());
                        movieData.setMovieReview(bean.getOverview());
                        movieData.setMovieRate(bean.getVote_average());

                        AllData.add(movieData);

                    }
                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Log.i("LHD","Request fails");
            }
        });

        RvAdapter adapter = new RvAdapter(AllData, getActivity());

        recyclerView.invalidate();
        recyclerView.setAdapter(adapter);

    }

}
