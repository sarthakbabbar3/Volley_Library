package com.example.sarthakbabbar.volley;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getName();
    private static final String REQUESTTAG= "string request first";
    private Button button;
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private String url="http://www.mocky.io/v2/5aba54f3350000540073a5c9";
    private DiskBasedCache mCache;
    private com.android.volley.Network mNetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send reuest and print response using volley library
            sendRequestAndPrintResponse();

            }
        });
    }

    private void sendRequestAndPrintResponse() {

        //mRequestQueue= Volley.newRequestQueue(this);
        //created our own request queue below & singleton


        //mCache=new DiskBasedCache(getCacheDir(),4*1024*1024);

        //mNetwork=new BasicNetwork(new HurlStack());

        //mRequestQueue=new RequestQueue(mCache,mNetwork);

        //mRequestQueue.start();


        mRequestQueue=VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());







        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : "+response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error : "+ error.toString());

            }
        });
        stringRequest.setTag(REQUESTTAG);
        mRequestQueue.add(stringRequest);

    }
//if the activity is stopped then cancel the requests
    @Override
    protected void onStop() {
        super.onStop();
        if(mRequestQueue!=null)
            mRequestQueue.cancelAll(REQUESTTAG);
    }
}
