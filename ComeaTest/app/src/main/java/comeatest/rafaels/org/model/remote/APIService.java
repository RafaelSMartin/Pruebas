package comeatest.rafaels.org.model.remote;


import java.util.List;

import comeatest.rafaels.org.model.Model;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
  Created by Rafael S Martin on 24/03/2018.
 */


/** FormUrlEncoded
 * Esto indicará que la petición tendrá su tipo MIME (un campo de encabezado que identifica
 * el formato del cuerpo de una petición o respuesta HTTP) establecido a
 * application/x-www-form-urlencoded y también que sus nombres de campo y valores
 * serán codificados en UTF-8 antes de ser codificados en URI
 *
 *
 *  Field("key")
 * debería empatar el nombre que la API espera.
 * Retrofit convierte implícitamente los valores a cadenas de texto usando String.valueOf(Object),
 * y las cadenas son entonces codificadas en forma de URL. Los valores null son ignorados.
 *
 **/

public interface APIService {
    //Queremos hacer una peticion POST
//    @POST("/direccion_post")
//    @FormUrlEncoded
//    Call<PojoResponse> savePost(@Field("employeePath") String employeePath,
//                             @Field("employeePath45") String employeePath45,
//                             @Field("employeeInfo") String employeeInfo,
//                             @Field("partners") String partners,
//                             @Field("teams") String teams,
//                             @Field("members") String members,
//                             @Field("messages") String messages,
//                             @Field("response") String response
//    );
    @GET("api/restaurants")
    Call<List<Model>> callModel();

    // Peticion del tipo
    // http://intranet.topdigital.es/apitop/api/test_post_data/?nombre=Ale&apellidos=gzl&telefono=626896727
//    @Headers("Content-Type:application/json")
//    @POST("/apitop/api/test_post_data/")
//    Call<ResponseBody> login(@Query("nombre") String nombre,
//                             @Query("apellidos") String apellidos,
//                             @Query("telefono") String telefono
//    );





}
