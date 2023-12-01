package com.rto.vehicle.numberplate.finder.information.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.adapters.IRecyclerViewClickListener;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.adapters.IRecyclerViewLongClickListener;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.adapters.RecentSearchHistoryAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.database.SearchVehicleHistoryTableAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.database.VehicleDetailsTableAdapter;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.SearchVehicleHistory;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.GlobalTracker;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.ToastHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.widget.BaseBottomSheet;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchVehicleActivity extends AppCompatActivity implements IRecyclerViewClickListener, IRecyclerViewLongClickListener {

    private RecentSearchHistoryAdapter adapter;
    private TextView btnSearchDetails;
    private CardView cvRecentSearches;
    private EditText etFirst;
    private List<SearchVehicleHistory> historyList;
    private ImageView imageClear;
    private boolean isHistoryItemClicked = false;
    private String type;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_vehicle);


        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        findViewById(R.id.back_img).setOnClickListener(view -> onBackPressed());

        type = getIntent().getStringExtra("TYPE");
        TextView title_txt = findViewById(R.id.title_txt);
        String str = this.type;
        if (str == null || str.equalsIgnoreCase("RC")) {
            title_txt.setText(R.string.activity_search_vehicle);
        } else if (this.type.equalsIgnoreCase("INSURANCE")) {
            title_txt.setText(R.string.activity_search_insurance);
        } else if (this.type.equalsIgnoreCase("FINANCE")) {
            title_txt.setText(R.string.activity_search_finance);
        }
        MyApplication.SEARCH_VEHICLE_SCREEN_VIEW_COUNTER++;
        if (MyApplication.SEARCH_VEHICLE_SCREEN_VIEW_COUNTER != 2) {
            int i = MyApplication.SEARCH_VEHICLE_SCREEN_VIEW_COUNTER % 10;
        }
        EditText editText = findViewById(R.id.first_part);
        this.etFirst = editText;
        editText.setOnKeyListener((view, i2, keyEvent) -> {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            if (i2 != 66) {
                if (i2 == 67 && SearchVehicleActivity.this.etFirst.getText().length() == 0) {
                    SearchVehicleActivity.this.etFirst.requestFocus();
                }
                return false;
            } else if (SearchVehicleActivity.this.isValidRegistrationNo()) {
                ToastHelper.showToast(SearchVehicleActivity.this, "Please enter vehicle registration number", false);
                return true;
            } else {
                SearchVehicleActivity searchVehicleActivity = SearchVehicleActivity.this;
                searchVehicleActivity.btnSearchVehicleDetailsClickListener(searchVehicleActivity.etFirst.getText().toString());
                return true;
            }
        });
        applyEditTextFilters();
        this.imageClear = findViewById(R.id.iv_clear);
        this.btnSearchDetails = findViewById(R.id.btnSearchDetails);
        this.cvRecentSearches = findViewById(R.id.cvRecentSearches);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSearchHistories);
        this.historyList = new ArrayList();
        RecentSearchHistoryAdapter recentSearchHistoryAdapter = new RecentSearchHistoryAdapter(this, this, this);
        this.adapter = recentSearchHistoryAdapter;
        recentSearchHistoryAdapter.setHasStableIds(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.adapter);
        recyclerView.setNestedScrollingEnabled(false);
        this.etFirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (Utils.isNullOrEmpty(charSequence.toString()) || charSequence.toString().length() == 0) {
                    imageClear.setVisibility(View.GONE);
                    return;
                }
                imageClear.setVisibility(View.VISIBLE);
            }
        });
        this.imageClear.setOnClickListener(view -> SearchVehicleActivity.this.etFirst.setText(""));

        this.btnSearchDetails.setOnClickListener(view -> {
            if (SearchVehicleActivity.this.isValidRegistrationNo()) {
                ToastHelper.showToast(SearchVehicleActivity.this, "Please enter vehicle registration number", false);
                return;
            }
            SearchVehicleActivity searchVehicleActivity = SearchVehicleActivity.this;
            searchVehicleActivity.btnSearchVehicleDetailsClickListener(searchVehicleActivity.etFirst.getText().toString());
        });
    }

    private void applyEditTextFilters() {
        this.etFirst.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(11)});
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1002) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Bundle bundle = new Bundle();
                bundle.putString("TITLE", "Permission Denied");
                bundle.putString("MESSAGE", "Kindly allow audio permission from app settings to enable voice input feature.");
                BaseBottomSheet baseBottomSheet = new BaseBottomSheet();
                baseBottomSheet.setArguments(bundle);
                baseBottomSheet.show(getSupportFragmentManager(), "acknowledgement_bottom_sheet");
            }
        }
    }


    public boolean isValidRegistrationNo() {
        return Utils.isNullOrEmpty(this.etFirst.getText().toString());
    }

    public void btnSearchVehicleDetailsClickListener(String str) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.btnSearchDetails.getWindowToken(), 0);
        }
        if (!Utils.isNetworkConnected(this)) {
            ToastHelper.showToast(this, getString(R.string.app_internet_msg), false);
            return;
        }
        String formatString = Utils.formatString(str);
        if (Utils.isNullOrEmpty(formatString) || formatString.length() < 5) {
            ToastHelper.showToast(this, "Please enter the correct vehicle no!", true);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(GlobalTracker.EVENT_VEHICLE_NO, formatString);
        bundle.putString("content_type", GlobalTracker.BUTTON);
        Intent intent = new Intent(this, SearchVehicleDetailsLoaderActivity.class);
        intent.putExtra("REGISTRATION_NO", formatString);
        intent.putExtra("ACTION", "SAVE");
        intent.putExtra("TYPE", this.type);
        new InterAds().ShowfuullAd(this, () -> {
            startActivity(intent);
        });
    }

    public void showOrHideHistoryElements(boolean z) {
        this.cvRecentSearches.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    private void loadSearchHistories() {
        this.historyList = new SearchVehicleHistoryTableAdapter(this).getSearchVehicleHistoryList(true, 20);
        Bundle bundle = new Bundle();
        bundle.putString("item_name", "Vehicle search histories loaded");
        List<SearchVehicleHistory> list = this.historyList;
        if (list == null) {
            bundle.putString("item_list", "0");
        } else {
            bundle.putString("item_list", String.valueOf(list.size()));
        }
        bundle.putString("content_type", GlobalTracker.BUTTON);
        List<SearchVehicleHistory> list2 = this.historyList;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        showOrHideHistoryElements(true);
        this.adapter.updateListData(this.historyList);
    }

    @Override
    public void onItemSelected(int i) {
        SearchVehicleHistory searchVehicleHistory;
        List<SearchVehicleHistory> list = this.historyList;
        if (list == null || list.size() <= 0 || i < 0 || i >= this.historyList.size() || (searchVehicleHistory = this.historyList.get(i)) == null) {
            return;
        }
        this.isHistoryItemClicked = true;
        String str = this.type;
        if (str == null || (!str.equalsIgnoreCase("INSURANCE") && !this.type.equalsIgnoreCase("FINANCE"))) {
            btnSearchVehicleDetailsClickListener(searchVehicleHistory.getRegistrationNo());
        } else {
            this.etFirst.setText(searchVehicleHistory.getRegistrationNo());
        }
    }

    @Override
    public void onItemLongClick(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.txt_confirm_delete_search_history);
        builder.setPositiveButton(R.string.txt_yes, (dialogInterface, i2) -> {
            SearchVehicleHistory searchVehicleHistory;
            List list = SearchVehicleActivity.this.historyList;
            if (list == null || list.size() <= 0 || i >= SearchVehicleActivity.this.historyList.size() || (searchVehicleHistory = SearchVehicleActivity.this.historyList.get(i)) == null) {
                return;
            }
            try {
                new SearchVehicleHistoryTableAdapter(SearchVehicleActivity.this.getApplicationContext()).deleteHistoryById(String.valueOf(searchVehicleHistory.getId()), true);
                new VehicleDetailsTableAdapter(SearchVehicleActivity.this.getApplicationContext()).deleteHistoryByArgs(searchVehicleHistory.getRegistrationNo());
            } catch (Exception e) {
                e.printStackTrace();
            }
            SearchVehicleActivity.this.historyList.remove(i);
            SearchVehicleActivity.this.adapter.notifyDataSetChanged();
            SearchVehicleActivity.this.showOrHideHistoryElements(SearchVehicleActivity.this.historyList.size() > 0);
        });
        builder.setNegativeButton(R.string.txt_no, null).show();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        String str;
        super.onActivityResult(i, i2, intent);
        if (i != 1001 || i2 != -1 || intent == null || (stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS")) == null || stringArrayListExtra.isEmpty()) {
            return;
        }
        Iterator<String> it = stringArrayListExtra.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            str = Utils.formatString(it.next());
            if (!Utils.isNullOrEmpty(str)) {
                break;
            }
        }
        if (Utils.isNullOrEmpty(str)) {
            ToastHelper.showToast(this, "Invalid registration no.", true);
        } else {
            this.etFirst.setText(str);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.isHistoryItemClicked) {
            return;
        }
        loadSearchHistories();
    }
}
