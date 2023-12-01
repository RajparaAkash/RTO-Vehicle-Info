package com.rto.vehicle.numberplate.finder.information.Fragment;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.numberplate.finder.information.Adapter.BIKE_SignAdapter;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOSignModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class TrafficSignalsFragment extends Fragment {
    public ArrayList<Integer> SignArrayList;
    public ArrayList<BIKE_RTOSignModel> SignModelArrayList;
    ArrayList<String> SignValueList;
    Context context;
    Resources mResources;
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRrRtoSigns;

    public static TrafficSignalsFragment newInstance() {
        TrafficSignalsFragment bIKE_TrafficSignalsFragment = new TrafficSignalsFragment();
        bIKE_TrafficSignalsFragment.setArguments(new Bundle());
        return bIKE_TrafficSignalsFragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_traffic_signals, viewGroup, false);

        this.SignValueList = new ArrayList<>();
        Context locale = LocaleHelper.setLocale(getActivity(), MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
        this.mRrRtoSigns = inflate.findViewById(R.id.rr_rtotrafficsignlist);
        this.SignValueList.add(this.mResources.getString(R.string.Stop));
        this.SignValueList.add(this.mResources.getString(R.string.Give_way));
        this.SignValueList.add(this.mResources.getString(R.string.Straight_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.One_way));
        this.SignValueList.add(this.mResources.getString(R.string.One_way));
        this.SignValueList.add(this.mResources.getString(R.string.Vehicles_prohibited_in_both_directions));
        this.SignValueList.add(this.mResources.getString(R.string.Horn_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Pedestrians_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Cycles_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Right_turn_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Left_turn_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.U_turn_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Overtaking_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Trucks_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Tonga_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.All_motor_vehicles_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Hand_cart_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.Bullock_and_hand_carts_prohibited));
        this.SignValueList.add(this.mResources.getString(R.string.No_parking));
        this.SignValueList.add(this.mResources.getString(R.string.No_parking_or_stopping));
        this.SignValueList.add(this.mResources.getString(R.string.Speed_limit));
        this.SignValueList.add(this.mResources.getString(R.string.Axle_load_limit));
        this.SignValueList.add(this.mResources.getString(R.string.Width_limit));
        this.SignValueList.add(this.mResources.getString(R.string.Weight_limit));
        this.SignValueList.add(this.mResources.getString(R.string.Length_limit));
        this.SignValueList.add(this.mResources.getString(R.string.Load_limit));
        this.SignValueList.add(this.mResources.getString(R.string.Restriction_ends));
        this.SignValueList.add(this.mResources.getString(R.string.Turn_left));
        this.SignValueList.add(this.mResources.getString(R.string.Turn_Right));
        this.SignValueList.add(this.mResources.getString(R.string.Ahead_or_turn_left));
        this.SignValueList.add(this.mResources.getString(R.string.Ahead_or_turn_right));
        this.SignValueList.add(this.mResources.getString(R.string.Ahead));
        this.SignValueList.add(this.mResources.getString(R.string.Keep_left));
        this.SignValueList.add(this.mResources.getString(R.string.Sound_horn));
        this.SignValueList.add(this.mResources.getString(R.string.Buses_only));
        this.SignValueList.add(this.mResources.getString(R.string.Right_hand_curve));
        this.SignValueList.add(this.mResources.getString(R.string.Left_hand_curve));
        this.SignValueList.add(this.mResources.getString(R.string.Right_hand_pin_bend));
        this.SignValueList.add(this.mResources.getString(R.string.Left_hand_pin_bend));
        this.SignValueList.add(this.mResources.getString(R.string.Right_reverse_bend));
        this.SignValueList.add(this.mResources.getString(R.string.Narrow_bridge));
        this.SignValueList.add(this.mResources.getString(R.string.Gap_in_median));
        this.SignValueList.add(this.mResources.getString(R.string.Cycle_crossing));
        this.SignValueList.add(this.mResources.getString(R.string.Pedestrian_crossing));
        this.SignValueList.add(this.mResources.getString(R.string.School_ahead));
        this.SignValueList.add(this.mResources.getString(R.string.Men_at_work));
        this.SignValueList.add(this.mResources.getString(R.string.Roundabout));
        this.SignValueList.add(this.mResources.getString(R.string.Narrow_road));
        this.SignValueList.add(this.mResources.getString(R.string.Road_widens));
        this.SignValueList.add(this.mResources.getString(R.string.Side_road_left));
        this.SignValueList.add(this.mResources.getString(R.string.Side_road_right));
        this.SignValueList.add(this.mResources.getString(R.string.Major_road));
        this.SignValueList.add(this.mResources.getString(R.string.Major_road));
        this.SignValueList.add(this.mResources.getString(R.string.Staggered_intersection));
        this.SignValueList.add(this.mResources.getString(R.string.Staggered_intersection));
        this.SignValueList.add(this.mResources.getString(R.string.Y_intersection));
        this.SignValueList.add(this.mResources.getString(R.string.Y_intersection));
        this.SignValueList.add(this.mResources.getString(R.string.Y_intersection));
        this.SignValueList.add(this.mResources.getString(R.string.T_intersection));
        this.SignValueList.add(this.mResources.getString(R.string.Reduced_carriageway));
        this.SignValueList.add(this.mResources.getString(R.string.Reduced_carriageway));
        this.SignValueList.add(this.mResources.getString(R.string.Two_way));
        this.SignValueList.add(this.mResources.getString(R.string.Cross_road));
        this.SignValueList.add(this.mResources.getString(R.string.Truck_lay_bay));
        this.SignValueList.add(this.mResources.getString(R.string.Toll_booth_ahead));
        this.SignValueList.add(this.mResources.getString(R.string.Parking_this_side));
        this.SignValueList.add(this.mResources.getString(R.string.Parking_both_sides));
        this.SignValueList.add(this.mResources.getString(R.string.Scooter_and_motor_cycle_stand));
        this.SignValueList.add(this.mResources.getString(R.string.Taxi_stand));
        this.SignValueList.add(this.mResources.getString(R.string.Auto_rickshaw_stand));
        this.SignValueList.add(this.mResources.getString(R.string.Public_telephone));
        this.SignValueList.add(this.mResources.getString(R.string.Filling_station));
        this.SignValueList.add(this.mResources.getString(R.string.Hospital));
        this.SignValueList.add(this.mResources.getString(R.string.First_aid_post));
        this.SignValueList.add(this.mResources.getString(R.string.Eating_place));
        this.SignValueList.add(this.mResources.getString(R.string.Light_refreshment));
        this.SignValueList.add(this.mResources.getString(R.string.No_through_road));
        this.SignValueList.add(this.mResources.getString(R.string.No_through_side_road));
        this.SignValueList.add(this.mResources.getString(R.string.Resting_place));
        this.SignValueList.add(this.mResources.getString(R.string.Pedestrian_subway));
        this.SignValueList.add(this.mResources.getString(R.string.Repair_facility));
        LoadSignData();
        AdapterFatch();
        return inflate;
    }

    private void LoadSignData() {
        this.SignArrayList = new ArrayList<>();
        this.SignModelArrayList = new ArrayList<>();
        this.SignArrayList = MyApplication.SignList;
        for (int i = 0; i < this.SignArrayList.size(); i++) {
            BIKE_RTOSignModel bIKE_RTOSignModel = new BIKE_RTOSignModel();
            bIKE_RTOSignModel.setResDrawable(this.SignArrayList.get(i));
            bIKE_RTOSignModel.setSignValue(this.SignValueList.get(i));
            this.SignModelArrayList.add(bIKE_RTOSignModel);
        }
    }

    public void onButtonPressed(Uri uri) {
        OnFragmentInteractionListener onFragmentInteractionListener = this.mListener;
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
    }

    private void AdapterFatch() {
        BIKE_SignAdapter bIKE_SignAdapter = new BIKE_SignAdapter(getActivity(), this.SignModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        this.mRrRtoSigns.setItemAnimator(new DefaultItemAnimator());
        this.mRrRtoSigns.setLayoutManager(linearLayoutManager);
        this.mRrRtoSigns.setAdapter(bIKE_SignAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
