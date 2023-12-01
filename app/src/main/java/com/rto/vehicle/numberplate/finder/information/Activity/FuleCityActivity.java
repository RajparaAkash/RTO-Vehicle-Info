package com.rto.vehicle.numberplate.finder.information.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.Adapter.FuelCityAdapter;
import com.rto.vehicle.numberplate.finder.information.Model.StateListModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.rto.vehicle.numberplate.finder.information.R;


public class FuleCityActivity extends AppCompatActivity {

    FuelCityAdapter adapter;
    SearchView editText;
    ArrayList<StateListModel> stateList;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fule_city);

        findViewById(R.id.back_img).setOnClickListener(view -> onBackPressed());

        this.stateList = new ArrayList<>();
        SearchView searchView = findViewById(R.id.editTextTextPersonName);
        this.editText = searchView;
        searchView.clearFocus();
        this.editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                FuleCityActivity.this.filterText(str);
                return true;
            }
        });

        try {
            getdata();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.fuelCityRec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FuelCityAdapter fuelCityAdapter = new FuelCityAdapter(this, this.stateList);
        this.adapter = fuelCityAdapter;
        recyclerView.setAdapter(fuelCityAdapter);
    }

    public String readFile(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(str), StandardCharsets.UTF_8));
        String str2 = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return str2;
            }
            str2 = str2 + readLine;
        }
    }

    public void getdata() throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject(readFile("allcitystate.json"));
        for (int i = 0; i < jSONObject.length(); i++) {
            String valueOf = String.valueOf(jSONObject.names().get(i));
            this.stateList.add(new StateListModel(valueOf, "null"));
            JSONArray jSONArray = jSONObject.getJSONArray(valueOf);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                this.stateList.add(new StateListModel(jSONObject2.getString("cityName"), jSONObject2.getString("id")));
            }
        }
    }

    public void filterText(String str) {
        ArrayList<StateListModel> arrayList = new ArrayList<>();
        for (StateListModel next : this.stateList) {
            if (next.getStateName().toLowerCase().contains(str.toLowerCase())) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.adapter.setFilteredList(arrayList);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
