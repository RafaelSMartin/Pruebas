package comeatest.rafaels.org.model.remote;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

public class ApiUtils {

    private ApiUtils(){}

    private static final String BASE_URL = "https://arcane-atoll-67184.herokuapp.com/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
