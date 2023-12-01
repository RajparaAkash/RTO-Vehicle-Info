package com.rto.vehicle.numberplate.finder.information.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleUtils;
import com.rto.vehicle.numberplate.finder.information.spinnerdatepicker.IDatePicker;
import com.rto.vehicle.numberplate.finder.information.spinnerdatepicker.SpinnerDatePicker;
import com.sdk.dierct.link.open.on.fail.sdk.AdClosedListener;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class DLActivity extends AppCompatActivity implements IDatePicker {

    public static ArrayList<String> arrayListDemo = new ArrayList<>();
    public static ArrayList<String> arrayListDemo2 = new ArrayList<>();
    public static Map<String, String> cookies;
    public static String formNumber;
    private static ReviewManager reviewManager;

    public static String viewState;

    String dateBirth;
    String dlNumber;
    EditText dl_number;
    TextView dob;

    ProgressBar progres_bar;
    String storeElement = "";
    Connection.Response response = null;

    @Override
    public void onDialogDismiss() {
    }

    public boolean checkEntry() {
        return this.dl_number.getText().toString().length() + this.dob.getText().toString().length() >= 10;
    }

    public String getAbsoluteURL(String str) {
        return "https://parivahan.gov.in" + str;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dl_number);
        reviewManager = ReviewManagerFactory.create(DLActivity.this);

        dob = findViewById(R.id.dob);
        progres_bar = findViewById(R.id.progres_bar);
        dl_number = findViewById(R.id.dl_number);

        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        dl_number.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        findViewById(R.id.back_img).setOnClickListener(view -> onBackPressed());
        this.dob.setOnClickListener(view -> SpinnerDatePicker.getInstance(DLActivity.this).setMaxDate(System.currentTimeMillis()).callback(DLActivity.this).setOkButtonText("Ok").show());
        findViewById(R.id.search_txt).setOnClickListener(view -> {
            if (DLActivity.this.isOnline()) {
                if (dl_number.length() == 0) {
                    Toast.makeText(DLActivity.this.getApplicationContext(), "Enter License Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!DLActivity.this.checkEntry()) {
                    Toast.makeText(DLActivity.this.getApplicationContext(), "License Number is invalid", Toast.LENGTH_SHORT).show();
                    return;
                } else if (DLActivity.this.dob.getText().toString().length() > 4) {
                    DLActivity dLActivity = DLActivity.this;
                    dLActivity.dlNumber = dLActivity.dl_number.getText().toString();
                    DLActivity dLActivity2 = DLActivity.this;
                    dLActivity2.dateBirth = dLActivity2.dob.getText().toString();
                    new LongOperation().execute("");
                    return;
                } else {
                    Toast.makeText(DLActivity.this.getApplicationContext(), "Invalid date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            VehicleUtils.showSnake(VehicleUtils.CONNECT, DLActivity.this);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private static void reviewdialog(Activity activity) {
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Getting the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = reviewManager.launchReviewFlow(activity, reviewInfo);

                flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("message", task.toString());
                        activity.finish();
                    }
                });
            }
        });
    }

    public boolean isOnline() {
        try {
            return ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        progres_bar.setVisibility(View.GONE);
    }

    @Override
    public void onOkClick(String str) {
        this.dob.setText(str);
    }

    public void nextGo() {
        Intent intent = new Intent(getApplicationContext(), DLDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ARRAY", arrayListDemo);
        bundle.putStringArrayList("ARRAY2", arrayListDemo2);
        intent.putExtras(bundle);
        new InterAds().ShowfuullAd(this, new AdClosedListener() {
            @Override
            public void AdisClosed() {
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        public void onProgressUpdate(Void... voidArr) {
        }

        @Override
        public String doInBackground(String... strArr) {
            try {
                DLActivity.arrayListDemo.clear();
                DLActivity.arrayListDemo2.clear();
                DLActivity dLActivity = DLActivity.this;
                dLActivity.response = Jsoup.connect(dLActivity.getAbsoluteURL("/rcdlstatus/vahan/rcDlHome.xhtml?pur_cd=101")).method(Connection.Method.GET).userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.101 Safari/537.36").execute();

                DLActivity.cookies = DLActivity.this.response.cookies();
                if (DLActivity.this.response.statusCode() == 200) {
                    Document parse = DLActivity.this.response.parse();
                    DLActivity.formNumber = parse.select("button[class=ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only]").attr("name");
                    DLActivity.viewState = parse.select("input[name=javax.faces.ViewState]").attr("value");
                    parse.getElementsByTag("img").get(1).attr("src");
                    DLActivity.this.storeElement = Jsoup.connect(VehicleUtils.SUB_URL).followRedirects(true).timeout(60000).method(Connection.Method.POST).cookies(DLActivity.cookies).referrer("https://parivahan.gov.in").header("Content-Type", "application/x-www-form-urlencoded").header("Host", "parivahan.gov.in").header("Accept", "application/xml, text/xml, */*; q=0.01").header("Accept-Language", "en-US,en;q=0.5").header("Accept-Encoding", "gzip, deflate, br").header("X-Requested-With", "XMLHttpRequest").header("Faces-Request", "partial/ajax").header("Origin", "https://parivahan.gov.in").userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36").data("javax.faces.partial.ajax", "true").data("javax.faces.source", DLActivity.formNumber).data("javax.faces.partial.execute", "@all").data("javax.faces.partial.render", "form_rcdl:pnl_show form_rcdl:pg_show form_rcdl:rcdl_pnl").data(DLActivity.formNumber, DLActivity.formNumber).data("form_rcdl", "form_rcdl").data("form_rcdl:tf_dlNO", DLActivity.this.dlNumber).data("form_rcdl:tf_dob_input", DLActivity.this.dateBirth).data("javax.faces.ViewState", DLActivity.viewState).execute().body();
                    String valueOf = String.valueOf(Jsoup.parse("<!DOCTYPE html><html><body>" + DLActivity.this.storeElement.substring(DLActivity.this.storeElement.indexOf("<table"), DLActivity.this.storeElement.lastIndexOf("</table>")) + "</body></html>"));
                    int indexOf = DLActivity.this.storeElement.indexOf("<div class=\"font-bold top-space bottom-space text-capitalize\">") + 62;
                    DLActivity dLActivity2 = DLActivity.this;
                    dLActivity2.storeElement = dLActivity2.storeElement.substring(indexOf, DLActivity.this.storeElement.indexOf("</div>", indexOf)).replaceAll("Registering Authority:", "").trim();

                    Document parse2 = Jsoup.parse(valueOf);
                    Element first = parse2.select("table").first();
                    String trim = first.select("tr").get(0).select("td").get(1).text().trim();
                    DLActivity.arrayListDemo.add(trim);
                    String trim2 = first.select("tr").get(1).select("td").get(1).text().trim();
                    DLActivity.arrayListDemo.add(trim2);


                    Element element = parse2.select("table").get(1);
                    DLActivity.arrayListDemo.add(element.select("tr").get(0).select("td").get(1).text());
                    DLActivity.arrayListDemo.add(element.select("tr").get(1).select("td").get(1).text());
                    Element element2 = parse2.select("table").get(3);
                    String replace = element2.select("tr").get(0).select("td").text().replace("Non-Transport", "");
                    DLActivity.arrayListDemo.add(replace);
                    String replace2 = element2.select("tr").get(1).select("td").text().replace("Transport", "");
                    DLActivity.arrayListDemo.add(replace2);

                    Iterator<Element> it = parse2.select("table").get(4).select("tr").iterator();
                    String str = null;
                    String str2 = null;
                    while (it.hasNext()) {
                        Elements select = it.next().select("td");
                        select.get(0).text();
                        String text = select.get(1).text();
                        select.get(2).text();
                        str2 = select.get(3).text();
                        str = text;
                    }
                    DLActivity.arrayListDemo.add(str);
                    DLActivity.arrayListDemo.add(str2);
                    DLActivity.arrayListDemo.add(DLActivity.this.dlNumber);
                    parse2.select("div").get(1);
                    parse2.select("div").get(2);
                    Element element3 = parse2.select("table").get(5);
                    Elements select2 = element3.select("tr");
                    element3.select("tr").get(0).select("th").get(0).select("span").text();
                    element3.select("tr").get(0).select("th").get(1).select("span").text();
                    element3.select("tr").get(0).select("th").get(2).select("span").text();
                    for (int i = 1; i < select2.size(); i++) {
                        select2.select("tr").get(i).text();
                        Elements select3 = select2.get(i).select("tr");
                        for (int i2 = 0; i2 < 3; i2++) {
                            String text2 = select3.select("td").get(i2).text();

                            DLActivity.arrayListDemo2.add(text2);
                        }
                    }
                    return "Executed";
                }
                return "Executed";
            } catch (Exception e) {
                e.printStackTrace();
                Intent intent = new Intent(DLActivity.this, DLErrorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("DLNO", DLActivity.this.dlNumber);
                intent.putExtras(bundle);
                startActivity(intent);
                return "Executed";
            }
        }


        @Override
        public void onPostExecute(String str) {
            if (DLActivity.arrayListDemo.size() == 0 || DLActivity.arrayListDemo2.size() == 0) {
                return;
            }
            progres_bar.setVisibility(View.GONE);
            nextGo();
        }

        @Override
        protected void onPreExecute() {
            progres_bar.setVisibility(View.VISIBLE);
        }
    }
}
