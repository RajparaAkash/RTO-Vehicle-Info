package com.rto.vehicle.numberplate.finder.information.VehicleInfo.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.rto.vehicle.numberplate.finder.information.R;


public class BaseBottomSheet extends BottomSheetDialogFragment {
    private Context context;
    private String message;
    private String title;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);
        this.title = getArguments().getString("TITLE");
        this.message = getArguments().getString("MESSAGE");
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dialog_acknowledgement, viewGroup, false);
        TextView textView = inflate.findViewById(R.id.cta);
        TextView textView2 = inflate.findViewById(R.id.secondaryBtn);
        ((TextView) inflate.findViewById(R.id.title)).setText(this.title);
        ((TextView) inflate.findViewById(R.id.message)).setText(this.message);
        textView2.setText(R.string.txt_not_now);
        textView.setText(R.string.txt_open_settings);
        textView2.setOnClickListener(view -> BaseBottomSheet.this.dismiss());
        textView.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", BaseBottomSheet.this.context.getPackageName(), null));
            BaseBottomSheet.this.context.startActivity(intent);
            BaseBottomSheet.this.dismiss();
        });
        return inflate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
