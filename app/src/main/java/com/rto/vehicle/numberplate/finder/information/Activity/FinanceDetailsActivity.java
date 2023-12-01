package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.ToastHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;
import com.squareup.picasso.Picasso;


public class FinanceDetailsActivity extends AppCompatActivity {
    Handler mHandler = new Handler();
    private String actionName;
    private TextView btnAction;
    private LinearLayout contentLayout;
    private LinearLayout cvFinancier;
    private LinearLayout cvOwnership;
    private String dataFetchStatus;
    private String dataFetchStatusMessage;
    private View errorContainer;
    private ImageView errorImage;
    private String registrationNo;
    private VehicleDetailsResponse response;
    private FrameLayout tvValuateCon;
    private TextView txvFinancierValue;
    private TextView txvOwnerNameValue;
    private TextView txvOwnershipValue;
    private TextView txvRegistrationNoValue;
    private TextView txvSubTitle;
    private TextView txvTitle;
    private String type;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_finance_details);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));
        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.actionName = getIntent().getStringExtra("ACTION");
        this.type = getIntent().getStringExtra("TYPE");
        this.dataFetchStatus = getIntent().getStringExtra("data_fetch_status");
        this.dataFetchStatusMessage = getIntent().getStringExtra("data_fetch_status_message");
        this.response = (VehicleDetailsResponse) getIntent().getSerializableExtra("VEHICLE_DETAILS_DATA");

        this.errorContainer = findViewById(R.id.errorContainer);
        this.contentLayout = findViewById(R.id.contentLayout);
        this.errorImage = findViewById(R.id.errorImageView);
        this.txvTitle = findViewById(R.id.titleTextView);
        this.txvSubTitle = findViewById(R.id.subtitleTextView);
        this.btnAction = findViewById(R.id.actionButton);
        this.cvOwnership = findViewById(R.id.cvOwnership);
        this.cvFinancier = findViewById(R.id.cvFinancier);
        this.txvOwnerNameValue = findViewById(R.id.ownerNameValue);
        this.txvOwnershipValue = findViewById(R.id.ownershipValue);
        this.txvRegistrationNoValue = findViewById(R.id.registrationNoValue);
        this.txvFinancierValue = findViewById(R.id.financierValue);
        this.tvValuateCon = findViewById(R.id.tvValuateCon);
        managePageElements();
    }

    private void managePageElements() {
        if (Utils.isNullOrEmpty(this.dataFetchStatus)) {
            startSearchVehicleDetailsLoaderActivity(this.type);
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_internet")) {
            showOrHideElements(false, false, getString(R.string.app_internet_msg));
            this.errorImage.setImageResource(R.drawable.wifi);
            this.txvTitle.setText(getString(R.string.txt_connection_error_title));
            this.txvSubTitle.setText(getString(R.string.no_network_message));
            this.btnAction.setText(getString(R.string.btn_retry));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> {
                FinanceDetailsActivity financeDetailsActivity = FinanceDetailsActivity.this;
                financeDetailsActivity.startSearchVehicleDetailsLoaderActivity(financeDetailsActivity.type);
            });
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_data_available")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            this.errorImage.setImageResource(R.drawable.empty_folder);
            this.txvTitle.setText(getString(R.string.oops));
            this.txvSubTitle.setText(getString(R.string.no_result_found_finance_info));
            this.btnAction.setText(getString(R.string.btn_go_back_search_again));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> onBackPressed());
        } else if (this.dataFetchStatus.equalsIgnoreCase("error")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            if (Utils.isNullOrEmpty(this.dataFetchStatusMessage)) {
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(getString(R.string.no_finance_info));
            } else {
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(this.dataFetchStatusMessage);
            }
            this.errorImage.setImageResource(R.drawable.bug);
            this.btnAction.setText(getString(R.string.btn_try_again));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> {
                FinanceDetailsActivity financeDetailsActivity = FinanceDetailsActivity.this;
                financeDetailsActivity.startSearchVehicleDetailsLoaderActivity(financeDetailsActivity.type);
            });
        } else {
            VehicleDetailsResponse vehicleDetailsResponse = this.response;
            if (vehicleDetailsResponse == null || vehicleDetailsResponse.getDetails() == null || Utils.isNullOrEmptyOrNA(this.response.getDetails().getFinancierName())) {
                showOrHideElements(true, false, this.dataFetchStatusMessage);
                this.errorImage.setImageResource(R.drawable.surprised);
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(getString(R.string.no_result_found_finance_info));
                this.btnAction.setText(getString(R.string.btn_go_back_search_again));
                this.btnAction.setVisibility(View.VISIBLE);
                this.btnAction.setOnClickListener(view -> onBackPressed());
                return;
            }
            handleResponse(this.response.getDetails());
            showOrHideElements(true, true, "");
        }
    }


    public void startSearchVehicleDetailsLoaderActivity(String str) {
        this.dataFetchStatus = "";
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, getString(R.string.app_internet_msg));
            return;
        }
        Intent intent = new Intent(this, SearchVehicleDetailsLoaderActivity.class);
        intent.putExtra("REGISTRATION_NO", this.registrationNo);
        intent.putExtra("ACTION", this.actionName);
        intent.putExtra("TYPE", str);
        new InterAds().ShowfuullAd(this, () -> {
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void handleResponse(VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) {
            showOrHideElements(true, false, getString(R.string.error_message));
            return;
        }
        setupCoverUi(vehicleDetails);
        this.txvOwnerNameValue.setText(vehicleDetails.getOwnerName());
        this.txvRegistrationNoValue.setText(vehicleDetails.getRegistrationNo());
        if (Utils.isNullOrEmpty(vehicleDetails.getOwnership())) {
            this.cvOwnership.setVisibility(View.GONE);
        } else {
            this.cvOwnership.setVisibility(View.VISIBLE);
            if (Utils.isNullOrEmpty(vehicleDetails.getOwnershipDesc())) {
                this.txvOwnershipValue.setText(vehicleDetails.getOwnership());
            } else {
                this.txvOwnershipValue.setText(vehicleDetails.getOwnershipDesc());
            }
        }
        if (Utils.isNullOrEmpty(vehicleDetails.getFinancierName())) {
            this.cvFinancier.setVisibility(View.GONE);
        } else {
            this.cvFinancier.setVisibility(View.VISIBLE);
            this.txvFinancierValue.setText(vehicleDetails.getFinancierName());
        }
        this.tvValuateCon.setVisibility(View.VISIBLE);
        findViewById(R.id.btnViewCompleteRC).setOnClickListener(view -> startSearchVehicleDetailsLoaderActivity("RC"));
        showOrHideElements(true, true, "");
    }

    private void setupCoverUi(VehicleDetails vehicleDetails) {
        ImageView imageView = findViewById(R.id.ivVehicleCoverImage);
        if (vehicleDetails.getVehicleInfo() == null) {
            imageView.setImageResource(vehicleDetails.identifyVehicleType());
        } else {
            Picasso.get().load(vehicleDetails.getVehicleInfo().getImageUrl()).placeholder(vehicleDetails.identifyVehicleType()).error(vehicleDetails.identifyVehicleType()).into(imageView);
        }
    }

    private void showOrHideElements(boolean z, boolean z2, String str) {
        int i = 8;
        this.errorContainer.setVisibility((z && z2) ? View.GONE : View.VISIBLE);
        LinearLayout linearLayout = this.contentLayout;
        if (z && z2) {
            i = 0;
        }
        linearLayout.setVisibility(i);
    }

    public void shareTo3rdPartyApps() {
        VehicleDetailsResponse vehicleDetailsResponse = this.response;
        if (vehicleDetailsResponse == null || vehicleDetailsResponse.getDetails() == null) {
            ToastHelper.showToast(this, getString(R.string.share_error), true);
            return;
        }
        VehicleDetails details = this.response.getDetails();
        String format = String.format(getString(R.string.share_finance_detail), details.getRegistrationNo(), details.getOwnerName());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", format);
        startActivity(Intent.createChooser(intent, "Share with"));
    }

}
