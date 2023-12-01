package com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.City;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.FuelCity;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.FuelCityResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.FuelPricesResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.InitDataResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.PopularCarBikeResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.RTOInformationResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.RTOQuestionResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.TrafficSignalResponse;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeBrandsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarBrandsResponse;

import com.rto.vehicle.numberplate.finder.information.R;


public class PreferencesHelper {
    public static boolean isFirstLaunch() {
        return getSharedPreference().getBoolean(GlobalContext.getInstance().getContext().getString(R.string.first_launch), true);
    }

    public static void setFirstLaunch(boolean z) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putBoolean(GlobalContext.getInstance().getContext().getString(R.string.first_launch), z);
        edit.apply();
    }

    public static int getErrorCount() {
        return getSharedPreference().getInt(GlobalContext.getInstance().getContext().getString(R.string.error_count), 0);
    }

    public static void setErrorCount(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt(GlobalContext.getInstance().getContext().getString(R.string.error_count), i);
        edit.apply();
    }

    public static String getKeyToken() {
        return getSharedPreference().getString("key_token", "");
    }

    public static void setKeyToken(String str) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("key_token", str);
        edit.apply();
    }

    public static int getVehicleRatingCount() {
        return getSharedPreference().getInt(GlobalContext.getInstance().getContext().getString(R.string.vehicle_rating_count), 0);
    }

    public static void setVehicleRatingCount(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt(GlobalContext.getInstance().getContext().getString(R.string.vehicle_rating_count), i);
        edit.apply();
    }

    public static int getVehicleInsuranceRatingCount() {
        return getSharedPreference().getInt("vehicle_insurance_rating_count", 0);
    }

    public static void setVehicleInsuranceRatingCount(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt("vehicle_insurance_rating_count", i);
        edit.apply();
    }

    public static int getVehicleFinanceRatingCount() {
        return getSharedPreference().getInt("vehicle_finance_rating_count", 0);
    }

    public static void setVehicleFinanceRatingCount(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt("vehicle_finance_rating_count", i);
        edit.apply();
    }

    public static int getChallanRatingCount() {
        return getSharedPreference().getInt(GlobalContext.getInstance().getContext().getString(R.string.challan_rating_count), 0);
    }

    public static void setChallanRatingCount(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt(GlobalContext.getInstance().getContext().getString(R.string.challan_rating_count), i);
        edit.apply();
    }

    public static int getExitHomePageRatingDialogCount() {
        return getSharedPreference().getInt(GlobalContext.getInstance().getContext().getString(R.string.exit_home_page_rating_count), 0);
    }

    public static void setExitHomePageRatingDialogCount(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt(GlobalContext.getInstance().getContext().getString(R.string.exit_home_page_rating_count), i);
        edit.apply();
    }

    public static City getSelectedCity() {
        return new Gson().fromJson(getSharedPreference().getString("CITY", null), City.class);
    }

    public static void setSelectedCity(City city) {
        String json = new Gson().toJson(city);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("CITY", json);
        edit.apply();
    }

    public static FuelCity getSelectedFuelCity() {
        return new Gson().fromJson(getSharedPreference().getString("FUEL_CITY", null), FuelCity.class);
    }

    public static void setSelectedFuelCity(FuelCity fuelCity) {
        String json = new Gson().toJson(fuelCity);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("FUEL_CITY", json);
        edit.apply();
    }

    public static long getLastConnectionTimeToServer() {
        return getSharedPreference().getLong("TIME_TO_SERVER", 0L);
    }

    public static void setLastConnectionTimeToServer(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("TIME_TO_SERVER", j);
        edit.apply();
    }

    public static FuelPricesResponse getFuelPricesResponse() {
        return new Gson().fromJson(getSharedPreference().getString("FUEL_PRICES_RESPONSE", null), FuelPricesResponse.class);
    }

    public static void setFuelPricesResponse(FuelPricesResponse fuelPricesResponse) {
        String json = new Gson().toJson(fuelPricesResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("FUEL_PRICES_RESPONSE", json);
        edit.apply();
    }

    public static long getFuelCitySearchingLastFetchTime() {
        return getSharedPreference().getLong("FUEL_CITY_SEARCHING_TIME_TO_SERVER", 0L);
    }

    public static void setFuelCitySearchingLastFetchTime(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("FUEL_CITY_SEARCHING_TIME_TO_SERVER", j);
        edit.apply();
    }

    public static FuelCityResponse getFuelCityResponse() {
        return new Gson().fromJson(getSharedPreference().getString("FUEL_CITY_RESPONSE", null), FuelCityResponse.class);
    }

    public static void setFuelCityResponse(FuelCityResponse fuelCityResponse) {
        String json = new Gson().toJson(fuelCityResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("FUEL_CITY_RESPONSE", json);
        edit.apply();
    }

    public static long getRtoQuestionLastFetchTime() {
        return getSharedPreference().getLong("RTO_QUESTION_TIME_TO_SERVER", 0L);
    }

    public static void setRtoQuestionLastFetchTime(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("RTO_QUESTION_TIME_TO_SERVER", j);
        edit.apply();
    }

    public static RTOQuestionResponse getRtoQuestionResponse() {
        return new Gson().fromJson(getSharedPreference().getString("RTO_QUESTION_RESPONSE", null), RTOQuestionResponse.class);
    }

    public static void setRtoQuestionResponse(RTOQuestionResponse rTOQuestionResponse) {
        String json = new Gson().toJson(rTOQuestionResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("RTO_QUESTION_RESPONSE", json);
        edit.apply();
    }

    public static long getTrafficSignalLastFetchTime() {
        return getSharedPreference().getLong("TRAFFIC_SIGNAL_FETCH_TIME_TO_SERVER", 0L);
    }

    public static void setTrafficSignalLastFetchTime(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("TRAFFIC_SIGNAL_FETCH_TIME_TO_SERVER", j);
        edit.apply();
    }

    public static TrafficSignalResponse getTrafficSignalResponse() {
        return new Gson().fromJson(getSharedPreference().getString("TRAFFIC_SIGNAL_RESPONSE", null), TrafficSignalResponse.class);
    }

    public static void setTrafficSignalResponse(TrafficSignalResponse trafficSignalResponse) {
        String json = new Gson().toJson(trafficSignalResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("TRAFFIC_SIGNAL_RESPONSE", json);
        edit.apply();
    }

    public static long getRtoInformationLastFetchTime() {
        return getSharedPreference().getLong("RTO_INFORMATION_TIME_TO_SERVER", 0L);
    }

    public static void setRtoInformationLastFetchTime(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("RTO_INFORMATION_TIME_TO_SERVER", j);
        edit.apply();
    }

    public static RTOInformationResponse getRtoInformationResponse() {
        return new Gson().fromJson(getSharedPreference().getString("RTO_INFORMATION_RESPONSE", null), RTOInformationResponse.class);
    }

    public static void setRtoInformationResponse(RTOInformationResponse rTOInformationResponse) {
        String json = new Gson().toJson(rTOInformationResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("RTO_INFORMATION_RESPONSE", json);
        edit.apply();
    }

    public static long getChooseCarBrandLastFetchTime() {
        return getSharedPreference().getLong("CAR_BRAND_FETCH_TIME_TO_SERVER", 0L);
    }

    public static void setChooseCarBrandLastFetchTime(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("CAR_BRAND_FETCH_TIME_TO_SERVER", j);
        edit.apply();
    }

    public static CarBrandsResponse getCarBrandsResponse() {
        return new Gson().fromJson(getSharedPreference().getString("CAR_BRAND_RESPONSE", null), CarBrandsResponse.class);
    }

    public static void setCarBrandsResponse(CarBrandsResponse carBrandsResponse) {
        String json = new Gson().toJson(carBrandsResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("CAR_BRAND_RESPONSE", json);
        edit.apply();
    }

    public static long getChooseBikeBrandLastFetchTime() {
        return getSharedPreference().getLong("BIKE_BRAND_FETCH_TIME_TO_SERVER", 0L);
    }

    public static void setChooseBikeBrandLastFetchTime(long j) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putLong("BIKE_BRAND_FETCH_TIME_TO_SERVER", j);
        edit.apply();
    }

    public static BikeBrandsResponse getBikeBrandsResponse() {
        return new Gson().fromJson(getSharedPreference().getString("BIKE_BRAND_RESPONSE", null), BikeBrandsResponse.class);
    }

    public static void setBikeBrandsResponse(BikeBrandsResponse bikeBrandsResponse) {
        String json = new Gson().toJson(bikeBrandsResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("BIKE_BRAND_RESPONSE", json);
        edit.apply();
    }

    public static PopularCarBikeResponse getPopularCarBikeResponse() {
        return new Gson().fromJson(getSharedPreference().getString("POPULAR_CAR_BIKE_RESPONSE", null), PopularCarBikeResponse.class);
    }

    public static void setPopularCarBikeResponse(PopularCarBikeResponse popularCarBikeResponse) {
        String json = new Gson().toJson(popularCarBikeResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("POPULAR_CAR_BIKE_RESPONSE", json);
        edit.apply();
    }

    public static InitDataResponse getInitDataResponse() {
        return new Gson().fromJson(getSharedPreference().getString("INIT_DATA_RESPONSE", null), InitDataResponse.class);
    }

    public static void setInitDataResponse(InitDataResponse initDataResponse) {
        String json = new Gson().toJson(initDataResponse);
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("INIT_DATA_RESPONSE", json);
        edit.apply();
    }

    public static boolean isShowSearchChallanButton() {
        return getSharedPreference().getBoolean("KEY_SHOW_SEARCH_CHALLAN_BUTTON", false);
    }

    public static void setShowSearchChallanButton(boolean z) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putBoolean("KEY_SHOW_SEARCH_CHALLAN_BUTTON", z);
        edit.apply();
    }

    public static boolean isClickToSeeAvailable() {
        return getSharedPreference().getBoolean("KEY_CLICK_TO_SEE", false);
    }

    public static void setClickToSeeAvailable(boolean z) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putBoolean("KEY_CLICK_TO_SEE", z);
        edit.apply();
    }

    public static int getClickToSeeAdIndex() {
        return getSharedPreference().getInt("KEY_CLICK_TO_SEE_AD_INDEX", 5);
    }

    public static void setClickToSeeAdIndex(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt("KEY_CLICK_TO_SEE_AD_INDEX", i);
        edit.apply();
    }

    public static int getVehicleDetailsAdIndex() {
        return getSharedPreference().getInt("KEY_VEHICLE_DETAILS_AD_INDEX", 3);
    }

    public static void setVehicleDetailsAdIndex(int i) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putInt("KEY_VEHICLE_DETAILS_AD_INDEX", i);
        edit.apply();
    }

    public static String getKeyDeviceId() {
        return getSharedPreference().getString("KEY_DEVICE_ID", "");
    }

    public static void setKeyDeviceId(String str) {
        SharedPreferences.Editor edit = getSharedPreference().edit();
        edit.putString("KEY_DEVICE_ID", str);
        edit.apply();
    }

    private static SharedPreferences getSharedPreference() {
        return PreferenceManager.getDefaultSharedPreferences(GlobalContext.getInstance().getContext());
    }
}
