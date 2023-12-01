package com.rto.vehicle.numberplate.finder.information.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class BIKE_BookAdapter extends RecyclerView.Adapter<BIKE_BookAdapter.moreapp_view_holder> {
    Context context;
    Resources mResources;
    ArrayList<BIKE_RTOQuestionModel> moArrayList;
    Activity moContext;

    public BIKE_BookAdapter(Activity activity, ArrayList<BIKE_RTOQuestionModel> arrayList) {
        this.moContext = activity;
        this.moArrayList = arrayList;
        Context locale = LocaleHelper.setLocale(activity, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
    }

    @NonNull
    @Override
    public moreapp_view_holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new moreapp_view_holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_rto_question_bank, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull moreapp_view_holder moreapp_view_holderVar, int i) {
        try {
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
            moreapp_view_holderVar.mTxtQueText.setText(bIKE_RTOQuestionModel.getQuestion());
            moreapp_view_holderVar.mTxtCorrectanswer.setText(bIKE_RTOQuestionModel.getAnswer());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.moArrayList.size();
    }

    public static class moreapp_view_holder extends RecyclerView.ViewHolder {
        public ImageView mImgQuestionImage;
        public TextView mTxtCorrectanswer;
        public TextView mTxtQueText;
        public CardView image_card;
        BIKE_RTOQuestionModel lRTOQuestionModel;

        public moreapp_view_holder(View view) {
            super(view);
            this.mTxtQueText = view.findViewById(R.id.txt_queText);
            this.mImgQuestionImage = view.findViewById(R.id.img_questionImage);
            this.mTxtCorrectanswer = view.findViewById(R.id.txt_correctanswer);
            this.image_card = view.findViewById(R.id.image_card);
        }
    }
}
