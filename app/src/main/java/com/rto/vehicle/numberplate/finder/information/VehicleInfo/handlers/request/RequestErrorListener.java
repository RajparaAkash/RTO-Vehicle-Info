package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;

import com.rto.vehicle.numberplate.finder.information.R;

public class RequestErrorListener implements Response.ErrorListener {

    private final RequestLoader requestLoader;

    public RequestErrorListener(RequestLoader requestLoader) {
        this.requestLoader = requestLoader;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (this.requestLoader.isProgressDialogShowing && Utils.isActivityFinished(this.requestLoader.context) && this.requestLoader.progressDialog != null && this.requestLoader.progressDialog.isShowing()) {
            this.requestLoader.mInstance.cancelProgressDialog(this.requestLoader.progressDialog);
        }
        NetworkResponse networkResponse = volleyError.networkResponse;
        if (networkResponse != null) {
            if (this.requestLoader.jsonResponseHandler != null) {
                this.requestLoader.jsonResponseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
            } else if (this.requestLoader.responseHandler != null) {
                this.requestLoader.responseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
            }
        } else if (this.requestLoader.jsonResponseHandler != null) {
            this.requestLoader.jsonResponseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
        } else if (this.requestLoader.responseHandler != null) {
            this.requestLoader.responseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
        }
    }
}
