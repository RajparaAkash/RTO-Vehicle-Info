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

import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class BIKE_PreparationAdapter extends RecyclerView.Adapter<BIKE_PreparationAdapter.moreapp_view_holder> {

    Context context;
    Resources mResources;
    ArrayList<BIKE_RTOQuestionModel> moArrayList;
    Context moContext;

    public BIKE_PreparationAdapter(Context context, ArrayList<BIKE_RTOQuestionModel> arrayList) {
        this.moContext = context;
        this.moArrayList = arrayList;
        Context locale = LocaleHelper.setLocale(context, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
    }

    @NonNull
    @Override
    public moreapp_view_holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new moreapp_view_holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_rto_exampreparation_list, viewGroup, false));
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
        moreapp_view_holderVar.mTxtA.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtOption2.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtB.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtOption3.setTextColor(this.moContext.getResources().getColor(R.color.black));
        moreapp_view_holderVar.mTxtC.setTextColor(this.moContext.getResources().getColor(R.color.black));
        if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("0")) {
            moreapp_view_holderVar.mCntOption1.setBackground(this.moContext.getResources().getDrawable(R.drawable.bg_14));
            moreapp_view_holderVar.mTxtOption1.setTextColor(this.moContext.getResources().getColor(R.color.white));
            moreapp_view_holderVar.mTxtA.setTextColor(this.moContext.getResources().getColor(R.color.white));
        } else if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase("1")) {
            moreapp_view_holderVar.mCntOption2.setBackground(this.moContext.getResources().getDrawable(R.drawable.bg_14));
            moreapp_view_holderVar.mTxtOption2.setTextColor(this.moContext.getResources().getColor(R.color.white));
            moreapp_view_holderVar.mTxtB.setTextColor(this.moContext.getResources().getColor(R.color.white));
        } else if (bIKE_RTOQuestionModel.getCorrectAnswer().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            moreapp_view_holderVar.mCntOption3.setBackground(this.moContext.getResources().getDrawable(R.drawable.bg_14));
            moreapp_view_holderVar.mTxtOption3.setTextColor(this.moContext.getResources().getColor(R.color.white));
            moreapp_view_holderVar.mTxtC.setTextColor(this.moContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return this.moArrayList.size();
    }

    public static class moreapp_view_holder extends RecyclerView.ViewHolder {

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
            this.mTxtOption1 = view.findViewById(R.id.txt_option1);
            this.mTxtOption2 = view.findViewById(R.id.txt_option2);
            this.mTxtOption3 = view.findViewById(R.id.txt_option3);
            this.mCntOption1 = view.findViewById(R.id.cnt_option1);
            this.mCntOption2 = view.findViewById(R.id.cnt_option2);
            this.mCntOption3 = view.findViewById(R.id.cnt_option3);
            this.mTxtA = view.findViewById(R.id.txt_a);
            this.mTxtB = view.findViewById(R.id.txt_b);
            this.mTxtC = view.findViewById(R.id.txt_c);
            this.mImgQuestionImage = view.findViewById(R.id.img_questionImage);
            this.image_card = view.findViewById(R.id.image_card);
        }
    }
}
