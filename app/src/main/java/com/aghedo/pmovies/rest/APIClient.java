package com.aghedo.pmovies.rest;

import com.aghedo.pmovies.model.Movie;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;


public class APIClient implements RequestInterceptor {
    public static final String API_KEY = "ea12c869f716552bef3493abf72c3617";
    public static final String KEY_PARAM = "api_key";
    public static final String BASE_URL = "https://rest.themoviedb.org/3";
    private static APIMethods api;
    private static APIClient instance;

    private APIClient() {

    }

    public static APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    private APIMethods getApi() {
        if (api == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setRequestInterceptor(this)
                    .setEndpoint(APIClient.BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            api = restAdapter.create(APIMethods.class);
        }
        return api;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(APIClient.KEY_PARAM, APIClient.API_KEY);
    }

    public List<Movie> getPopular() {
        ListResponse result = getApi().getPopular();
        return result.getResults();
    }

    public List<Movie> getTopRated() {
        ListResponse result = getApi().getTopRated();
        return result.getResults();
    }

    public Movie getMovieDetails(String id) {
        Movie m = getApi().getMovieDetails(id);
        return m;
    }

    private interface APIMethods {
        @GET("/movie/popular")
        ListResponse getPopular();

        @GET("/movie/top_rated")
        ListResponse getTopRated();

        @GET("/movie/{id}")
        Movie getMovieDetails(@Path("id") String id);
    }
}
