package com.example.sarthakbabbar.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SARTHAK BABBAR on 27-Mar-18.
 */
//throughout the application there will be only one instance of request queue
class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;


    public static VolleySingleton getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new VolleySingleton(context);
        }

        return mInstance;
    }

    private VolleySingleton(Context context) {

    mRequestQueue=getRequestQueue(context);

    }


    public RequestQueue getRequestQueue(Context context){
        if(mRequestQueue==null)
        {
            mRequestQueue= Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }


}
