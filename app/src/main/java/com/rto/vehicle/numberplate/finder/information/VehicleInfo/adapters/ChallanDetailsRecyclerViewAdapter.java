package com.rto.vehicle.numberplate.finder.information.VehicleInfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ChallanDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;

import java.util.List;

import com.rto.vehicle.numberplate.finder.information.R;

public class ChallanDetailsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ChallanDetails> challanDetailList;
    Context context;

    public ChallanDetailsRecyclerViewAdapter(Context context, List<ChallanDetails> list) {
        this.context = context;
        this.challanDetailList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(this.context).inflate(R.layout.itemview_challan_details, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            ChallanDetails challanDetails = this.challanDetailList.get(viewHolder.getAdapterPosition());
            itemViewHolder.txvChallanNoValue.setText(this.context.getString(R.string.format_challan_no, challanDetails.getChallanNo()));
            itemViewHolder.txvChallanStatus.setText(challanDetails.getChallanStatus());
            itemViewHolder.txvChallanAmount.setText(this.context.getString(R.string.format_price, String.valueOf(challanDetails.getAmount())));
            itemViewHolder.txvChallanDate.setText(challanDetails.getChallanDate());
            if (Utils.isNullOrEmpty(challanDetails.getPaymentDate())) {
                itemViewHolder.layoutPaymentDate.setVisibility(View.GONE);
            } else {
                itemViewHolder.layoutPaymentDate.setVisibility(View.VISIBLE);
                itemViewHolder.txvPaymentDate.setText(challanDetails.getPaymentDate());
            }
            itemViewHolder.itemView.setTag(challanDetails);
        }
    }

    @Override
    public int getItemCount() {
        return this.challanDetailList.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutPaymentDate;
        TextView txvChallanAmount;
        TextView txvChallanDate;
        TextView txvChallanNoValue;
        TextView txvChallanStatus;
        TextView txvPaymentDate;

        ItemViewHolder(View view) {
            super(view);
            this.txvChallanNoValue = view.findViewById(R.id.txvChallanNoValue);
            this.txvChallanDate = view.findViewById(R.id.txvChallanDate);
            this.txvChallanAmount = view.findViewById(R.id.txvChallanAmount);
            this.txvChallanStatus = view.findViewById(R.id.txvChallanStatus);
            this.txvPaymentDate = view.findViewById(R.id.txvPaymentDate);
            this.layoutPaymentDate = view.findViewById(R.id.layoutPaymentDate);
        }
    }
}
