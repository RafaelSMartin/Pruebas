package com.rafaels.universapptest.model.remote;

import com.rafaels.universapptest.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("/api/?results=15&exc=location,login,id,email,registered,cell,nat")
    Call<Model> callModel();

}
