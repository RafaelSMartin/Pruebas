package org.rafaels.tdconsulting.model.remote;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "http://intranet.topdigital.es/apitop/api/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
