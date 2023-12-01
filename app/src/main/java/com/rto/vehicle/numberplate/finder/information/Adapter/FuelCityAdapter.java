package com.rto.vehicle.numberplate.finder.information.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.Activity.DashBoardActivity;
import com.rto.vehicle.numberplate.finder.information.Model.StateListModel;
import com.rto.vehicle.numberplate.finder.information.MyConstant;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class FuelCityAdapter extends RecyclerView.Adapter<FuelCityAdapter.ViewHolder> {
    Context context;
    ArrayList<StateListModel> list;

    public FuelCityAdapter(Context context, ArrayList<StateListModel> arrayList) {
        this.context = context;
        this.list = arrayList;
    }

    public void setFilteredList(ArrayList<StateListModel> arrayList) {
        this.list = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_city_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setIsRecyclable(false);
        final StateListModel stateListModel = this.list.get(i);
        viewHolder.city_name_text.setText(stateListModel.getStateName());
        if (stateListModel.getId().equalsIgnoreCase("null")) {
            viewHolder.layout.setBackgroundResource(R.drawable.bg_fuel_city);
            viewHolder.city_name_text.setTextColor(Color.parseColor("#2C2C2C"));
            viewHolder.city_name_text.setTextSize(16);
            viewHolder.arrow_img.setVisibility(View.GONE);
        }
        viewHolder.layout.setOnClickListener(view -> {
            if (stateListModel.getId().equalsIgnoreCase("null")) {
                return;
            }
            Intent intent = new Intent(FuelCityAdapter.this.context, DashBoardActivity.class);
            SharedPreferences.Editor edit = FuelCityAdapter.this.context.getSharedPreferences(MyConstant.MY_PREFS_NAME, 0).edit();
            edit.putString("cityName", stateListModel.getStateName());
            edit.putString("cityId", stateListModel.getId());
            edit.apply();
            FuelCityAdapter.this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView city_name_text;
        ImageView arrow_img;

        public ViewHolder(View view) {
            super(view);
            this.city_name_text = view.findViewById(R.id.city_name_text);
            this.layout = view.findViewById(R.id.layout);
            this.arrow_img = view.findViewById(R.id.arrow_img);
        }
    }
}
