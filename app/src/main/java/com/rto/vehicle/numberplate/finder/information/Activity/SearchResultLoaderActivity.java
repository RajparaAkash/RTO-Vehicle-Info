package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.database.SearchChallanHistoryTableAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ChallanDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.SearchChallanHistory;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.widget.CustomLoaderScreen;



public class SearchResultLoaderActivity extends AppCompatActivity {

    private String actionName;
    private ChallanDetailsResponse challanDetailsResponse;
    private CustomLoaderScreen customLoaderScreen;
    private String registrationNo;
    private String searchType;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_result_loader);
        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.searchType = getIntent().getStringExtra("SEARCH_TYPE");
        String stringExtra = getIntent().getStringExtra("ACTION");
        this.actionName = stringExtra;
        if (!Utils.isNullOrEmpty(stringExtra) && this.actionName.equalsIgnoreCase("SAVE")) {
            try {
                SearchChallanHistory searchChallanHistory = new SearchChallanHistory();
                searchChallanHistory.setRegistrationNo(this.registrationNo);
                searchChallanHistory.setSearchType(this.searchType);
                new SearchChallanHistoryTableAdapter(this).insertSearchChallanHistory(searchChallanHistory, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CustomLoaderScreen customLoaderScreen = findViewById(R.id.customLoader);
        this.customLoaderScreen = customLoaderScreen;
        customLoaderScreen.setVisibilityCustomLoaderScreen(0);
        this.customLoaderScreen.setCallback(() -> loadServerData());
    }

    public void loadServerData() {
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, false, "");
        } else {
            TaskHandler.newInstance().fetchChallanDetails(this, this.registrationNo, true, false, new TaskHandler.ResponseHandler<ChallanDetailsResponse>() {
                @Override
                public void onError(String str) {
                    SearchResultLoaderActivity searchResultLoaderActivity = SearchResultLoaderActivity.this;
                    searchResultLoaderActivity.showOrHideElements(true, true, false, searchResultLoaderActivity.getString(R.string.no_challan_info));
                }

                @Override
                public void onResponse(ChallanDetailsResponse challanDetailsResponse) {
                    String concat;
                    String str = SearchResultLoaderActivity.this.registrationNo + " INTERNAL_SERVER ";
                    if (challanDetailsResponse == null) {
                        concat = str.concat("500 ").concat("Null Response");
                    } else {
                        concat = str.concat(String.valueOf(challanDetailsResponse.getStatusCode())).concat(" ").concat(challanDetailsResponse.getStatusMessage());
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("VIEW_CHALLAN_DETAILS", concat);
                    bundle.putString("content_type", "CHALLAN_DETAILS");
                    if (challanDetailsResponse == null || challanDetailsResponse.getStatusCode() != 200) {
                        if (challanDetailsResponse != null && challanDetailsResponse.getStatusCode() > 200 && challanDetailsResponse.getStatusCode() < 500 && challanDetailsResponse.getStatusCode() != 404) {
                            SearchResultLoaderActivity.this.showOrHideElements(true, true, false, challanDetailsResponse.getStatusMessage());
                        } else if (challanDetailsResponse != null && challanDetailsResponse.getStatusCode() == 404) {
                            SearchResultLoaderActivity.this.showOrHideElements(true, true, false, "");
                        } else {
                            SearchResultLoaderActivity searchResultLoaderActivity = SearchResultLoaderActivity.this;
                            searchResultLoaderActivity.showOrHideElements(true, true, false, searchResultLoaderActivity.getString(R.string.no_challan_info));
                        }
                    } else if (challanDetailsResponse.getDetails() == null || challanDetailsResponse.getDetails().size() <= 0) {
                        SearchResultLoaderActivity.this.showOrHideElements(true, false, true, challanDetailsResponse.getStatusMessage());
                    } else {
                        SearchResultLoaderActivity.this.challanDetailsResponse = challanDetailsResponse;
                        SearchResultLoaderActivity.this.showOrHideElements(true, false, false, "");
                    }
                }
            });
        }
    }


    public void showOrHideElements(boolean z, boolean z2, boolean z3, String str) {
        CustomLoaderScreen customLoaderScreen = this.customLoaderScreen;
        if (customLoaderScreen != null && customLoaderScreen.isLoadingStarted()) {
            this.customLoaderScreen.finishLoading();
        }
        Intent intent = new Intent(this, ChallanDetailsActivity.class);
        intent.putExtra("REGISTRATION_NO", this.registrationNo);
        intent.putExtra("SEARCH_TYPE", this.searchType);
        intent.putExtra("ACTION", this.actionName);
        intent.putExtra("CHALLAN_DETAILS_DATA", this.challanDetailsResponse);
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
