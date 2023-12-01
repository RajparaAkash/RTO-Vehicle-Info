package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.ToastHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;
import com.squareup.picasso.Picasso;


public class InsuranceDetailsActivity extends AppCompatActivity {

    private String actionName;
    private TextView btnAction;
    private LinearLayout contentLayout;
    private LinearLayout cvInsuranceAge;
    private LinearLayout cvInsuranceCompany;
    private LinearLayout cvInsuranceUpto;
    private LinearLayout cvOwnership;
    private String dataFetchStatus;
    private String dataFetchStatusMessage;
    private View errorContainer;
    private ImageView errorImage;
    private String registrationNo;
    private VehicleDetailsResponse response;
    private TextView btnViewCompleteRC;
    private TextView txvInsuranceAgeValue;
    private TextView txvInsuranceCompanyValue;
    private TextView txvInsuranceUptoValue;
    private TextView txvOwnerNameValue;
    private TextView txvOwnershipValue;
    private TextView txvRegistrationNoValue;
    private TextView txvSubTitle;
    private TextView txvTitle;
    private String type;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_insurance_details);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        findViewById(R.id.back_img).setOnClickListener(v -> onBackPressed());

        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.actionName = getIntent().getStringExtra("ACTION");
        this.type = getIntent().getStringExtra("TYPE");
        this.dataFetchStatus = getIntent().getStringExtra("data_fetch_status");
        this.dataFetchStatusMessage = getIntent().getStringExtra("data_fetch_status_message");
        this.response = (VehicleDetailsResponse) getIntent().getSerializableExtra("VEHICLE_DETAILS_DATA");


        ((TextView) findViewById(R.id.header_title)).setText(getString(R.string.activity_insurance_details));

        findViewById(R.id.share_img).setOnClickListener(v -> shareTo3rdPartyApps());

        MyApplication.INSURANCE_DETAILS_SCREEN_VIEW_COUNTER++;

        this.errorContainer = findViewById(R.id.errorContainer);
        this.contentLayout = findViewById(R.id.contentLayout);
        this.errorImage = findViewById(R.id.errorImageView);
        this.txvTitle = findViewById(R.id.titleTextView);
        this.txvSubTitle = findViewById(R.id.subtitleTextView);
        this.btnAction = findViewById(R.id.actionButton);
        this.cvInsuranceUpto = findViewById(R.id.cvInsuranceUpto);
        this.cvInsuranceCompany = findViewById(R.id.cvInsuranceCompany);
        this.cvOwnership = findViewById(R.id.cvOwnership);
        this.cvInsuranceAge = findViewById(R.id.cvInsuranceAge);
        this.txvOwnerNameValue = findViewById(R.id.ownerNameValue);
        this.txvInsuranceUptoValue = findViewById(R.id.insuranceUptoValue);
        this.txvInsuranceCompanyValue = findViewById(R.id.insuranceCompanyValue);
        this.txvOwnershipValue = findViewById(R.id.ownershipValue);
        this.txvRegistrationNoValue = findViewById(R.id.registrationNoValue);
        this.txvInsuranceAgeValue = findViewById(R.id.insuranceAgeValue);
        this.btnViewCompleteRC = findViewById(R.id.btnViewCompleteRC);
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
                InsuranceDetailsActivity insuranceDetailsActivity = InsuranceDetailsActivity.this;
                insuranceDetailsActivity.startSearchVehicleDetailsLoaderActivity(insuranceDetailsActivity.type);
            });
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_data_available")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            this.errorImage.setImageResource(R.drawable.empty_folder);
            this.txvTitle.setText(getString(R.string.oops));
            this.txvSubTitle.setText(getString(R.string.no_result_found_insurance_info));
            this.btnAction.setText(getString(R.string.btn_go_back_search_again));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> onBackPressed());
        } else if (this.dataFetchStatus.equalsIgnoreCase("error")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            if (Utils.isNullOrEmpty(this.dataFetchStatusMessage)) {
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(getString(R.string.no_insurance_info));
            } else {
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(this.dataFetchStatusMessage);
            }
            this.errorImage.setImageResource(R.drawable.bug);
            this.btnAction.setText(getString(R.string.btn_try_again));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> {
                InsuranceDetailsActivity insuranceDetailsActivity = InsuranceDetailsActivity.this;
                insuranceDetailsActivity.startSearchVehicleDetailsLoaderActivity(insuranceDetailsActivity.type);
            });
        } else {
            VehicleDetailsResponse vehicleDetailsResponse = this.response;
            if (vehicleDetailsResponse == null || vehicleDetailsResponse.getDetails() == null) {
                showOrHideElements(true, false, this.dataFetchStatusMessage);
                this.errorImage.setImageResource(R.drawable.surprised);
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(getString(R.string.no_result_found_insurance_info));
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

    private void handleResponse(VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) {
            showOrHideElements(true, false, getString(R.string.error_message));
            return;
        }
        setupCoverUi(vehicleDetails);
        this.txvOwnerNameValue.setText(vehicleDetails.getOwnerName());
        this.txvRegistrationNoValue.setText(vehicleDetails.getRegistrationNo());
        String insuranceAge = Utils.getInsuranceAge(vehicleDetails.getInsuranceUpto());
        if (Utils.isNullOrEmpty(insuranceAge)) {
            this.cvInsuranceAge.setVisibility(View.GONE);
        } else {
            this.cvInsuranceAge.setVisibility(View.VISIBLE);
            this.txvInsuranceAgeValue.setText(insuranceAge);
        }
        if (Utils.isNullOrEmpty(vehicleDetails.getInsuranceUpto())) {
            this.cvInsuranceUpto.setVisibility(View.GONE);
        } else {
            this.cvInsuranceUpto.setVisibility(View.VISIBLE);
            this.txvInsuranceUptoValue.setText(vehicleDetails.getInsuranceUpto());
        }
        if (Utils.isNullOrEmpty(vehicleDetails.getInsuranceCompany())) {
            this.cvInsuranceCompany.setVisibility(View.GONE);
        } else {
            this.cvInsuranceCompany.setVisibility(View.VISIBLE);
            this.txvInsuranceCompanyValue.setText(vehicleDetails.getInsuranceCompany());
        }
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
        btnViewCompleteRC.setVisibility(View.VISIBLE);
        btnViewCompleteRC.setOnClickListener(view -> startSearchVehicleDetailsLoaderActivity("RC"));
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
        String format = String.format(getString(R.string.share_insurance_detail), details.getRegistrationNo(), details.getOwnerName());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", format);
        startActivity(Intent.createChooser(intent, "Share with"));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
