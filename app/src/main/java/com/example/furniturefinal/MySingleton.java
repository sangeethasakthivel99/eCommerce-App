package com.example.furniturefinal;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private Context context;

    private MySingleton(Context mcontext) {
        context = mcontext;
        // requestQueue=getRequestQueue();
    }

   /* public RequestQueue requestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            return requestQueue;
        }

    }*/
}
