package com.rto.vehicle.numberplate.finder.information.VehicleInfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.SearchVehicleHistory;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import com.rto.vehicle.numberplate.finder.information.R;

public class RecentSearchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    IRecyclerViewClickListener mListener;
    IRecyclerViewLongClickListener mLongClickListener;
    private List<SearchVehicleHistory> historyList = new ArrayList();

    public RecentSearchHistoryAdapter(Context context, IRecyclerViewClickListener iRecyclerViewClickListener, IRecyclerViewLongClickListener iRecyclerViewLongClickListener) {
        this.context = context;
        this.mListener = iRecyclerViewClickListener;
        this.mLongClickListener = iRecyclerViewLongClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchVehicleHistoryItemHolder(LayoutInflater.from(this.context).inflate(R.layout.itemview_search_vehicle_history, viewGroup, false), this.mListener, this.mLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SearchVehicleHistoryItemHolder) {
            SearchVehicleHistoryItemHolder searchVehicleHistoryItemHolder = (SearchVehicleHistoryItemHolder) viewHolder;
            SearchVehicleHistory searchVehicleHistory = this.historyList.get(viewHolder.getAdapterPosition());
            searchVehicleHistoryItemHolder.txvRegNo.setText(searchVehicleHistory.getRegistrationNo());
            if (Utils.isNullOrEmpty(searchVehicleHistory.getName())) {
                searchVehicleHistoryItemHolder.txvName.setVisibility(View.GONE);
            } else {
                searchVehicleHistoryItemHolder.txvName.setText(searchVehicleHistory.getName());
                searchVehicleHistoryItemHolder.txvName.setVisibility(View.VISIBLE);
            }
            searchVehicleHistoryItemHolder.itemView.setTag(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        List<SearchVehicleHistory> list = this.historyList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void updateListData(List<SearchVehicleHistory> list) {
        this.historyList = list;
        notifyDataSetChanged();
    }

    static class SearchVehicleHistoryItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        IRecyclerViewClickListener mListener;
        IRecyclerViewLongClickListener mLongClickListener;
        TextView txvName;
        TextView txvRegNo;

        SearchVehicleHistoryItemHolder(View view, IRecyclerViewClickListener iRecyclerViewClickListener, IRecyclerViewLongClickListener iRecyclerViewLongClickListener) {
            super(view);
            this.txvRegNo = view.findViewById(R.id.txvRegNo);
            this.txvName = view.findViewById(R.id.txvName);
            this.mListener = iRecyclerViewClickListener;
            this.mLongClickListener = iRecyclerViewLongClickListener;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.mListener.onItemSelected(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            this.mLongClickListener.onItemLongClick(getAdapterPosition());
            return true;
        }
    }
}
