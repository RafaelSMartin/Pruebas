package com.rafaels.testvocces.volley;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by Rafael S Martin on 27/02/2018.
 */

public class SendRequest {

    private String request;

    public SendRequest(String request){
        this.request = request;
    }

    public StringRequest getRequest(Response.Listener<String> responseListener, Response.ErrorListener errorListener){

        final HashMap<String, String> credenciales = new HashMap<String, String>();
        credenciales.put("request", request);

        String url = "https://randomuser.me/api/?results=15&exc=location,login,id,email,registered,cell,nat";

        StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener){
            @Override
            public String getBodyContentType(){
                // Condificacion que va a tener UTF8
                return "application/json charset="+getParamsEncoding();
            }
            @Override
            public byte[] getBody(){
                // Devuelve un objeto de tipo JSON formado con las credenciales y codificacion
                try {
                    return new JSONObject(credenciales).toString().getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException e){

                }
                return null;
            }
        };
        // Las veces que queremos que intente la transaccion
        request.setRetryPolicy(new LongTimeoutAndTryRetryPolicy(LongTimeoutAndTryRetryPolicy.RETRIES_PHONE_ISP));
        return request;

    }

}
