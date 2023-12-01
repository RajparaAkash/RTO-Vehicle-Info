package com.rto.vehicle.numberplate.finder.information.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.Activity.TestResultActivity;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOExamResultModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class BIKE_ResultAdapter extends RecyclerView.Adapter<BIKE_ResultAdapter.moreapp_view_holder> {
    Context context;
    Resources mResources;
    ArrayList<BIKE_RTOExamResultModel> moArrayList;
    Context moContext;

    public BIKE_ResultAdapter(Context context, ArrayList<BIKE_RTOExamResultModel> arrayList) {
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
                .inflate(R.layout.itemview_rto_result_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(moreapp_view_holder moreapp_view_holderVar, @SuppressLint("RecyclerView") int i) {
        Drawable drawable;

        BIKE_RTOExamResultModel bIKE_RTOExamResultModel = this.moArrayList.get(i);
        moreapp_view_holderVar.lRTOExamResultModel = bIKE_RTOExamResultModel;
        if (bIKE_RTOExamResultModel.getResult()) {
            this.moContext.getResources().getColor(R.color.darkgreen);
        } else {
            this.moContext.getResources().getColor(R.color.red);
        }
        moreapp_view_holderVar.lPosition = i;
        moreapp_view_holderVar.mTxtAttendlbl.setText(this.mResources.getString(R.string.attended));
        moreapp_view_holderVar.mTxtCorrectlbl.setText(this.mResources.getString(R.string.correct));
        moreapp_view_holderVar.mTxtWronglbl.setText(this.mResources.getString(R.string.wrong));
        moreapp_view_holderVar.mTxtDate.setText(bIKE_RTOExamResultModel.getExamDate());
        moreapp_view_holderVar.mTxtAttend.setText(bIKE_RTOExamResultModel.getAttendQuestion());
        moreapp_view_holderVar.mTxtCorrect.setText(bIKE_RTOExamResultModel.getCorrectAnswer());
        moreapp_view_holderVar.mTxtWrong.setText(bIKE_RTOExamResultModel.getWrongAnswer());

        ImageView imageView = moreapp_view_holderVar.mImgresultlbl;
        if (bIKE_RTOExamResultModel.getResult()) {
            drawable = this.moContext.getResources().getDrawable(R.drawable.ic_passed);
        } else {
            drawable = this.moContext.getResources().getDrawable(R.drawable.ic_fail);
        }
        imageView.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return this.moArrayList.size();
    }


    public class moreapp_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int lPosition;
        public ImageView mImgresultlbl;
        public TextView mTxtAttend;
        public TextView mTxtAttendlbl;
        public TextView mTxtCorrect;
        public TextView mTxtCorrectlbl;
        public TextView mTxtDate;
        public TextView mTxtWrong;
        public TextView mTxtWronglbl;
        BIKE_RTOExamResultModel lRTOExamResultModel;
        private final TextView mImgdelete;

        public moreapp_view_holder(View view) {
            super(view);
            this.mTxtDate = view.findViewById(R.id.txt_date);
            this.mTxtAttend = view.findViewById(R.id.txt_attend);
            this.mTxtCorrect = view.findViewById(R.id.txt_Rcorrect);
            this.mTxtWrong = view.findViewById(R.id.txt_Rwrong);
            this.mImgdelete = view.findViewById(R.id.img_delete);
            this.mTxtAttendlbl = view.findViewById(R.id.txt_attendlbl);
            this.mTxtCorrectlbl = view.findViewById(R.id.txt_correctlbl);
            this.mTxtWronglbl = view.findViewById(R.id.txt_wronglbl);
            this.mImgresultlbl = view.findViewById(R.id.img_resultmsg);

            mImgdelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == this.mImgdelete) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.dialog_history_remove);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

                dialog.findViewById(R.id.btn_cancle).setOnClickListener(view2 -> dialog.dismiss());
                dialog.findViewById(R.id.btn_ok).setOnClickListener(view2 -> {
                    ((TestResultActivity) BIKE_ResultAdapter.this.moContext).DeleteItemAndSave(moreapp_view_holder.this.lPosition);
                    dialog.dismiss();
                });
                dialog.show();
            }
        }
    }
}
