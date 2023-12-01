package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rto.vehicle.numberplate.finder.information.Adapter.BIKE_ExamAdapter;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOExamResultModel;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;
import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class TestExamActivity extends AppCompatActivity implements View.OnClickListener {

    public String ExamDate;
    public Boolean Result;
    public String ResultMessage;
    public String Score;
    public RecyclerView mRrRtoexamapps;
    public TextView mTxtTime;
    public int AttendQuestion = 0;
    public int CorrectAnswer = 0;
    public Boolean IsQuestionScroll = false;
    public int WrongAnswer = 0;
    Context context;
    SharedPreferences.Editor editor;
    Gson gson;
    Resources mResources;
    Handler myHandler;
    Runnable myRunnable;
    SharedPreferences pref;
    ArrayList<BIKE_RTOExamResultModel> DB_ExamResultArray = new ArrayList<>();
    int ExamQuestionNo = 0;
    int MaxQuestionNo = 0;
    ArrayList<BIKE_RTOQuestionModel> QuestionArray = new ArrayList<>();
    int QuestionNo = 0;
    int QuestionTime = 30;
    ArrayList<Integer> RandomNumber = new ArrayList<>();
    int TotalExamQuestions = 0;
    ArrayList<BIKE_RTOQuestionModel> TotalQuestionArray = new ArrayList<>();
    private RelativeLayout mCntresultback;
    private TextView mTxtCanswer;
    private TextView mTxtRAttempt;
    private TextView mTxtRCorrect;
    private TextView mTxtRScore;
    private TextView mTxtRWrong;
    private TextView mTxtResultmsg;
    private TextView mTxtScore;
    private TextView mTxtWrong;

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_exam);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        (findViewById(R.id.back_img)).setOnClickListener(view -> onBackPressed());

        Context locale = LocaleHelper.setLocale(this, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
        this.mRrRtoexamapps = findViewById(R.id.rr_rtoexamapps);
        this.mTxtScore = findViewById(R.id.txt_score);
        this.mTxtCanswer = findViewById(R.id.txt_canswer);
        this.mTxtWrong = findViewById(R.id.txt_wrong);
        this.mTxtTime = findViewById(R.id.txt_time);
        this.mCntresultback = findViewById(R.id.cntresultback);
        this.mTxtRScore = findViewById(R.id.txt_Rscore);
        this.mTxtResultmsg = findViewById(R.id.txt_Rresultmsg);
        this.mTxtRAttempt = findViewById(R.id.txt_Rattempt);
        this.mTxtRCorrect = findViewById(R.id.txt_Rcorrect);
        this.mTxtRWrong = findViewById(R.id.txt_Rwrong);

        this.mCntresultback.setVisibility(View.GONE);
        this.mCntresultback.setOnClickListener(this);
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
        this.ExamDate = new SimpleDateFormat(MyApplication.DATE_FORMATE).format(new Date());
        this.MaxQuestionNo = MyApplication.QuestionsArray.size();
        this.QuestionArray = new ArrayList<>();
        this.TotalQuestionArray = MyApplication.QuestionsArray;
        RefreshQuestions();
        while (this.RandomNumber.size() != 15) {
            int nextInt = new Random().nextInt(this.MaxQuestionNo);
            if (!this.RandomNumber.contains(nextInt)) {
                this.RandomNumber.add(nextInt);
            }
        }
        Collections.sort(this.RandomNumber);
        for (int i = 0; i < this.RandomNumber.size(); i++) {
            this.QuestionArray.add(this.TotalQuestionArray.get(this.RandomNumber.get(i)));
        }
        this.TotalExamQuestions = this.QuestionArray.size();
        AdapterFatch();
        this.myHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String string2;
                TestExamActivity.this.QuestionTime--;
                if (TestExamActivity.this.QuestionTime == 0) {
                    TestExamActivity.this.mTxtTime.setText(String.valueOf(TestExamActivity.this.QuestionTime));
                    TestExamActivity.this.WrongAnswer++;
                    TestExamActivity.this.QuestionArray.get(TestExamActivity.this.ExamQuestionNo).setAttemptMiss(true);
                    TestExamActivity.this.ExamQuestionNo++;
                    TestExamActivity.this.QuestionNo++;
                    TestExamActivity bIKE_ExamActivity = TestExamActivity.this;
                    bIKE_ExamActivity.Result = bIKE_ExamActivity.CorrectAnswer >= 11;
                    TestExamActivity bIKE_Exam2Activity = TestExamActivity.this;
                    if (bIKE_Exam2Activity.CorrectAnswer >= 11) {
                        string2 = TestExamActivity.this.mResources.getString(R.string.passmsg);
                    } else {
                        string2 = TestExamActivity.this.mResources.getString(R.string.failmsg);
                    }
                    bIKE_Exam2Activity.ResultMessage = string2;
                    TestExamActivity.this.ShowResultData();
                    if (TestExamActivity.this.WrongAnswer == 5) {
                        TestExamActivity.this.FinalResultStore();
                    } else if (TestExamActivity.this.CorrectAnswer == 11) {
                        TestExamActivity.this.FinalResultStore();
                    } else if (TestExamActivity.this.QuestionNo == TestExamActivity.this.TotalExamQuestions) {
                        TestExamActivity.this.FinalResultStore();
                    } else {
                        TestExamActivity.this.mRrRtoexamapps.getLayoutManager().scrollToPosition(TestExamActivity.this.QuestionNo);
                        TestExamActivity bIKE_Exam3Activity = TestExamActivity.this;
                        bIKE_Exam3Activity.QuestionTime = 30;
                        bIKE_Exam3Activity.mTxtTime.setText(String.valueOf(TestExamActivity.this.QuestionTime));
                        TestExamActivity.this.myHandler.removeCallbacksAndMessages(null);
                        TestExamActivity.this.myHandler.postDelayed(this, 1000L);
                        TestExamActivity.this.mRrRtoexamapps.getAdapter().notifyDataSetChanged();
                    }
                } else if (TestExamActivity.this.QuestionNo == TestExamActivity.this.TotalExamQuestions) {
                    TestExamActivity.this.myHandler.removeCallbacksAndMessages(null);
                } else {
                    TestExamActivity.this.mTxtTime.setText(String.valueOf(TestExamActivity.this.QuestionTime));
                    TestExamActivity.this.myHandler.postDelayed(this, 1000L);
                }
            }
        };
        this.myRunnable = runnable;
        this.myHandler.postDelayed(runnable, 1000L);
    }

    private void RefreshQuestions() {
        for (int i = 0; i < this.TotalQuestionArray.size(); i++) {
            this.TotalQuestionArray.get(i).setAttemptMiss(false);
            this.TotalQuestionArray.get(i).setAttempt(false);
            this.TotalQuestionArray.get(i).setSelctedAnswer("0");
        }
    }

    private void AdapterFatch() {
        BIKE_ExamAdapter bIKE_ExamAdapter = new BIKE_ExamAdapter(this, this.QuestionArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return TestExamActivity.this.IsQuestionScroll;
            }

            @Override
            public boolean canScrollVertically() {
                return TestExamActivity.this.IsQuestionScroll;
            }
        };
        this.mRrRtoexamapps.setItemAnimator(new DefaultItemAnimator());
        this.mRrRtoexamapps.setLayoutManager(linearLayoutManager);
        this.mRrRtoexamapps.setAdapter(bIKE_ExamAdapter);
        new PagerSnapHelper().attachToRecyclerView(this.mRrRtoexamapps);
    }

    public void FinalResultStore() {
        this.mCntresultback.setVisibility(View.VISIBLE);
        this.myHandler.removeCallbacksAndMessages(null);
        BIKE_RTOExamResultModel bIKE_RTOExamResultModel = new BIKE_RTOExamResultModel();
        bIKE_RTOExamResultModel.setAttendQuestion(String.valueOf(this.AttendQuestion));
        bIKE_RTOExamResultModel.setCorrectAnswer(String.valueOf(this.CorrectAnswer));
        bIKE_RTOExamResultModel.setExamDate(this.ExamDate);
        bIKE_RTOExamResultModel.setResult(this.Result);
        bIKE_RTOExamResultModel.setScore(this.Score);
        bIKE_RTOExamResultModel.setResultMessage(this.ResultMessage);
        bIKE_RTOExamResultModel.setWrongAnswer(String.valueOf(this.WrongAnswer));
        MyApplication.LastRTOExamResultModel = bIKE_RTOExamResultModel;
        this.DB_ExamResultArray.add(bIKE_RTOExamResultModel);
        this.editor.putString(MyApplication.RTOExamResult, this.gson.toJson(this.DB_ExamResultArray));
        this.editor.commit();
    }

    public void ShowResultData() {
        this.mTxtTime.setText(String.valueOf(this.QuestionTime));
        this.mTxtScore.setText(this.CorrectAnswer + "/15");
        this.mTxtCanswer.setText(String.valueOf(this.CorrectAnswer));
        this.mTxtWrong.setText(String.valueOf(this.WrongAnswer));
        String str = this.CorrectAnswer + "/15";
        this.Score = str;
        this.mTxtRScore.setText(str);
        this.mTxtResultmsg.setText(this.ResultMessage);
        this.mTxtRAttempt.setText(this.mResources.getString(R.string.attended) + " " + this.AttendQuestion);
        this.mTxtRCorrect.setText(this.mResources.getString(R.string.correct) + " " + this.CorrectAnswer);
        this.mTxtRWrong.setText(this.mResources.getString(R.string.wrong) + " " + this.WrongAnswer);
    }

    public void NextQuestion() {
        new Handler().postDelayed(() -> {
            String string;
            TestExamActivity.this.QuestionNo++;
            TestExamActivity bIKE_ExamActivity = TestExamActivity.this;
            bIKE_ExamActivity.Result = bIKE_ExamActivity.CorrectAnswer >= 11;
            TestExamActivity bIKE_Exam2Activity = TestExamActivity.this;
            if (bIKE_Exam2Activity.CorrectAnswer >= 11) {
                string = TestExamActivity.this.mResources.getString(R.string.passmsg);
            } else {
                string = TestExamActivity.this.mResources.getString(R.string.failmsg);
            }
            bIKE_Exam2Activity.ResultMessage = string;
            TestExamActivity.this.ShowResultData();
            if (TestExamActivity.this.WrongAnswer == 5) {
                TestExamActivity.this.FinalResultStore();
            } else if (TestExamActivity.this.CorrectAnswer == 11) {
                TestExamActivity.this.FinalResultStore();
            } else if (TestExamActivity.this.QuestionNo == TestExamActivity.this.TotalExamQuestions) {
                TestExamActivity.this.FinalResultStore();
            } else {
                TestExamActivity.this.mRrRtoexamapps.getLayoutManager().scrollToPosition(TestExamActivity.this.QuestionNo);
                TestExamActivity.this.QuestionTime = 30;
                TestExamActivity.this.mTxtTime.setText(String.valueOf(TestExamActivity.this.QuestionTime));
                TestExamActivity.this.myHandler.removeCallbacksAndMessages(null);
                TestExamActivity.this.myHandler.postDelayed(TestExamActivity.this.myRunnable, 1000L);
                TestExamActivity.this.mRrRtoexamapps.getAdapter().notifyDataSetChanged();
            }
        }, 1000L);
    }

    @Override
    public void onBackPressed() {
        this.myHandler.removeCallbacksAndMessages(null);
        finish();
    }
}
