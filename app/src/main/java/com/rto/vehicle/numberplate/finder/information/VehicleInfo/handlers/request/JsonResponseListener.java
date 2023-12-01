package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.request;

import com.android.volley.Response;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;


public class JsonResponseListener implements Response.Listener<JSONObject> {
    private final RequestLoader requestLoader;

    public JsonResponseListener(RequestLoader requestLoader) {
        this.requestLoader = requestLoader;
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        if (this.requestLoader.isProgressDialogShowing && Utils.isActivityFinished(this.requestLoader.context) && this.requestLoader.progressDialog != null && this.requestLoader.progressDialog.isShowing()) {
            this.requestLoader.mInstance.cancelProgressDialog(this.requestLoader.progressDialog);
        }
        this.requestLoader.jsonResponseHandler.onResponse(jSONObject);
    }
}
