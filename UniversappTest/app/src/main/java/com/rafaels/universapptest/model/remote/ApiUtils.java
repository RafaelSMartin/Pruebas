package com.rafaels.universapptest.model.remote;

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "https://randomuser.me/api/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
