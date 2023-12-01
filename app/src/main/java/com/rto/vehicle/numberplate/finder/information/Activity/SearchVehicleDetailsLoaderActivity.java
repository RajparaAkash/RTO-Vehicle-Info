package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.database.SearchVehicleHistoryTableAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.database.VehicleDetailsTableAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ExternalVehicleDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ExternalVehicleDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.SearchVehicleHistory;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetailsDatabaseModel;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.ScraperAsyncTask;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.ResponseJuicer;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.VehicleDetailsLogger;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.GlobalReferenceEngine;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.widget.CustomLoaderScreen;


import org.json.JSONObject;


public class SearchVehicleDetailsLoaderActivity extends AppCompatActivity {

    private String actionName;
    private CustomLoaderScreen customLoaderScreen;

    private String registrationNo;
    private String type;
    private VehicleDetailsResponse vehicleDetailsResponse;
    private int counter = 0;
    private int externalCounter = 0;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_result_loader);

        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.actionName = getIntent().getStringExtra("ACTION");
        this.type = getIntent().getStringExtra("TYPE");
        if (!Utils.isNullOrEmpty(this.actionName) && this.actionName.equalsIgnoreCase("SAVE")) {
            try {
                SearchVehicleHistory searchVehicleHistory = new SearchVehicleHistory();
                searchVehicleHistory.setRegistrationNo(this.registrationNo);
                new SearchVehicleHistoryTableAdapter(this).insertSearchVehicleHistory(searchVehicleHistory, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.customLoaderScreen = findViewById(R.id.customLoader);
        managePageElements();
    }

    private void managePageElements() {
        String str = this.type;
        if (str == null || str.equalsIgnoreCase("RC")) {
            try {
                VehicleDetailsDatabaseModel readVehicleDetails = new VehicleDetailsTableAdapter(this).readVehicleDetails(this.registrationNo);
                if (readVehicleDetails != null && readVehicleDetails.getData() != null) {
                    this.vehicleDetailsResponse = new Gson().fromJson(readVehicleDetails.getData(), VehicleDetailsResponse.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("VIEW_VEHICLE_DETAILS", this.registrationNo + " LOCAL_DB");
                    bundle.putString("content_type", "VEHICLE_DETAILS");
                    showOrHideElements(true, false, false, "");
                    return;
                }
                startCustomLoader();
                return;
            } catch (Exception unused) {
                if (this.vehicleDetailsResponse == null) {
                    startCustomLoader();
                } else {
                    showOrHideElements(true, false, false, "");
                }
                return;
            }
        }
        startCustomLoader();
    }

    private void startCustomLoader() {
        this.customLoaderScreen.setVisibilityCustomLoaderScreen(0);
        if (!Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessPoint) && GlobalReferenceEngine.dataAccessPoint.equalsIgnoreCase("WEB")) {
            this.customLoaderScreen.setCallback(() -> loadWebServerData(true));
        } else if (!Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessPoint) && GlobalReferenceEngine.dataAccessPoint.equalsIgnoreCase("EXTERNAL")) {
            this.customLoaderScreen.setCallback(() -> loadExternalServerData(false));
        } else if (Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessPoint) || !GlobalReferenceEngine.dataAccessPoint.equalsIgnoreCase("LOCAL") || Utils.isNullOrEmpty(GlobalReferenceEngine.localSourceInitUrl) || Utils.isNullOrEmpty(GlobalReferenceEngine.localSourceFinalUrl) || Utils.isNullOrEmpty(GlobalReferenceEngine.localSourceHostUrl) || Utils.isNullOrEmpty(GlobalReferenceEngine.localSourceField1) || Utils.isNullOrEmpty(GlobalReferenceEngine.localSourceField2)) {
            this.customLoaderScreen.setCallback(() -> loadLocalSourceData());
        } else {
            this.customLoaderScreen.setCallback(() -> loadLocalSourceData());
        }
    }


    public void loadWebServerData(boolean z) {
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, false, "");
        } else {
            TaskHandler.newInstance().fetchVehicleDetails(this, this.registrationNo, false, z, false, new TaskHandler.ResponseHandler<JSONObject>() {
                @Override
                public void onError(String str) {
                    SearchVehicleDetailsLoaderActivity.this.loadLocalSourceData();
                }

                @Override
                public void onResponse(JSONObject jSONObject) {
                    SearchVehicleDetailsLoaderActivity.this.manipulateJsonResponse(jSONObject);
                }
            });
        }
    }


    public void manipulateJsonResponse(JSONObject jSONObject) {
        String concat;
        try {
            this.vehicleDetailsResponse = new Gson().fromJson(jSONObject.toString(), VehicleDetailsResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = this.registrationNo + " INTERNAL_SERVER ";
        VehicleDetailsResponse vehicleDetailsResponse = this.vehicleDetailsResponse;
        if (vehicleDetailsResponse == null) {
            concat = str.concat("500 ").concat("Null Response");
        } else {
            concat = str.concat(String.valueOf(vehicleDetailsResponse.getStatusCode())).concat(" ").concat(this.vehicleDetailsResponse.getStatusMessage());
        }
        Bundle bundle = new Bundle();
        bundle.putString("VIEW_VEHICLE_DETAILS", concat);
        bundle.putString("content_type", "VEHICLE_DETAILS");
        VehicleDetailsResponse vehicleDetailsResponse2 = this.vehicleDetailsResponse;
        if (vehicleDetailsResponse2 == null || vehicleDetailsResponse2.getStatusCode() != 200) {
            VehicleDetailsResponse vehicleDetailsResponse3 = this.vehicleDetailsResponse;
            if (vehicleDetailsResponse3 == null || vehicleDetailsResponse3.getStatusCode() <= 200 || this.vehicleDetailsResponse.getStatusCode() >= 500 || this.vehicleDetailsResponse.getStatusCode() == 404) {
                VehicleDetailsResponse vehicleDetailsResponse4 = this.vehicleDetailsResponse;
                if (vehicleDetailsResponse4 == null || vehicleDetailsResponse4.getStatusCode() != 404) {
                    loadLocalSourceData();
                    return;
                } else if (this.vehicleDetailsResponse.isExtra() && this.externalCounter < 1 && !Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessUrl) && !Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessKey) && !Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessParams)) {
                    loadExternalServerData(true);
                    return;
                } else if (this.externalCounter == 1) {
                    loadLocalSourceData();
                    return;
                } else {
                    showOrHideElements(true, false, true, "");
                    return;
                }
            }
            loadLocalSourceData();
        } else if (this.vehicleDetailsResponse.getDetails() == null) {
            showOrHideElements(true, false, true, "");
        } else {
            try {
                new VehicleDetailsTableAdapter(this).saveVehicleDetails(this.registrationNo, jSONObject.toString());
                SearchVehicleHistoryTableAdapter searchVehicleHistoryTableAdapter = new SearchVehicleHistoryTableAdapter(this);
                SearchVehicleHistory searchVehicleHistoryByDetails = searchVehicleHistoryTableAdapter.getSearchVehicleHistoryByDetails(this.registrationNo, true);
                if (searchVehicleHistoryByDetails == null) {
                    searchVehicleHistoryByDetails = new SearchVehicleHistory();
                    searchVehicleHistoryByDetails.setRegistrationNo(this.registrationNo);
                }
                searchVehicleHistoryByDetails.setName(this.vehicleDetailsResponse.getDetails().getOwnerName());
                searchVehicleHistoryTableAdapter.insertSearchVehicleHistory(searchVehicleHistoryByDetails, true);
                showOrHideElements(true, false, false, "");
            } catch (Exception unused) {
                loadLocalSourceData();
            }
        }
    }


    public void manipulateExternalResponse(ExternalVehicleDetailsResponse externalVehicleDetailsResponse) {
        String concat;
        ExternalVehicleDetails result = externalVehicleDetailsResponse.getResult();
        String str = this.registrationNo + " EXTERNAL_SERVER ";
        if (result == null) {
            concat = str.concat("500 ").concat("Null Response");
        } else if (result.isEmptyResponse()) {
            concat = str.concat("404 ").concat("Vehicle Info Not Found");
        } else {
            concat = str.concat(String.valueOf(externalVehicleDetailsResponse.getStatusCode())).concat(" ").concat(externalVehicleDetailsResponse.getStatusMessage());
        }
        Bundle bundle = new Bundle();
        bundle.putString("VIEW_VEHICLE_DETAILS", concat);
        bundle.putString("content_type", "VEHICLE_DETAILS");
        if (externalVehicleDetailsResponse.getStatusCode() != 200 || result == null) {
            if (externalVehicleDetailsResponse.getStatusCode() > 200 && externalVehicleDetailsResponse.getStatusCode() < 500 && externalVehicleDetailsResponse.getStatusCode() != 404) {
                loadLocalSourceData();
            } else if (externalVehicleDetailsResponse.getStatusCode() == 404) {
                showOrHideElements(true, false, true, "");
            } else {
                loadLocalSourceData();
            }
        } else if (result.isEmptyResponse()) {
            showOrHideElements(true, false, true, "");
        } else {
            try {
                VehicleDetailsLogger.logVehicleDetails(this, result.convertInto());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                VehicleDetailsTableAdapter vehicleDetailsTableAdapter = new VehicleDetailsTableAdapter(this);
                this.vehicleDetailsResponse = new VehicleDetailsResponse(200, "Success", result.convertInto());
                vehicleDetailsTableAdapter.saveVehicleDetails(this.registrationNo, new Gson().toJson(this.vehicleDetailsResponse, VehicleDetailsResponse.class));
                SearchVehicleHistoryTableAdapter searchVehicleHistoryTableAdapter = new SearchVehicleHistoryTableAdapter(this);
                SearchVehicleHistory searchVehicleHistoryByDetails = searchVehicleHistoryTableAdapter.getSearchVehicleHistoryByDetails(this.registrationNo, true);
                if (searchVehicleHistoryByDetails == null) {
                    searchVehicleHistoryByDetails = new SearchVehicleHistory();
                    searchVehicleHistoryByDetails.setRegistrationNo(this.registrationNo);
                }
                searchVehicleHistoryByDetails.setName(result.getOwnerName());
                searchVehicleHistoryTableAdapter.insertSearchVehicleHistory(searchVehicleHistoryByDetails, true);
                showOrHideElements(true, false, false, "");
            } catch (Exception unused) {
                loadLocalSourceData();
            }
        }
    }


    public void loadExternalServerData(boolean z) {
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, false, "");
            return;
        }
        if (z) {
            this.externalCounter++;
        }
        TaskHandler.newInstance().fetchVehicleDetails(this, this.registrationNo, GlobalReferenceEngine.dataAccessParams.split(" "), false, new TaskHandler.ResponseHandler<String>() {
            @Override
            public void onError(String str) {
                SearchVehicleDetailsLoaderActivity.this.loadLocalSourceData();
            }

            @Override
            public void onResponse(String str) {
                if (Utils.isNullOrEmpty(GlobalReferenceEngine.dataAccessRouter) || GlobalReferenceEngine.dataAccessRouter.equalsIgnoreCase("EXTERNAL")) {
                    SearchVehicleDetailsLoaderActivity.this.fetchVehicleDetails(str);
                    return;
                }
                ExternalVehicleDetailsResponse responseJuice = ResponseJuicer.responseJuice(str);
                if (responseJuice != null) {
                    SearchVehicleDetailsLoaderActivity.this.manipulateExternalResponse(responseJuice);
                } else {
                    SearchVehicleDetailsLoaderActivity.this.loadLocalSourceData();
                }
            }
        });
    }


    public void fetchVehicleDetails(String str) {
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, false, "");
        } else {
            TaskHandler.newInstance().fetchVehicleDetails(this, this.registrationNo, str, false, new TaskHandler.ResponseHandler<JSONObject>() {
                @Override
                public void onError(String str2) {
                    ExternalVehicleDetailsResponse responseJuice = ResponseJuicer.responseJuice(str2);
                    if (responseJuice != null) {
                        SearchVehicleDetailsLoaderActivity.this.manipulateExternalResponse(responseJuice);
                    } else {
                        SearchVehicleDetailsLoaderActivity.this.loadLocalSourceData();
                    }
                }

                @Override
                public void onResponse(JSONObject jSONObject) {
                    SearchVehicleDetailsLoaderActivity.this.manipulateJsonResponse(jSONObject);
                }
            });
        }
    }

    public void loadLocalSourceData() {
        this.counter++;
        String[] splitRegistrationNo = Utils.splitRegistrationNo(this.registrationNo);
        new ScraperAsyncTask(this, "", new ScraperAsyncTask.IResponseCallback() {
            @Override
            public void onNotFound() {
                SearchVehicleDetailsLoaderActivity.this.runOnUiThread(() -> SearchVehicleDetailsLoaderActivity.this.showOrHideElements(true, false, true, ""));
            }

            @Override
            public void onError(final String str) {
                SearchVehicleDetailsLoaderActivity.this.runOnUiThread(() -> {
                    if (SearchVehicleDetailsLoaderActivity.this.counter >= 2 || SearchVehicleDetailsLoaderActivity.this.externalCounter > 0) {
                        SearchVehicleDetailsLoaderActivity.this.showOrHideElements(true, true, false, str);
                    } else {
                        SearchVehicleDetailsLoaderActivity.this.loadWebServerData(false);
                    }
                });
            }

            @Override
            public void onResponse(final VehicleDetails vehicleDetails) {
                SearchVehicleDetailsLoaderActivity.this.runOnUiThread(() -> manipulateResponse(vehicleDetails));
            }
        }).execute(splitRegistrationNo[0], splitRegistrationNo[1]);
    }


    public void manipulateResponse(VehicleDetails vehicleDetails) {
        String concat;
        String str = this.registrationNo + " LOCAL_SOURCE ";
        if (vehicleDetails == null) {
            concat = str.concat("500 ").concat("Null Response");
        } else if (vehicleDetails.isEmptyResponse()) {
            concat = str.concat("404 ").concat("Vehicle Info Not Found");
        } else {
            concat = str.concat("200 ").concat("Success");
        }
        Bundle bundle = new Bundle();
        bundle.putString("VIEW_VEHICLE_DETAILS", concat);
        bundle.putString("content_type", "VEHICLE_DETAILS");
        if (vehicleDetails == null || vehicleDetails.isEmptyResponse()) {
            showOrHideElements(true, false, true, "");
            return;
        }
        try {
            VehicleDetailsLogger.logVehicleDetails(this, vehicleDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.vehicleDetailsResponse = new VehicleDetailsResponse(200, "Success", vehicleDetails);
            new VehicleDetailsTableAdapter(this).saveVehicleDetails(this.registrationNo, new Gson().toJson(this.vehicleDetailsResponse, VehicleDetailsResponse.class));
            SearchVehicleHistoryTableAdapter searchVehicleHistoryTableAdapter = new SearchVehicleHistoryTableAdapter(this);
            SearchVehicleHistory searchVehicleHistoryByDetails = searchVehicleHistoryTableAdapter.getSearchVehicleHistoryByDetails(this.registrationNo, true);
            if (searchVehicleHistoryByDetails == null) {
                searchVehicleHistoryByDetails = new SearchVehicleHistory();
                searchVehicleHistoryByDetails.setRegistrationNo(this.registrationNo);
            }
            searchVehicleHistoryByDetails.setName(vehicleDetails.getOwnerName());
            searchVehicleHistoryTableAdapter.insertSearchVehicleHistory(searchVehicleHistoryByDetails, true);
            showOrHideElements(true, false, false, "");
        } catch (Exception unused) {
            loadWebServerData(false);
        }
    }


    public void showOrHideElements(boolean z, boolean z2, boolean z3, String str) {
        final Intent intent;
        CustomLoaderScreen customLoaderScreen = this.customLoaderScreen;
        if (customLoaderScreen != null && customLoaderScreen.isLoadingStarted()) {
            this.customLoaderScreen.finishLoading();
        }
        if (!Utils.isNullOrEmpty(this.type) && this.type.equalsIgnoreCase("INSURANCE")) {
            intent = new Intent(this, InsuranceDetailsActivity.class);
        } else if (Utils.isNullOrEmpty(this.type) || !this.type.equalsIgnoreCase("FINANCE")) {
            intent = new Intent(this, VehicleDetailsActivity.class);
        } else {
            intent = new Intent(this, FinanceDetailsActivity.class);
        }
        intent.putExtra("REGISTRATION_NO", this.registrationNo);
        intent.putExtra("ACTION", this.actionName);
        intent.putExtra("TYPE", this.type);
        intent.putExtra("VEHICLE_DETAILS_DATA", this.vehicleDetailsResponse);
        if (!z) {
            intent.putExtra("data_fetch_status", "no_internet");
        } else if (z3) {
            intent.putExtra("data_fetch_status", "no_data_available");
            intent.putExtra("data_fetch_status_message", str);
        } else if (z2) {
            intent.putExtra("data_fetch_status", "error");
            intent.putExtra("data_fetch_status_message", str);
        } else {
            intent.putExtra("data_fetch_status", "data_available");
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
