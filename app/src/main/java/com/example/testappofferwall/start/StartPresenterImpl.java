package com.example.testappofferwall.start;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testappofferwall.Utils.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class StartPresenterImpl implements StartPresenter {

    private StartView startViewInt;
    private SQLiteHandler db;

    @Override
    public void attachView(StartView view) {
        startViewInt = view;
    }

    @Override
    public void detachView() {
        startViewInt = null ;
    }

    @Override
    public void sendRequest() {
        db = new SQLiteHandler(startViewInt.getContext());
        RequestQueue getRequestQueue = Volley.newRequestQueue(startViewInt.getContext());

        String url = "https://tstofferwall.000webhostapp.com/get_request.php?status=true";
        StringRequest getStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG START ACTIVITY ", "StartActivity data Response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(!jsonObject.getBoolean("error")) {
                        boolean allowAttribute = jsonObject.getBoolean("allow");
                        if (allowAttribute) {
                            startViewInt.startNextStep(true);
                            db.addAllowValue(1);
                        }
                        else {
                            startViewInt.startNextStep(false);
                            db.addAllowValue(0);
                        }
                    }
                    else {
                        startViewInt.error();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        getRequestQueue.add(getStringRequest);
    }
}
