package com.rto.vehicle.numberplate.finder.information.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOSignModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class BIKE_SignAdapter extends RecyclerView.Adapter<BIKE_SignAdapter.moreapp_view_holder> {
    Activity activity;
    Context context;
    Resources mResources;
    ArrayList<BIKE_RTOSignModel> moArrayList;


    public BIKE_SignAdapter(Activity activity, ArrayList<BIKE_RTOSignModel> arrayList) {
        this.activity = activity;
        this.moArrayList = arrayList;
        Context locale = LocaleHelper.setLocale(activity, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
    }

    @Override
    public moreapp_view_holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new moreapp_view_holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_rto_signals, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(moreapp_view_holder moreapp_view_holderVar, int i) {
        BIKE_RTOSignModel bIKE_RTOSignModel = this.moArrayList.get(i);
        moreapp_view_holderVar.lRTOSignModel = bIKE_RTOSignModel;
        moreapp_view_holderVar.mImgSignImage.setImageResource(bIKE_RTOSignModel.getResDrawable());
        moreapp_view_holderVar.mTxtSignValue.setText(bIKE_RTOSignModel.getSignValue());
    }

    @Override
    public int getItemCount() {
        return this.moArrayList.size();
    }

    public static class moreapp_view_holder extends RecyclerView.ViewHolder {
        public ImageView mImgSignImage;
        public TextView mTxtSignValue;
        BIKE_RTOSignModel lRTOSignModel;

        public moreapp_view_holder(View view) {
            super(view);
            this.mTxtSignValue = view.findViewById(R.id.txt_signValue);
            this.mImgSignImage = view.findViewById(R.id.img_SignImage);
        }
    }
}
