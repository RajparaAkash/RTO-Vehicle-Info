package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.Adapter.ServerEffectAdapter;
import com.rto.vehicle.numberplate.finder.information.FillterClass;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers.ToastHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CelebrityListActivity extends AppCompatActivity {

    String card;
    TextView header_title;
    ListView celebrity_rv;
    FillterClass exit_app_right_data;
    String fullVehicleNum;
    JSONArray jsonResultArr;

    ServerEffectAdapter serverEffectAdapter;
    ArrayList<FillterClass> array_exit_app_right = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_celebrity_list);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        (findViewById(R.id.back_img)).setOnClickListener(view -> onBackPressed());

        try {
            card = getIntent().getStringExtra("path");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        header_title = findViewById(R.id.header_title);
        celebrity_rv = findViewById(R.id.celebrity_rv);

        switch (card) {
            case "1":
                header_title.setText("Dancers");
                break;
            case "2":
                header_title.setText("Tycoons");
                break;
            case "3":
                header_title.setText("Singers");
                break;
            case "4":
                header_title.setText("Politician");
                break;
            case "5":
                header_title.setText("Sports");
                break;
            case "6":
                header_title.setText("Actress");
                break;
            case "7":
                header_title.setText("Actors");
                break;
        }

        initFilters();

        celebrity_rv.setOnItemClickListener((adapterView, view, i, j) -> {
            fullVehicleNum = array_exit_app_right.get(i).number;
            if (Utils.isNullOrEmpty(fullVehicleNum)) {
                ToastHelper.showToast(CelebrityListActivity.this, getString(R.string.no_info), true);
            } else if (!Utils.isNetworkConnected(CelebrityListActivity.this)) {
                ToastHelper.showToast(CelebrityListActivity.this, getString(R.string.app_internet_msg), true);
            } else {
                final String formatString = Utils.formatString(CelebrityListActivity.this.fullVehicleNum);
                if (Utils.isNullOrEmpty(formatString) || formatString.length() < 5) {
                    ToastHelper.showToast(CelebrityListActivity.this, "Please enter the correct vehicle no!", true);
                } else {
                    Intent intent = new Intent(CelebrityListActivity.this, SearchVehicleDetailsLoaderActivity.class);
                    intent.putExtra("REGISTRATION_NO", formatString);
                    intent.putExtra("ACTION", "SAVE");
                    startActivity(intent);
                }
            }
        });
    }

    public String loadJSONFromAsset() {
        try {
            InputStream open = getAssets().open("vehicle.json");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initFilters() {
        try {
            this.array_exit_app_right.clear();
            try {
                JSONObject jSONObject = new JSONObject(loadJSONFromAsset());
                switch (this.card) {
                    case "1":
                        this.jsonResultArr = jSONObject.optJSONArray("dancer_list");
                        break;
                    case "2":
                        this.jsonResultArr = jSONObject.optJSONArray("business_list");
                        break;
                    case "3":
                        this.jsonResultArr = jSONObject.optJSONArray("singer_list");
                        break;
                    case "4":
                        this.jsonResultArr = jSONObject.optJSONArray("politician_list");
                        break;
                    case "5":
                        this.jsonResultArr = jSONObject.optJSONArray("sports_list");
                        break;
                    case "6":
                        this.jsonResultArr = jSONObject.optJSONArray("actress_list");
                        break;
                    case "7":
                        this.jsonResultArr = jSONObject.optJSONArray("actor_list");
                        break;
                }
                for (int i = 0; i < this.jsonResultArr.length(); i++) {
                    JSONObject optJSONObject = this.jsonResultArr.optJSONObject(i);
                    this.exit_app_right_data = new FillterClass();
                    String optString = optJSONObject.optString("name");
                    String optString2 = optJSONObject.optString("url");
                    this.exit_app_right_data.name = optString;
                    this.exit_app_right_data.number = optString2;
                    this.array_exit_app_right.add(this.exit_app_right_data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (array_exit_app_right.size() != 0) {
                serverEffectAdapter = new ServerEffectAdapter(this, R.layout.itemview_celebrity_list, this.array_exit_app_right);
                celebrity_rv.setAdapter(serverEffectAdapter);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
