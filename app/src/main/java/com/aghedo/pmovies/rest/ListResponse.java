package com.aghedo.pmovies.rest;

import com.aghedo.pmovies.model.Movie;

import java.util.List;

public class ListResponse {
    List<Movie> results;
    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
