package kr.ac.yjc.wdj.capstondesignproject;

/**
 * Created by dogsa on 2018-04-16.
 */

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class LoginRequest extends StringRequest {

    final static private String URL = "http://13.124.213.132/login";
    private Map<String, String> parameters;

    //생성자
    public LoginRequest(String id, String password, String type, String device, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();

        parameters.put("id", id);
        parameters.put("password", password);
        parameters.put("type", type);
        parameters.put("device", device);
    }

    //추후 사용을 위한 부분
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}