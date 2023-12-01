package com.rto.vehicle.numberplate.finder.information.spinnerdatepicker;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.Calendar;

import com.rto.vehicle.numberplate.finder.information.R;

public class SpinnerDatePicker implements DatePicker.OnDateChangedListener {

    static String mdate;
    private static SpinnerDatePicker instance;
    Button btnOk;
    LinearLayout buttonLayout;
    Button cancel;
    Context context;
    Dialog dtPickerDlg;
    IDatePicker iDatePicker;
    CardView mCardView;
    TextView mTitle;
    DatePicker picker;

    public SpinnerDatePicker(Context context) {
        this.context = context;
        this.dtPickerDlg = new Dialog(context);
        openSpinnerDatePicker();
    }

    public static SpinnerDatePicker getInstance(Context context) {
        SpinnerDatePicker spinnerDatePicker = new SpinnerDatePicker(context);
        instance = spinnerDatePicker;
        return spinnerDatePicker;
    }

    public SpinnerDatePicker show() {
        this.dtPickerDlg.show();
        return instance;
    }

    public SpinnerDatePicker callback(IDatePicker iDatePicker) {
        this.iDatePicker = iDatePicker;
        return instance;
    }

    public SpinnerDatePicker setTitle(String str) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setText(str);
        }
        return instance;
    }

    public SpinnerDatePicker setTitleBackgroundColor(int i) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setBackgroundColor(i);
        }
        return instance;
    }

    public SpinnerDatePicker setTitleTextdColor(int i) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setTextColor(i);
        }
        return instance;
    }

    public SpinnerDatePicker setMaxDate(long j) {
        this.picker.setMaxDate(j);
        return instance;
    }

    public SpinnerDatePicker setMinDate(long j) {
        this.picker.setMinDate(j);
        return instance;
    }

    public SpinnerDatePicker setOkButtonText(String str) {
        Button button = this.btnOk;
        if (button != null) {
            button.setText(str);
        }
        return instance;
    }

    public SpinnerDatePicker setCancelButtonText(String str) {
        Button button = this.cancel;
        if (button != null) {
            button.setText(str);
        }
        return instance;
    }

    public SpinnerDatePicker setCancelButtonTextColor(int i) {
        Button button = this.cancel;
        if (button != null) {
            button.setTextColor(i);
        }
        return instance;
    }

    public SpinnerDatePicker setOkButtonTextColor(int i) {
        Button button = this.btnOk;
        if (button != null) {
            button.setTextColor(i);
        }
        return instance;
    }

    public SpinnerDatePicker setButtonLayoutGravity(int i) {
        LinearLayout linearLayout = this.buttonLayout;
        if (linearLayout != null) {
            linearLayout.setGravity(i);
        }
        return instance;
    }

    public SpinnerDatePicker setTitleGravity(int i) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setGravity(i);
        }
        return instance;
    }

    public SpinnerDatePicker setCardViewBackgroundColor(int i) {
        CardView cardView = this.mCardView;
        if (cardView != null) {
            cardView.setCardBackgroundColor(i);
        }
        return instance;
    }

    public SpinnerDatePicker setCalendarBackgroundColor(int i) {
        DatePicker datePicker = this.picker;
        if (datePicker != null) {
            datePicker.setBackgroundColor(i);
        }
        return instance;
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        mdate = i3 + "-" +
                (i2 + 1) + "-" +
                i;
    }

    private void openSpinnerDatePicker() {
        Dialog dialog = new Dialog(this.context);
        this.dtPickerDlg = dialog;
        dialog.requestWindowFeature(1);
        this.dtPickerDlg.setContentView(R.layout.dialog_date_picker);
        this.picker = this.dtPickerDlg.findViewById(R.id.datePicker);
        this.btnOk = this.dtPickerDlg.findViewById(R.id.okbutton);
        this.mTitle = this.dtPickerDlg.findViewById(R.id.title);
        this.cancel = this.dtPickerDlg.findViewById(R.id.cancel);
        this.buttonLayout = this.dtPickerDlg.findViewById(R.id.buttonLayout);
        this.mCardView = this.dtPickerDlg.findViewById(R.id.cardView);
        this.btnOk.setOnClickListener(view -> {
            if (SpinnerDatePicker.this.iDatePicker != null && SpinnerDatePicker.mdate != null && !SpinnerDatePicker.mdate.isEmpty()) {
                SpinnerDatePicker.this.iDatePicker.onOkClick(SpinnerDatePicker.mdate);
            } else if (SpinnerDatePicker.this.iDatePicker != null && SpinnerDatePicker.this.picker != null) {
                String sb = SpinnerDatePicker.this.picker.getDayOfMonth() + "-" +
                        (SpinnerDatePicker.this.picker.getMonth() + 1) + "-" +
                        SpinnerDatePicker.this.picker.getYear();
                SpinnerDatePicker.mdate = sb;
                SpinnerDatePicker.this.iDatePicker.onOkClick(SpinnerDatePicker.mdate);
            }
            SpinnerDatePicker.this.dtPickerDlg.dismiss();
        });
        this.cancel.setOnClickListener(view -> SpinnerDatePicker.this.dtPickerDlg.dismiss());
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(5);
        int i2 = calendar.get(2);
        this.picker.init(calendar.get(1), i2, i, this);
        this.dtPickerDlg.setOnDismissListener(dialogInterface -> {
            if (SpinnerDatePicker.this.iDatePicker != null) {
                SpinnerDatePicker.this.iDatePicker.onDialogDismiss();
            }
        });
    }
}
