package com.arbaelbarca.depedencymodule.network;

import com.arbaelbarca.depedencymodule.model.modeltopheadlines.ResponseNewsTopHeadlines;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiServices {
    @GET("top-headlines")
    Observable<ResponseNewsTopHeadlines> getListTopHeadlines(
            @Query("country") String country

    );
}
