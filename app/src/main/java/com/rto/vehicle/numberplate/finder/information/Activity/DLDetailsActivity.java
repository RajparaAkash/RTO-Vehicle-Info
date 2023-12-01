package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.R;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.util.ArrayList;


public class DLDetailsActivity extends AppCompatActivity {

    public ArrayList<String> arrayListDemo = new ArrayList<>();
    public ArrayList<String> arrayListDemo2 = new ArrayList<>();
    public ArrayList<String> cov_cat_array = new ArrayList<>();
    public ArrayList<String> vehicle_class_array = new ArrayList<>();
    public ArrayList<String> cov_issue_date_array = new ArrayList<>();
    TextView cov_cat;
    TextView cov_issue_date;
    TextView dl_validity;
    TextView haz_valid;
    TextView hill_valid;
    TextView issue_date;
    TextView last_trans_at;
    TextView lic_status;
    TextView name;
    TextView titleTxt;
    TextView trans;
    TextView vehicle_class;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dl_details);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        findViewById(R.id.back_img).setOnClickListener(view -> onBackPressed());

        this.arrayListDemo = getIntent().getExtras().getStringArrayList("ARRAY");
        this.arrayListDemo2 = getIntent().getExtras().getStringArrayList("ARRAY2");

        this.titleTxt = findViewById(R.id.title_txt);
        this.lic_status = findViewById(R.id.lic_status);
        this.name = findViewById(R.id.name);
        this.issue_date = findViewById(R.id.issue_date);
        this.last_trans_at = findViewById(R.id.last_trans_at);
        this.dl_validity = findViewById(R.id.dl_validity);
        this.trans = findViewById(R.id.tran_validity);
        this.cov_cat = findViewById(R.id.cov_cat);
        this.vehicle_class = findViewById(R.id.vehicle_class);
        this.cov_issue_date = findViewById(R.id.cov_issue_date);
        this.haz_valid = findViewById(R.id.haz_valid);
        this.hill_valid = findViewById(R.id.hill_valid);
        this.lic_status.setText(this.arrayListDemo.get(0));
        this.name.setText(this.arrayListDemo.get(1));
        this.issue_date.setText(this.arrayListDemo.get(2));
        this.last_trans_at.setText(this.arrayListDemo.get(3));
        this.dl_validity.setText(this.arrayListDemo.get(4));
        this.trans.setText(this.arrayListDemo.get(5));
        this.haz_valid.setText(this.arrayListDemo.get(6));
        this.hill_valid.setText(this.arrayListDemo.get(7));
        this.titleTxt.setText(this.arrayListDemo.get(8));
        for (int i = 0; i < this.arrayListDemo2.size(); i++) {
            if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12 || i == 15 || i == 18) {
                this.cov_cat_array.add(this.arrayListDemo2.get(i));
            } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13 || i == 16) {
                this.vehicle_class_array.add(this.arrayListDemo2.get(i));
            } else {
                this.cov_issue_date_array.add(this.arrayListDemo2.get(i));
            }
        }
        StringBuilder str = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for (int i2 = 0; i2 < this.cov_cat_array.size(); i2++) {
            str2.append(this.cov_cat_array.get(i2)).append(",");
        }
        this.cov_cat.setText(removeLastChar(str2.toString()));
        StringBuilder str3 = new StringBuilder();
        for (int i3 = 0; i3 < this.vehicle_class_array.size(); i3++) {
            str3.append(this.vehicle_class_array.get(i3)).append(",");
        }
        this.vehicle_class.setText(removeLastChar(str3.toString()));
        for (int i4 = 0; i4 < this.cov_issue_date_array.size(); i4++) {
            str.append(this.cov_issue_date_array.get(i4)).append(",");
        }
        this.cov_issue_date.setText(removeLastChar(str.toString()));

        findViewById(R.id.share_img).setOnClickListener(view -> {
            String sb = "Current Status - " +
                    DLDetailsActivity.this.lic_status.getText() +
                    "\n\nHolder's Name - " +
                    DLDetailsActivity.this.name.getText() +
                    "\n\nDate Of Issue: - " +
                    DLDetailsActivity.this.issue_date.getText().toString() +
                    "\n\nLast Transaction At: - " +
                    DLDetailsActivity.this.last_trans_at.getText().toString() +
                    "\n\nNon-Transport Validity- " +
                    DLDetailsActivity.this.dl_validity.getText().toString() +
                    "\n\nTransport Validity - " +
                    DLDetailsActivity.this.trans.getText().toString() +
                    "\n\nCOV Category - " +
                    DLDetailsActivity.this.cov_cat.getText().toString() +
                    "\n\nClass Of Vehicle - " +
                    DLDetailsActivity.this.vehicle_class.getText().toString() +
                    "\n\nCOV Issue Date - " +
                    DLDetailsActivity.this.cov_issue_date.getText().toString() +
                    "\n\nHazardous Valid Till: - " +
                    DLDetailsActivity.this.haz_valid.getText().toString() +
                    "\n\nHill Valid Till: - " +
                    DLDetailsActivity.this.hill_valid.getText().toString() +
                    "\nhttps://play.google.com/store/apps/details?id=" + DLDetailsActivity.this.getPackageName();
            int i5 = DLDetailsActivity.this.getApplicationInfo().labelRes;
            Intent intent = new Intent("android.intent.action.SEND");
            intent.addFlags(67108864);
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", DLDetailsActivity.this.getApplicationContext().getString(i5));
            DLDetailsActivity.this.getPackageName();
            intent.putExtra("android.intent.extra.TEXT", sb);
            DLDetailsActivity.this.startActivity(Intent.createChooser(intent, "Share:"));
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public String removeLastChar(String str) {
        return !TextUtils.isEmpty(str) ? str.substring(0, str.length() - 1) : str;
    }
}
