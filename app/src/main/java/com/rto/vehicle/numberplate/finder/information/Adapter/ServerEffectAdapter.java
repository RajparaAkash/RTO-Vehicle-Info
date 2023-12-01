package com.rto.vehicle.numberplate.finder.information.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rto.vehicle.numberplate.finder.information.FillterClass;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;


public class ServerEffectAdapter extends BaseAdapter {

    private ArrayList<FillterClass> array_all_apps;
    private LayoutInflater mInflater;

    public ServerEffectAdapter(Context context, int i, ArrayList<FillterClass> arrayList) {
        new ArrayList();
        try {
            this.array_all_apps = arrayList;
            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int i) {
        return 0L;
    }

    @Override
    public int getCount() {
        return this.array_all_apps.size();
    }

    @Override
    public FillterClass getItem(int i) {
        return this.array_all_apps.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        try {
            if (view == null) {
                viewHolder = new ViewHolder();
                view = this.mInflater.inflate(R.layout.itemview_celebrity_list, null);
                viewHolder.numtxt = view.findViewById(R.id.number);
                viewHolder.filterTxt = view.findViewById(R.id.name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            FillterClass item = getItem(i);
            String str = item.name;
            String str2 = item.number;
            viewHolder.filterTxt.setText(str);
            viewHolder.numtxt.setText(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    public static class ViewHolder {
        TextView filterTxt;
        TextView numtxt;

        public ViewHolder() {
        }
    }
}
