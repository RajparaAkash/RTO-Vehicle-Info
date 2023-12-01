package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.GlobalTracker;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.ToastHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.PreferencesHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;
import com.squareup.picasso.Picasso;


public class VehicleDetailsActivity extends AppCompatActivity {

    private String actionName;
    private TextView btnAction;
    private CardView btnCheckChallan;
    private CardView btnCheckHiddenFinancierValue;
    private CardView btnCheckHiddenInsuranceCompanyValue;
    private CardView btnVehicleDetails;
    private LinearLayout contentLayout;
    private LinearLayout cvBodyTypeDesc;
    private LinearLayout cvChallan;
    private LinearLayout cvFinancier;
    private LinearLayout cvFitnessUpto;
    private LinearLayout cvInsuranceCompany;
    private LinearLayout cvInsuranceUpto;
    private LinearLayout cvManufactureMonthYear;
    private LinearLayout cvOwnerPopularity;
    private LinearLayout cvOwnership;
    private LinearLayout cvPuccUpto;
    private LinearLayout cvRCStatus;
    private LinearLayout cvRoadTaxPaidUpto;
    private LinearLayout cvSeatCapacity;
    private LinearLayout cvUnloadWeight;
    private LinearLayout cvVehicleAge;
    private LinearLayout cvVehicleColor;
    private LinearLayout cvVehicleInfo;
    private String dataFetchStatus;
    private String dataFetchStatusMessage;
    private View errorContainer;
    private ImageView errorImage;
    private RelativeLayout financierValueHiddenContainer;
    private RelativeLayout insuranceCompanyValueHiddenContainer;
    private ImageView ivVehicleImage;

    private String registrationNo;
    private VehicleDetailsResponse response;
    private TextView txvBodyTypeDescValue;
    private TextView txvChassisNoValue;
    private TextView txvEngineNoValue;
    private TextView txvFinancierValue;
    private TextView txvFinancierValueHidden;
    private TextView txvFitnessUptoValue;
    private TextView txvFuelNormsValue;
    private TextView txvFuelTypeValue;
    private TextView txvInsuranceCompanyValue;
    private TextView txvInsuranceCompanyValueHidden;
    private TextView txvInsuranceUptoValue;
    private TextView txvMakerModelValue;
    private TextView txvManufactureMonthYearValue;
    private TextView txvOwnerNameValue;
    private TextView txvOwnerPopularityValue;
    private TextView txvOwnershipValue;
    private TextView txvPuccUptoValue;
    private TextView txvRCStatusValue;
    private TextView txvRegAuthorityValue;
    private TextView txvRegDateValue;
    private TextView txvRegistrationNoValue;
    private TextView txvRoadTaxPaidUptoValue;
    private TextView txvSeatCapacityValue;
    private TextView txvSubTitle;
    private TextView txvTitle;
    private TextView txvUnloadWeightValue;
    private TextView txvVehicleAgeValue;
    private TextView txvVehicleBrandName;
    private TextView txvVehicleClassValue;
    private TextView txvVehicleColorValue;
    private TextView txvVehicleModelName;
    private String type;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_vehicle_details);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        findViewById(R.id.back_img).setOnClickListener(v -> onBackPressed());

        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.actionName = getIntent().getStringExtra("ACTION");
        this.type = getIntent().getStringExtra("TYPE");
        this.dataFetchStatus = getIntent().getStringExtra("data_fetch_status");
        this.dataFetchStatusMessage = getIntent().getStringExtra("data_fetch_status_message");
        this.response = (VehicleDetailsResponse) getIntent().getSerializableExtra("VEHICLE_DETAILS_DATA");

        ((TextView) findViewById(R.id.header_title)).setText(!Utils.isNullOrEmpty(this.registrationNo) ? this.registrationNo : "");

        findViewById(R.id.share_img).setOnClickListener(v -> shareTo3rdPartyApps());

        MyApplication.VEHICLE_DETAILS_SCREEN_VIEW_COUNTER++;
        MyApplication.VEHICLE_DETAILS_CLICK_TO_SEE_SCREEN_VIEW_COUNTER++;

        this.errorContainer = findViewById(R.id.errorContainer);
        this.contentLayout = findViewById(R.id.contentLayout);
        this.errorImage = findViewById(R.id.errorImageView);
        this.txvTitle = findViewById(R.id.titleTextView);
        this.txvSubTitle = findViewById(R.id.subtitleTextView);
        this.btnAction = findViewById(R.id.actionButton);
        this.cvInsuranceUpto = findViewById(R.id.cvInsuranceUpto);
        this.cvInsuranceCompany = findViewById(R.id.cvInsuranceCompany);
        this.cvOwnership = findViewById(R.id.cvOwnership);
        this.cvFinancier = findViewById(R.id.cvFinancier);
        this.cvVehicleColor = findViewById(R.id.cvVehicleColor);
        this.cvSeatCapacity = findViewById(R.id.cvSeatCapacity);
        this.cvVehicleAge = findViewById(R.id.cvVehicleAge);
        this.cvVehicleInfo = findViewById(R.id.cvVehicleInfo);
        this.cvRoadTaxPaidUpto = findViewById(R.id.cvRoadTaxPaidUpto);
        this.cvOwnerPopularity = findViewById(R.id.cvOwnerPopularity);
        this.btnVehicleDetails = findViewById(R.id.btnVehicleDetails);
        this.cvChallan = findViewById(R.id.cvChallan);
        this.btnCheckChallan = findViewById(R.id.btnCheckChallan);
        this.cvPuccUpto = findViewById(R.id.cvPuccUpto);
        this.cvUnloadWeight = findViewById(R.id.cvUnloadWeight);
        this.cvBodyTypeDesc = findViewById(R.id.cvBodyTypeDesc);
        this.cvManufactureMonthYear = findViewById(R.id.cvManufactureMonthYear);
        this.cvRCStatus = findViewById(R.id.cvRCStatus);
        this.cvFitnessUpto = findViewById(R.id.cvFitnessUpto);
        this.txvOwnerNameValue = findViewById(R.id.ownerNameValue);
        this.txvMakerModelValue = findViewById(R.id.makerModelValue);
        this.txvRegDateValue = findViewById(R.id.regDateValue);
        this.txvFuelTypeValue = findViewById(R.id.fuelTypeValue);
        this.txvVehicleClassValue = findViewById(R.id.vehicleClassValue);
        this.txvRegAuthorityValue = findViewById(R.id.regAuthorityValue);
        this.txvEngineNoValue = findViewById(R.id.engineNoValue);
        this.txvChassisNoValue = findViewById(R.id.chassisNoValue);
        this.txvInsuranceUptoValue = findViewById(R.id.insuranceUptoValue);
        this.txvInsuranceCompanyValue = findViewById(R.id.insuranceCompanyValue);
        this.txvFitnessUptoValue = findViewById(R.id.fitnessUptoValue);
        this.txvFuelNormsValue = findViewById(R.id.fuelNormsValue);
        this.txvOwnershipValue = findViewById(R.id.ownershipValue);
        this.txvVehicleColorValue = findViewById(R.id.vehicleColorValue);
        this.txvSeatCapacityValue = findViewById(R.id.seatCapacityValue);
        this.txvRegistrationNoValue = findViewById(R.id.registrationNoValue);
        this.txvVehicleAgeValue = findViewById(R.id.vehicleAgeValue);
        this.txvVehicleModelName = findViewById(R.id.txvVehicleModelName);
        this.txvVehicleBrandName = findViewById(R.id.txvVehicleBrandName);
        this.txvRoadTaxPaidUptoValue = findViewById(R.id.roadTaxPaidUptoValue);
        this.txvOwnerPopularityValue = findViewById(R.id.ownerPopularityValue);
        this.txvFinancierValue = findViewById(R.id.financierValue);
        this.txvPuccUptoValue = findViewById(R.id.puccUptoValue);
        this.txvUnloadWeightValue = findViewById(R.id.unloadWeightValue);
        this.txvBodyTypeDescValue = findViewById(R.id.bodyTypeDescValue);
        this.txvManufactureMonthYearValue = findViewById(R.id.manufactureMonthYearValue);
        this.txvRCStatusValue = findViewById(R.id.rcStatusValue);
        this.financierValueHiddenContainer = findViewById(R.id.financierValueHiddenContainer);
        this.insuranceCompanyValueHiddenContainer = findViewById(R.id.insuranceCompanyValueHiddenContainer);
        this.txvFinancierValueHidden = findViewById(R.id.financierValueHidden);
        this.txvInsuranceCompanyValueHidden = findViewById(R.id.insuranceCompanyValueHidden);
        this.btnCheckHiddenFinancierValue = findViewById(R.id.btnCheckHiddenFinancierValue);
        this.btnCheckHiddenInsuranceCompanyValue = findViewById(R.id.btnCheckHiddenInsuranceCompanyValue);
        this.ivVehicleImage = findViewById(R.id.ivVehicleImage);
        managePageElements();
    }

    private void managePageElements() {
        if (Utils.isNullOrEmpty(this.dataFetchStatus)) {
            startSearchVehicleDetailsLoaderActivity();
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_internet")) {
            showOrHideElements(false, false, getString(R.string.app_internet_msg));
            this.errorImage.setImageResource(R.drawable.wifi);
            this.txvTitle.setText(getString(R.string.txt_connection_error_title));
            this.txvSubTitle.setText(getString(R.string.no_network_message));
            this.btnAction.setText(getString(R.string.btn_retry));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> startSearchVehicleDetailsLoaderActivity());
        } else if (this.dataFetchStatus.equalsIgnoreCase("no_data_available")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            this.errorImage.setImageResource(R.drawable.empty_folder);
            this.txvTitle.setText(getString(R.string.oops));
            this.txvSubTitle.setText(getString(R.string.no_result_found_info));
            this.btnAction.setText(getString(R.string.btn_go_back_search_again));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> onBackPressed());
        } else if (this.dataFetchStatus.equalsIgnoreCase("error")) {
            showOrHideElements(true, false, this.dataFetchStatusMessage);
            if (Utils.isNullOrEmpty(this.dataFetchStatusMessage)) {
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(getString(R.string.no_info));
            } else {
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(this.dataFetchStatusMessage);
            }
            this.errorImage.setImageResource(R.drawable.bug);
            this.btnAction.setText(getString(R.string.btn_try_again));
            this.btnAction.setVisibility(View.VISIBLE);
            this.btnAction.setOnClickListener(view -> startSearchVehicleDetailsLoaderActivity());
        } else {
            VehicleDetailsResponse vehicleDetailsResponse = this.response;
            if (vehicleDetailsResponse == null || vehicleDetailsResponse.getDetails() == null) {
                showOrHideElements(true, false, this.dataFetchStatusMessage);
                this.errorImage.setImageResource(R.drawable.surprised);
                this.txvTitle.setText(getString(R.string.oops));
                this.txvSubTitle.setText(getString(R.string.no_result_found_info));
                this.btnAction.setText(getString(R.string.btn_go_back_search_again));
                this.btnAction.setVisibility(View.VISIBLE);
                this.btnAction.setOnClickListener(view -> onBackPressed());
                return;
            }
            handleResponse(this.response.getDetails());
            showOrHideElements(true, true, "");
        }
    }


    public void startSearchVehicleDetailsLoaderActivity() {
        this.dataFetchStatus = "";
        if (!Utils.isNetworkConnected(this)) {
            showOrHideElements(false, false, getString(R.string.app_internet_msg));
            return;
        }
        Intent intent = new Intent(this, SearchVehicleDetailsLoaderActivity.class);
        intent.putExtra("REGISTRATION_NO", this.registrationNo);
        intent.putExtra("ACTION", this.actionName);
        intent.putExtra("TYPE", this.type);
        new InterAds().ShowfuullAd(this, () -> {
            startActivity(intent);
            finish();
        });
    }

    private void handleResponse(final VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) {
            showOrHideElements(true, false, getString(R.string.error_message));
            return;
        }
        setupCoverUi(vehicleDetails);
        this.txvOwnerNameValue.setText(vehicleDetails.getOwnerName());
        this.txvMakerModelValue.setText(vehicleDetails.getMakerModel());
        this.txvRegDateValue.setText(vehicleDetails.getRegistrationDate());
        this.txvRegistrationNoValue.setText(vehicleDetails.getRegistrationNo());
        this.txvFuelTypeValue.setText(vehicleDetails.getFuelType());
        this.txvVehicleClassValue.setText(vehicleDetails.getVehicleClass());
        this.txvRegAuthorityValue.setText(vehicleDetails.getRegistrationAuthority());
        this.txvEngineNoValue.setText(vehicleDetails.getEngineNo());
        this.txvChassisNoValue.setText(vehicleDetails.getChassisNo());
        String vehicleAge = Utils.getVehicleAge(vehicleDetails.getRegistrationDate());
        if (Utils.isNullOrEmpty(vehicleAge)) {
            this.cvVehicleAge.setVisibility(View.GONE);
        } else {
            this.cvVehicleAge.setVisibility(View.VISIBLE);
            this.txvVehicleAgeValue.setText(vehicleAge);
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getInsuranceUpto())) {
            this.cvInsuranceUpto.setVisibility(View.GONE);
        } else {
            this.cvInsuranceUpto.setVisibility(View.VISIBLE);
            this.txvInsuranceUptoValue.setText(vehicleDetails.getInsuranceUpto());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getInsuranceCompany())) {
            this.cvInsuranceCompany.setVisibility(View.GONE);
        } else {
            this.cvInsuranceCompany.setVisibility(View.VISIBLE);
            this.txvInsuranceCompanyValue.setText(vehicleDetails.getInsuranceCompany());
            this.txvInsuranceCompanyValueHidden.setText(Utils.hideString(vehicleDetails.getInsuranceCompany()));
            if (!PreferencesHelper.isClickToSeeAvailable() || (MyApplication.VEHICLE_DETAILS_CLICK_TO_SEE_SCREEN_VIEW_COUNTER != 1 && MyApplication.VEHICLE_DETAILS_CLICK_TO_SEE_SCREEN_VIEW_COUNTER % PreferencesHelper.getClickToSeeAdIndex() != 0)) {
                this.insuranceCompanyValueHiddenContainer.setVisibility(View.GONE);
                this.txvInsuranceCompanyValue.setVisibility(View.VISIBLE);
            } else {
                this.insuranceCompanyValueHiddenContainer.setVisibility(View.VISIBLE);
                this.txvInsuranceCompanyValue.setVisibility(View.GONE);
                this.btnCheckHiddenInsuranceCompanyValue.setOnClickListener(view -> updateInsuranceValue());
            }
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getFitnessUpto())) {
            this.cvFitnessUpto.setVisibility(View.GONE);
        } else {
            this.cvFitnessUpto.setVisibility(View.VISIBLE);
            this.txvFitnessUptoValue.setText(vehicleDetails.getFitnessUpto());
        }
        this.txvFuelNormsValue.setText(vehicleDetails.getFuelNorms());
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
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getFinancierName())) {
            this.cvFinancier.setVisibility(View.GONE);
        } else {
            this.cvFinancier.setVisibility(View.VISIBLE);
            this.txvFinancierValue.setText(vehicleDetails.getFinancierName());
            this.txvFinancierValueHidden.setText(Utils.hideString(vehicleDetails.getInsuranceCompany()));
            if (!PreferencesHelper.isClickToSeeAvailable() || (MyApplication.VEHICLE_DETAILS_CLICK_TO_SEE_SCREEN_VIEW_COUNTER != 1 && MyApplication.VEHICLE_DETAILS_CLICK_TO_SEE_SCREEN_VIEW_COUNTER % PreferencesHelper.getClickToSeeAdIndex() != 0)) {
                this.financierValueHiddenContainer.setVisibility(View.GONE);
                this.txvFinancierValue.setVisibility(View.VISIBLE);
            } else {
                this.financierValueHiddenContainer.setVisibility(View.VISIBLE);
                this.txvFinancierValue.setVisibility(View.GONE);
                this.btnCheckHiddenFinancierValue.setOnClickListener(view -> updateFinanceValue());
            }
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getVehicleColor())) {
            this.cvVehicleColor.setVisibility(View.GONE);
        } else {
            this.cvVehicleColor.setVisibility(View.VISIBLE);
            this.txvVehicleColorValue.setText(vehicleDetails.getVehicleColor());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getSeatCapacity())) {
            this.cvSeatCapacity.setVisibility(View.GONE);
        } else {
            this.cvSeatCapacity.setVisibility(View.VISIBLE);
            this.txvSeatCapacityValue.setText(vehicleDetails.getSeatCapacity());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getRoadTaxPaidUpto())) {
            this.cvRoadTaxPaidUpto.setVisibility(View.GONE);
        } else {
            this.cvRoadTaxPaidUpto.setVisibility(View.VISIBLE);
            this.txvRoadTaxPaidUptoValue.setText(vehicleDetails.getRoadTaxPaidUpto());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getPucUpto())) {
            this.cvPuccUpto.setVisibility(View.GONE);
        } else {
            this.cvPuccUpto.setVisibility(View.VISIBLE);
            this.txvPuccUptoValue.setText(vehicleDetails.getPucUpto());
        }
        if (vehicleDetails.getSearchCount() <= 1) {
            this.cvOwnerPopularity.setVisibility(View.GONE);
        } else {
            this.cvOwnerPopularity.setVisibility(View.VISIBLE);
            this.txvOwnerPopularityValue.setText(getString(R.string.format_views, vehicleDetails.getSearchCount()));
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getUnloadWeight())) {
            this.cvUnloadWeight.setVisibility(View.GONE);
        } else {
            this.cvUnloadWeight.setVisibility(View.VISIBLE);
            this.txvUnloadWeightValue.setText(vehicleDetails.getUnloadWeight());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getBodyTypeDesc())) {
            this.cvBodyTypeDesc.setVisibility(View.GONE);
        } else {
            this.cvBodyTypeDesc.setVisibility(View.VISIBLE);
            this.txvBodyTypeDescValue.setText(vehicleDetails.getBodyTypeDesc());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getManufactureMonthYear())) {
            this.cvManufactureMonthYear.setVisibility(View.GONE);
        } else {
            this.cvManufactureMonthYear.setVisibility(View.VISIBLE);
            this.txvManufactureMonthYearValue.setText(vehicleDetails.getManufactureMonthYear());
        }
        if (Utils.isNullOrEmptyOrNA(vehicleDetails.getRcStatus())) {
            this.cvRCStatus.setVisibility(View.GONE);
        } else {
            this.cvRCStatus.setVisibility(View.VISIBLE);
            this.txvRCStatusValue.setText(vehicleDetails.getRcStatus());
        }
        if (vehicleDetails.getVehicleInfo() == null || Utils.isNullOrEmpty(vehicleDetails.getVehicleType())) {
            this.cvVehicleInfo.setVisibility(View.GONE);
        } else {
            this.cvVehicleInfo.setVisibility(View.VISIBLE);
            int i = vehicleDetails.getVehicleType().equalsIgnoreCase("car_models") ? R.drawable.ic_default_car : R.drawable.ic_default_bike;
            try {
                Picasso.get().load(vehicleDetails.getVehicleInfo().getImageUrl()).error(i).placeholder(i).into(this.ivVehicleImage);
            } catch (Exception unused) {
                Picasso.get().load(i).error(i).placeholder(i).into(this.ivVehicleImage);
            }
            this.txvVehicleModelName.setText(vehicleDetails.getVehicleInfo().getModelName());
            this.txvVehicleBrandName.setText(getString(R.string.format_brand_name, vehicleDetails.getVehicleInfo().getBrandName()));
            this.btnVehicleDetails.setVisibility(View.GONE);
            this.btnVehicleDetails.setOnClickListener(view -> {
                if (vehicleDetails.getVehicleType().equalsIgnoreCase("car_models")) {
                    Bundle bundle = new Bundle();
                    bundle.putString(GlobalTracker.CAR, "CAR_MODEL " + vehicleDetails.getVehicleInfo().getModelName());
                    bundle.putString("content_type", GlobalTracker.CAR_MODEL);
                } else if (vehicleDetails.getVehicleType().equalsIgnoreCase("bike_models")) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(GlobalTracker.BIKE, "BIKE_MODEL " + vehicleDetails.getVehicleInfo().getModelName());
                    bundle2.putString("content_type", GlobalTracker.BIKE_MODEL);
                }
            });
        }
        if (PreferencesHelper.isShowSearchChallanButton()) {
            this.cvChallan.setVisibility(View.VISIBLE);
            this.btnCheckChallan.setOnClickListener(view -> {
                Intent intent = new Intent(VehicleDetailsActivity.this, SearchResultLoaderActivity.class);
                intent.putExtra("REGISTRATION_NO", VehicleDetailsActivity.this.registrationNo);
                intent.putExtra("SEARCH_TYPE", Utils.getSearchTypeByNo(VehicleDetailsActivity.this.registrationNo));
                intent.putExtra("ACTION", "SAVE");
                new InterAds().ShowfuullAd(this, () -> {
                    startActivity(intent);
                });
            });
        } else {
            this.cvChallan.setVisibility(View.GONE);
        }
        showOrHideElements(true, true, "");
    }


    public void updateInsuranceValue() {
        this.insuranceCompanyValueHiddenContainer.setVisibility(View.GONE);
        this.txvInsuranceCompanyValue.setVisibility(View.VISIBLE);
    }


    public void updateFinanceValue() {
        this.financierValueHiddenContainer.setVisibility(View.GONE);
        this.txvFinancierValue.setVisibility(View.VISIBLE);
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
        String format = String.format(getString(R.string.share_vehicle_detail), details.getRegistrationNo(), details.getOwnerName(), details.getRegistrationAuthority(), details.getRegistrationDate(), details.getMakerModel(), details.getVehicleClass(), details.getFuelType(), details.getChassisNo(), details.getEngineNo());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", format);
        startActivity(Intent.createChooser(intent, "Share with"));
    }


    public void shareToWhatsApp() {
        VehicleDetailsResponse vehicleDetailsResponse = this.response;
        if (vehicleDetailsResponse == null || vehicleDetailsResponse.getDetails() == null) {
            ToastHelper.showToast(this, getString(R.string.share_error), true);
            return;
        }
        VehicleDetails details = this.response.getDetails();
        String format = String.format(getString(R.string.share_vehicle_detail), details.getRegistrationNo(), details.getOwnerName(), details.getRegistrationAuthority(), details.getRegistrationDate(), details.getMakerModel(), details.getVehicleClass(), details.getFuelType(), details.getChassisNo(), details.getEngineNo());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.putExtra("android.intent.extra.TEXT", format);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastHelper.showToast(this, "WhatsApp not installed on your device.", true);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
