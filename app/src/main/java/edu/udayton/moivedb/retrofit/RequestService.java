package edu.udayton.moivedb.retrofit;

import edu.udayton.moivedb.retrofit.Bean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by xurengui on 29/06/2017.
 */

public interface RequestService {

    @GET("search/movie?")
    Call<Bean> getKey(@QueryMap Map<String, String> params);

}
