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

import com.rto.vehicle.numberplate.finder.information.Adapter.BIKE_BookAdapter;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;

import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;

public class QuestionsFragment extends Fragment {

    ArrayList<BIKE_RTOQuestionModel> QuestionArray;
    Context context;
    Resources mResources;
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRrRtobook;

    public static QuestionsFragment newInstance() {
        QuestionsFragment bIKE_QuestionsFragment = new QuestionsFragment();
        bIKE_QuestionsFragment.setArguments(new Bundle());
        return bIKE_QuestionsFragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_questions, viewGroup, false);

        this.QuestionArray = new ArrayList<>();
        Context locale = LocaleHelper.setLocale(getActivity(), MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();
        this.mRrRtobook = inflate.findViewById(R.id.rr_rtoquestionlist);
        this.QuestionArray = MyApplication.QuestionsArray;
        AdapterFatch();
        return inflate;
    }

    private void AdapterFatch() {
        BIKE_BookAdapter bIKE_BookAdapter = new BIKE_BookAdapter(getActivity(), this.QuestionArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        this.mRrRtobook.setItemAnimator(new DefaultItemAnimator());
        this.mRrRtobook.setLayoutManager(linearLayoutManager);
        this.mRrRtobook.setAdapter(bIKE_BookAdapter);
    }

    public void onButtonPressed(Uri uri) {
        OnFragmentInteractionListener onFragmentInteractionListener = this.mListener;
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
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
