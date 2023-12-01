package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class RequestLoader {
    public Context context;
    public boolean isProgressDialogShowing;
    public TaskHandler.JsonResponseHandler jsonResponseHandler;
    public TaskHandler mInstance;
    public ProgressDialog progressDialog;
    public String requestUrl;
    public TaskHandler.ResponseHandler<String> responseHandler;
    Map<String, Object> params;
    int requestMethod;
    String tag;

    public RequestLoader(TaskHandler taskHandler, int i, Map<String, Object> map, String str, boolean z, Context context, ProgressDialog progressDialog, TaskHandler.JsonResponseHandler jsonResponseHandler, String str2) {
        this.mInstance = taskHandler;
        this.requestMethod = i;
        this.params = map;
        this.requestUrl = str;
        this.isProgressDialogShowing = z;
        this.context = context;
        this.progressDialog = progressDialog;
        this.jsonResponseHandler = jsonResponseHandler;
        this.tag = str2;
    }

    public RequestLoader(TaskHandler taskHandler, int i, Map<String, Object> map, String str, boolean z, Context context, ProgressDialog progressDialog, TaskHandler.ResponseHandler<String> responseHandler, String str2) {
        this.mInstance = taskHandler;
        this.requestMethod = i;
        this.params = map;
        this.requestUrl = str;
        this.isProgressDialogShowing = z;
        this.context = context;
        this.progressDialog = progressDialog;
        this.responseHandler = responseHandler;
        this.tag = str2;
    }

    public void request() {
        JSONObject jSONObject;
        StringBuilder sb = new StringBuilder();
        if (this.requestMethod == 0) {
            for (Map.Entry<String, Object> entry : this.params.entrySet()) {
                sb.append("&");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(this.mInstance.encodeString((String) entry.getValue()));
            }
            jSONObject = null;
        } else {
            jSONObject = new JSONObject(this.params);
        }
        JSONObject jSONObject2 = jSONObject;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.requestUrl);
        sb2.append(sb.length() == 0 ? "" : "?" + sb);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(this.requestMethod, sb2.toString(), jSONObject2, new JsonResponseListener(this), new RequestErrorListener(this));
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(250000, 1, 1.0f));
        CustomRequestQueue.getInstance(this.context).addToRequestQueue(jsonObjectRequest, this.tag);
    }

    public void requestExternal() {
        StringRequest stringRequest = new StringRequest(this.requestMethod, this.requestUrl, new Response.Listener() {
            @Override
            public void onResponse(Object obj) {
                RequestLoader.this.lambda$requestExternal$0$RequestLoader((String) obj);
            }
        }, new RequestErrorListener(this)) {
            @Override
            public Map<String, String> getParams() {
                HashMap hashMap = new HashMap();
                for (Map.Entry<String, Object> entry : RequestLoader.this.params.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                return hashMap;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap hashMap = new HashMap();
                hashMap.put("Content-Type", "application/x-www-form-urlencoded");
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(250000, 1, 1.0f));
        CustomRequestQueue.getInstance(this.context).addToRequestQueue(stringRequest, this.tag);
    }

    public void lambda$requestExternal$0$RequestLoader(String str) {
        ProgressDialog progressDialog;
        if (this.isProgressDialogShowing && Utils.isActivityFinished(this.context) && (progressDialog = this.progressDialog) != null && progressDialog.isShowing()) {
            this.mInstance.cancelProgressDialog(this.progressDialog);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("E_VEHICLE_DETAILS_PARAMS", this.params.toString());
            bundle.putString("E_VEHICLE_DETAILS_RESPONSE", str);
            bundle.putString("content_type", "E_VEHICLE_DETAILS");
        } catch (Exception unused) {
        }
        this.responseHandler.onResponse(str);
    }
}
