package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.adapters.ChallanDetailsRecyclerViewAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ChallanDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ChallanDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.sdk.dierct.link.open.on.fail.sdk.AdClosedListener;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;
import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.util.List;


public class ChallanDetailsActivity extends AppCompatActivity {

    private String actionName;
    private CardView btnCheckVehicleDetails;
    private LinearLayout contentLayout;
    private CardView cvVehicleDetails;
    private String dataFetchStatus;
    private String dataFetchStatusMessage;
    private View emptyLayout;
    private RecyclerView recyclerView;
    private String registrationNo;
    private ChallanDetailsResponse response;
    private String searchType;
    private TextView txvChallanDisclaimer;
    private TextView txvErrorHeader;
    private TextView txvErrorMessage;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_challan_details);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.searchType = getIntent().getStringExtra("SEARCH_TYPE");
        this.actionName = getIntent().getStringExtra("ACTION");
        this.dataFetchStatus = getIntent().getStringExtra("data_fetch_status");
        this.dataFetchStatusMessage = getIntent().getStringExtra("data_fetch_status_message");
        this.response = (ChallanDetailsResponse) getIntent().getSerializableExtra("CHALLAN_DETAILS_DATA");

//        ((TextView) findViewById(R.id.action_bar_title)).setText(!Utils.isNullOrEmpty(this.registrationNo) ? this.registrationNo : "");
        this.emptyLayout = findViewById(R.id.emptyLayout);
        this.contentLayout = findViewById(R.id.contentLayout);
        this.txvChallanDisclaimer = findViewById(R.id.txvChallanDisclaimer);
        this.txvErrorHeader = findViewById(R.id.oopsTv);
        this.txvErrorMessage = findViewById(R.id.noResultTv);
        this.recyclerView = findViewById(R.id.recyclerView);
        this.cvVehicleDetails = findViewById(R.id.cvVehicleDetails);
        this.btnCheckVehicleDetails = findViewById(R.id.btnCheckVehicleDetails);
        managePageElements();
    }

    private void managePageElements() {
        if (Utils.isNullOrEmpty(this.dataFetchStatus)) {
            startSearchResultLoaderActivity();
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_internet")) {
            showOrHideElements(false, false, getString(R.string.app_internet_msg));
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_data_available")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            this.txvErrorHeader.setVisibility(View.GONE);
            this.txvChallanDisclaimer.setVisibility(View.VISIBLE);
        } else if (this.dataFetchStatus.equalsIgnoreCase("error")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
        } else {
            ChallanDetailsResponse challanDetailsResponse = this.response;
            if (challanDetailsResponse == null || challanDetailsResponse.getDetails() == null || this.response.getDetails().isEmpty()) {
                showOrHideElements(true, false, this.dataFetchStatusMessage);
                return;
            }
            handleResponse(this.response.getDetails());
            showOrHideElements(true, true, "");
        }
    }

    private void startSearchResultLoaderActivity() {
        this.dataFetchStatus = "";
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, getString(R.string.app_internet_msg));
            return;
        }
        Intent intent = new Intent(this, SearchResultLoaderActivity.class);
        intent.putExtra("REGISTRATION_NO", this.registrationNo);
        intent.putExtra("SEARCH_TYPE", this.searchType);
        intent.putExtra("ACTION", this.actionName);
        new InterAds().ShowfuullAd(this, new AdClosedListener() {
            @Override
            public void AdisClosed() {
                startActivity(intent);
                finish();
            }
        });
    }

    private void handleResponse(List<ChallanDetails> list) {
        if (list == null) {
            showOrHideElements(true, false, getString(R.string.error_message));
            return;
        }
        showOrHideCheckVehicleDetailsUI();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(new ChallanDetailsRecyclerViewAdapter(this, list));
        showOrHideElements(true, true, "");
    }

    private void showOrHideCheckVehicleDetailsUI() {
        if (Utils.isNullOrEmpty(this.searchType) || !this.searchType.equalsIgnoreCase("RC")) {
            this.cvVehicleDetails.setVisibility(View.GONE);
            return;
        }
        this.cvVehicleDetails.setVisibility(View.VISIBLE);
        this.btnCheckVehicleDetails.setOnClickListener(view -> {
            if (Utils.isNullOrEmpty(ChallanDetailsActivity.this.actionName) || !ChallanDetailsActivity.this.actionName.equalsIgnoreCase("SAVE")) {
                Intent intent = new Intent(ChallanDetailsActivity.this, SearchVehicleDetailsLoaderActivity.class);
                intent.putExtra("REGISTRATION_NO", ChallanDetailsActivity.this.registrationNo);
                intent.putExtra("ACTION", "SAVE");
                new InterAds().ShowfuullAd(this, new AdClosedListener() {
                    @Override
                    public void AdisClosed() {
                        ChallanDetailsActivity.this.startActivity(intent);
                    }
                });
            }
            ChallanDetailsActivity.this.onBackPressed();
        });
    }

    private void showOrHideElements(boolean z, boolean z2, String str) {
        int i = 0;
        if (this.txvErrorHeader.getVisibility() == View.GONE) {
            this.txvErrorHeader.setVisibility(View.VISIBLE);
        }
        if (str != null && !str.isEmpty()) {
            this.txvErrorMessage.setText(str);
        }
        this.emptyLayout.setVisibility((z && z2) ? View.GONE : View.VISIBLE);
        this.contentLayout.setVisibility((z && z2) ? View.VISIBLE : View.GONE);
        this.txvChallanDisclaimer.setVisibility(View.GONE);
    }

    public void shareTo3rdPartyApps() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", getString(R.string.share_challan_detail));
        startActivity(Intent.createChooser(intent, "Share with"));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
