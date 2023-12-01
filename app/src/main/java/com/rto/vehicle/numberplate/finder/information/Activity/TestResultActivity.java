package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rto.vehicle.numberplate.finder.information.Adapter.BIKE_ResultAdapter;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOExamResultModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.util.ArrayList;

public class TestResultActivity extends AppCompatActivity {

    Context context;
    SharedPreferences.Editor editor;
    Gson gson;
    Resources mResources;
    SharedPreferences pref;
    private ArrayList<BIKE_RTOExamResultModel> DB_ExamResultArray;
    private RecyclerView mRrRtoresults;
    private LinearLayout mTxtNoresult;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_result);

        /*Native_AdPreload1.getInstance(this)
                .Native_Banner_Ads(this, findViewById(R.id.native_banner));*/

        (findViewById(R.id.back_img)).setOnClickListener(view -> onBackPressed());

        this.mTxtNoresult = findViewById(R.id.txt_noresult);
        this.mRrRtoresults = findViewById(R.id.rr_rtoresults);
        Context locale = LocaleHelper.setLocale(this, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MyApplication.DefaultPref, 0);
        this.pref = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.gson = new Gson();
        this.DB_ExamResultArray = new ArrayList<>();
        String string = this.pref.getString(MyApplication.RTOExamResult, null);
        if (string != null) {
            this.DB_ExamResultArray = this.gson.fromJson(string, new TypeToken<ArrayList<BIKE_RTOExamResultModel>>() {
            }.getType());
        }
        AdapterFatch();
    }

    public void DeleteItemAndSave(int i) {
        this.DB_ExamResultArray.remove(i);
        this.editor.putString(MyApplication.RTOExamResult, this.gson.toJson(this.DB_ExamResultArray));
        this.editor.commit();
        this.mRrRtoresults.getAdapter().notifyItemRemoved(i);
        AdapterFatch();
    }

    public void DeleteAllAndSave() {
        this.DB_ExamResultArray.clear();
        this.editor.putString(MyApplication.RTOExamResult, this.gson.toJson(this.DB_ExamResultArray));
        this.editor.commit();
    }

    private void AdapterFatch() {
        ArrayList<BIKE_RTOExamResultModel> arrayList = this.DB_ExamResultArray;
        if (arrayList == null || arrayList.size() == 0) {
            this.mTxtNoresult.setVisibility(View.VISIBLE);
        } else {
            this.mTxtNoresult.setVisibility(View.GONE);
        }
        BIKE_ResultAdapter bIKE_ResultAdapter = new BIKE_ResultAdapter(this, this.DB_ExamResultArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.mRrRtoresults.setItemAnimator(new DefaultItemAnimator());
        this.mRrRtoresults.setLayoutManager(linearLayoutManager);
        this.mRrRtoresults.setAdapter(bIKE_ResultAdapter);
        bIKE_ResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
