package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.Adapter.BIKE_PreparationAdapter;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.util.ArrayList;

public class TestPaperActivity extends AppCompatActivity implements View.OnClickListener {

    public Boolean IsQuestionScroll = false;
    Context context;
    Resources mResources;
    LinearLayout next;
    LinearLayout preview;
    int MaxQuestionNo = 0;
    ArrayList<BIKE_RTOQuestionModel> QuestionArray = new ArrayList<>();
    int QuestionNo = 0;
    private RecyclerView mRrRtopreparation;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_paper);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        (findViewById(R.id.back_img)).setOnClickListener(view -> onBackPressed());

        Context locale = LocaleHelper.setLocale(this, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
        this.mRrRtopreparation = findViewById(R.id.rr_rtopreparation);

        this.next = findViewById(R.id.next);
        this.preview = findViewById(R.id.preview);

        this.next.setOnClickListener(this);
        this.preview.setOnClickListener(this);
        ArrayList<BIKE_RTOQuestionModel> arrayList = MyApplication.QuestionsArray;
        this.QuestionArray = arrayList;
        this.MaxQuestionNo = arrayList.size();
        AdapterFatch();
    }

    private void AdapterFatch() {

        BIKE_PreparationAdapter bIKE_PreparationAdapter = new BIKE_PreparationAdapter(this, this.QuestionArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return TestPaperActivity.this.IsQuestionScroll;
            }

            @Override
            public boolean canScrollVertically() {
                return TestPaperActivity.this.IsQuestionScroll;
            }
        };
        this.mRrRtopreparation.setItemAnimator(new DefaultItemAnimator());
        this.mRrRtopreparation.setLayoutManager(linearLayoutManager);
        this.mRrRtopreparation.setAdapter(bIKE_PreparationAdapter);
        new PagerSnapHelper().attachToRecyclerView(this.mRrRtopreparation);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == this.next) {
            next_method();
        } else if (view == this.preview) {
            preview_method();
        }
    }

    private void next_method() {
        int i = this.QuestionNo;
        if (i != this.MaxQuestionNo) {
            this.QuestionNo = i + 1;
        }
        this.mRrRtopreparation.getLayoutManager().scrollToPosition(this.QuestionNo);
    }

    private void preview_method() {
        int i = this.QuestionNo;
        if (i != 0) {
            this.QuestionNo = i - 1;
        }
        this.mRrRtopreparation.getLayoutManager().scrollToPosition(this.QuestionNo);
    }
}
