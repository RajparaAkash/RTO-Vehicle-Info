package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.request;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class CustomRequestQueue {
    private static CustomRequestQueue mInstance;
    private final Context context;
    private RequestQueue requestQueue;

    private CustomRequestQueue(Context context) {
        this.context = context;
    }

    public static synchronized CustomRequestQueue getInstance(Context context) {
        CustomRequestQueue customRequestQueue;
        synchronized (CustomRequestQueue.class) {
            synchronized (CustomRequestQueue.class) {
                synchronized (CustomRequestQueue.class) {
                    if (mInstance == null) {
                        mInstance = new CustomRequestQueue(context);
                    }
                    customRequestQueue = mInstance;
                }
                return customRequestQueue;
            }
        }
    }

    private RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(this.context);
        }
        return this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "CustomRequestQueue";
        }
        request.setTag(str);
        getRequestQueue().add(request);
    }
}
