package com.rto.vehicle.numberplate.finder.information.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.Activity.TestExamActivity;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class BIKE_ExamAdapter extends RecyclerView.Adapter<BIKE_ExamAdapter.moreapp_view_holder> {

    Context context;
    Resources mResources;
    ArrayList<BIKE_RTOQuestionModel> moArrayList;
    Context moContext;

    public BIKE_ExamAdapter(Context context, ArrayList<BIKE_RTOQuestionModel> arrayList) {
        this.moContext = context;
        this.moArrayList = arrayList;
        Context locale = LocaleHelper.setLocale(context, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
    }

    @NonNull
    @Override
    public moreapp_view_holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new moreapp_view_holder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemview_rto_exam_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(moreapp_view_holder moreapp_view_holderVar, int i) {
        BIKE_RTOQuestionModel bIKE_RTOQuestionModel = this.moArrayList.get(i);
        moreapp_view_holderVar.lRTOQuestionModel = bIKE_RTOQuestionModel;
        if (bIKE_RTOQuestionModel.getIsimage().equalsIgnoreCase("0")) {
            moreapp_view_holderVar.mImgQuestionImage.setVisibility(View.GONE);
            moreapp_view_holderVar.image_card.setVisibility(View.GONE);
        } else if (bIKE_RTOQuestionModel.getIsimage().equalsIgnoreCase("1")) {
            Resources resources = this.moContext.getResources();
            String imageUrl = bIKE_RTOQuestionModel.getImageUrl();
            if (imageUrl.indexOf(".") > 0) {
                imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("."));
            }
            moreapp_view_holderVar.mImgQuestionImage.setImageDrawable(resources.getDrawable(resources.getIdentifier(imageUrl, "drawable", this.moContext.getPackageName())));
            moreapp_view_holderVar.mImgQuestionImage.setVisibility(View.VISIBLE);
            moreapp_view_holderVar.image_card.setVisibility(View.VISIBLE);
        }

        moreapp_view_holderVar.mTxtQuenum.setText(String.valueOf(i + 1));
        moreapp_view_holderVar.mTxtQueText.setText(bIKE_RTOQuestionModel.getQuestion());
        moreapp_view_holderVar.mTxtOption1.setText(bIKE_RTOQuestionModel.getQuestionOptions().get(0));
        moreapp_view_holderVar.mTxtOption2.setText(bIKE_RTOQuestionModel.getQuestionOptions().get(1));
        moreapp_view_holderVar.mTxtOption3.setText(bIKE_RTOQuestionModel.getQuestionOptions().get(2));
        moreapp_view_holderVar.mCntOption1.setBackground(this.moContext.getResources().getDrawable(R.drawable.layout_unselected));
        moreapp_view_holderVar.mCntOption2.setBackground(this.moContext.getResources().getDrawable(R.drawable.layout_unselected));
        moreapp_view_holderVar.mCntOption3.setBackground(this.moContext.getResources().getDrawable(R.drawable.layout_unselected));
        moreapp_view_holderVar.mTxtOption1.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtOption2.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtOption3.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtA.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtB.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtC.setTextColor(this.moContext.getResources().getColor(R.color.black));
        if (bIKE_RTOQuestionModel.getAttemptMiss()) {
            moreapp_view_holderVar.mCntOption1.setClickable(false);
            moreapp_view_holderVar.mCntOption2.setClickable(false);
            moreapp_view_holderVar.mCntOption3.setClickable(false);
        } else if (bIKE_RTOQuestionModel.getAttempt()) {
            moreapp_view_holderVar.mCntOption1.setClickable(false);
            moreapp_view_holderVar.mCntOption2.setClickable(false);
            moreapp_view_holderVar.mCntOption3.setClickable(false);
            if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("0")) {
                moreapp_view_holderVar.mCntOption1.setBackground(this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                moreapp_view_holderVar.mTxtOption1.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                moreapp_view_holderVar.mTxtA.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(bIKE_RTOQuestionModel.getSelctedAnswer())) {
                    return;
                }
                if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase("0")) {
                    moreapp_view_holderVar.mCntOption1.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                } else if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase("1")) {
                    moreapp_view_holderVar.mCntOption2.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                } else if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    moreapp_view_holderVar.mCntOption3.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                }
            } else if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("1")) {
                moreapp_view_holderVar.mCntOption2.setBackground(this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                moreapp_view_holderVar.mTxtOption2.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                moreapp_view_holderVar.mTxtB.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(bIKE_RTOQuestionModel.getSelctedAnswer())) {
                    return;
                }
                if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase("0")) {
                    moreapp_view_holderVar.mCntOption1.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                } else if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase("1")) {
                    moreapp_view_holderVar.mCntOption2.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                } else if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    moreapp_view_holderVar.mCntOption3.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                }
            } else if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                moreapp_view_holderVar.mCntOption3.setBackground(this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                moreapp_view_holderVar.mTxtOption3.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                moreapp_view_holderVar.mTxtC.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(bIKE_RTOQuestionModel.getSelctedAnswer())) {
                    return;
                }
                if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase("0")) {
                    moreapp_view_holderVar.mCntOption1.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                } else if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase("1")) {
                    moreapp_view_holderVar.mCntOption2.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                } else if (bIKE_RTOQuestionModel.getSelctedAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    moreapp_view_holderVar.mCntOption3.setBackground(this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                }
            }
        } else {
            moreapp_view_holderVar.mCntOption1.setClickable(true);
            moreapp_view_holderVar.mCntOption2.setClickable(true);
            moreapp_view_holderVar.mCntOption3.setClickable(true);
        }
    }

    @Override
    public int getItemCount() {
        return this.moArrayList.size();
    }

    public class moreapp_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout mCntOption1;
        public LinearLayout mCntOption2;
        public LinearLayout mCntOption3;
        public TextView mTxtA;
        public TextView mTxtB;
        public TextView mTxtC;
        public TextView mTxtOption1;
        public TextView mTxtOption2;
        public TextView mTxtOption3;
        public TextView mTxtQueText;
        public TextView mTxtQuenum;
        public ImageView mImgQuestionImage;
        public CardView image_card;
        BIKE_RTOQuestionModel lRTOQuestionModel;

        public moreapp_view_holder(View view) {
            super(view);
            this.mTxtQuenum = view.findViewById(R.id.txt_quenum);
            this.mTxtQueText = view.findViewById(R.id.txt_queText);
            this.mCntOption1 = view.findViewById(R.id.cnt_option1);
            this.mCntOption2 = view.findViewById(R.id.cnt_option2);
            this.mCntOption3 = view.findViewById(R.id.cnt_option3);
            this.mTxtOption1 = view.findViewById(R.id.txt_option1);
            this.mTxtOption2 = view.findViewById(R.id.txt_option2);
            this.mTxtOption3 = view.findViewById(R.id.txt_option3);
            this.mTxtA = view.findViewById(R.id.txt_a);
            this.mTxtB = view.findViewById(R.id.txt_b);
            this.mTxtC = view.findViewById(R.id.txt_c);
            this.mCntOption1.setOnClickListener(this);
            this.mCntOption2.setOnClickListener(this);
            this.mCntOption3.setOnClickListener(this);
            this.mImgQuestionImage = view.findViewById(R.id.img_questionImage);
            this.image_card = view.findViewById(R.id.image_card);
        }

        @Override
        public void onClick(View view) {
            if (view == this.mCntOption1) {
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("0")) {
                    ((TestExamActivity) BIKE_ExamAdapter.this.moContext).CorrectAnswer++;
                    this.mCntOption1.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption1.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtA.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                } else {
                    ((TestExamActivity) BIKE_ExamAdapter.this.moContext).WrongAnswer++;
                    this.mCntOption1.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                    this.mTxtOption1.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtA.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("1")) {
                    this.mCntOption2.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption2.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtB.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    this.mCntOption3.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption3.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtC.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                this.lRTOQuestionModel.setAttempt(true);
                this.lRTOQuestionModel.setSelctedAnswer("0");
                ((TestExamActivity) BIKE_ExamAdapter.this.moContext).AttendQuestion++;
                BIKE_ExamAdapter.this.notifyDataSetChanged();
                ((TestExamActivity) BIKE_ExamAdapter.this.moContext).NextQuestion();
            } else if (view == this.mCntOption2) {
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("1")) {
                    ((TestExamActivity) BIKE_ExamAdapter.this.moContext).CorrectAnswer++;
                    this.mCntOption2.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption2.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtB.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                } else {
                    ((TestExamActivity) BIKE_ExamAdapter.this.moContext).WrongAnswer++;
                    this.mCntOption2.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                    this.mTxtOption2.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtB.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("0")) {
                    this.mCntOption1.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption1.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtA.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    this.mCntOption3.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption3.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtC.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                this.lRTOQuestionModel.setAttempt(true);
                this.lRTOQuestionModel.setSelctedAnswer("1");
                ((TestExamActivity) BIKE_ExamAdapter.this.moContext).AttendQuestion++;
                BIKE_ExamAdapter.this.notifyDataSetChanged();
                ((TestExamActivity) BIKE_ExamAdapter.this.moContext).NextQuestion();
            } else if (view == this.mCntOption3) {
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    ((TestExamActivity) BIKE_ExamAdapter.this.moContext).CorrectAnswer++;
                    this.mCntOption3.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption3.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtC.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                } else {
                    ((TestExamActivity) BIKE_ExamAdapter.this.moContext).WrongAnswer++;
                    this.mCntOption3.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.wrong_answer));
                    this.mTxtOption3.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtC.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("1")) {
                    this.mCntOption2.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption2.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtB.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                if (this.lRTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("0")) {
                    this.mCntOption1.setBackground(BIKE_ExamAdapter.this.moContext.getResources().getDrawable(R.drawable.correct_answer));
                    this.mTxtOption1.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                    this.mTxtA.setTextColor(BIKE_ExamAdapter.this.moContext.getResources().getColor(R.color.white));
                }
                this.lRTOQuestionModel.setAttempt(true);
                this.lRTOQuestionModel.setSelctedAnswer(ExifInterface.GPS_MEASUREMENT_2D);
                ((TestExamActivity) BIKE_ExamAdapter.this.moContext).AttendQuestion++;
                BIKE_ExamAdapter.this.notifyDataSetChanged();
                ((TestExamActivity) BIKE_ExamAdapter.this.moContext).NextQuestion();
            }
        }
    }
}
