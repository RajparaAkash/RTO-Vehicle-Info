package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetails;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.GlobalReferenceEngine;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;
import com.rto.vehicle.numberplate.finder.information.VehicleUtils;

import java.util.Map;


public class ScraperAsyncTask extends AsyncTask<String, Void, Void> {
    private String btnIdStr;
    private final IResponseCallback callback;
    private final Context context;
    private Map<String, String> cookies;
    private ProgressDialog dProgress;
    private String viewStateStr;


    public ScraperAsyncTask(Context context, String str, IResponseCallback iResponseCallback) {
        this.context = context;
        this.callback = iResponseCallback;
        if (str == null || str.isEmpty() || !Utils.isActivityFinished(context)) {
            return;
        }
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dProgress = progressDialog;
        progressDialog.setMessage(str);
        this.dProgress.setCancelable(false);
        this.dProgress.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onPreExecute() {
        ProgressDialog progressDialog;
        super.onPreExecute();
        if (!Utils.isActivityFinished(this.context) || (progressDialog = this.dProgress) == null || progressDialog.isShowing()) {
            return;
        }
        this.dProgress.show();
    }

    @Override
    public Void doInBackground(String... strArr) {
        parseUrl();
        parseDetailsUrl(strArr[0], strArr[1]);
        return null;
    }

    @Override
    public void onPostExecute(Void r1) {
        ProgressDialog progressDialog;
        super.onPostExecute(r1);
        try {
            if (Utils.isActivityFinished(this.context) && (progressDialog = this.dProgress) != null && progressDialog.isShowing()) {
                this.dProgress.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    private void parseDetailsUrl(String str, String str2) {
        Connection.Response execute = null;
        String str3;
        Map<String, String> map = this.cookies;
        if (map == null || map.size() <= 0) {
            this.callback.onError("Error in your request, please try again later");
        } else if (Utils.isNullOrEmpty(this.btnIdStr) || Utils.isNullOrEmpty(this.viewStateStr)) {
            this.callback.onError("Error in your request, please try again later");
        } else {
            try {
                Connection connect = Jsoup.connect(GlobalReferenceEngine.localSourceFinalUrl);
                connect.followRedirects(true);
                connect.timeout(VehicleUtils.SERVER_TIMEOUT);
                connect.method(Connection.Method.POST);
                connect.cookies(this.cookies);
                connect.referrer(GlobalReferenceEngine.localSourceInitUrl);
                connect.header("Content-Type", "application/x-www-form-urlencoded");
                connect.header("Host", GlobalReferenceEngine.localSourceHostUrl);
                connect.header("Accept", "application/xml, text/xml, */*; q=0.01");
                connect.header("Accept-Language", "en-US,en;q=0.5");
                connect.header("Accept-Encoding", "gzip, deflate, br");
                connect.header("X-Requested-With", "XMLHttpRequest");
                connect.header("Faces-Request", "partial/ajax");
                connect.header("Origin", "https://" + GlobalReferenceEngine.localSourceHostUrl);
                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36");
                connect.data("javax.faces.partial.ajax", "true");
                connect.data("javax.faces.source", this.btnIdStr);
                connect.data("javax.faces.partial.execute", "@all");
                String str4 = this.btnIdStr;
                connect.data(str4, str4);
                connect.data("form_rcdl", "form_rcdl");
                connect.data("javax.faces.partial.render", "form_rcdl:pnl_show form_rcdl:pg_show form_rcdl:rcdl_pnl");
                connect.data(GlobalReferenceEngine.localSourceField1, str);
                connect.data(GlobalReferenceEngine.localSourceField2, str2);
                connect.data("javax.faces.ViewState", this.viewStateStr);
                execute = connect.execute();
            } catch (Exception unused) {
                this.callback.onError("Error in your request, please try again later");
            }
            if (execute.statusCode() != 200) {
                this.callback.onError("Error in your request, please try again later");
            } else if (execute.body().contains(VehicleUtils.REG_NOT_EXIT)) {
                this.callback.onNotFound();
            } else if (execute.body().contains("Technical error")) {
                this.callback.onError("Error in your request, please try again later");
            } else if (execute.body().contains("showMessageInDialog")) {
                this.callback.onError(Utils.extractWarningMessage(execute.body(), "registration"));
            } else {
                String body = execute.body();
                int indexOf = body.indexOf("<div class=\"font-bold top-space bottom-space text-capitalize\">") + 62;
                if (indexOf <= 62) {
                    this.callback.onError("Error in your request, please try again later");
                    return;
                }
                String trim = body.substring(indexOf, body.indexOf("</div>", indexOf)).replaceAll("Registering Authority:", "").trim();
                VehicleDetails vehicleDetails = new VehicleDetails();
                vehicleDetails.setRegistrationAuthority(trim);
                String substring = body.substring(body.indexOf("<table"), body.lastIndexOf("</table>"));
                Document parse = Jsoup.parse("<!DOCTYPE html><html><body>" + substring + "</body></html>");
                if (parse != null && parse.select("table") != null) {
                    Element first = parse.select("table").first();
                    first.select("tr").get(0).select("td").get(1).text();
                    String text = first.select("tr").get(0).select("td").get(3).text();
                    String text2 = first.select("tr").get(1).select("td").get(1).text();
                    String text3 = first.select("tr").get(1).select("td").get(3).text();
                    String text4 = first.select("tr").get(2).select("td").get(1).text();
                    String text5 = first.select("tr").get(3).select("td").get(1).text();
                    String trim2 = first.select("tr").get(3).select("td").get(3).text().trim();
                    String text6 = first.select("tr").get(4).select("td").get(1).text();
                    Element element = first.select("tr").get(5);
                    if (element != null) {
                        try {
                            if (element.select("td").get(1) != null) {
                                vehicleDetails.setFitnessUpto(element.select("td").get(1).text());
                            }
                            if (element.select("td").get(3) != null) {
                                vehicleDetails.setInsuranceUpto(element.select("td").get(3).text());
                            }
                        } catch (Exception unused2) {
                            this.callback.onError("Error in your request, please try again later");
                            str3 = "Error in your request, please try again later";
                        }
                    }
                    str3 = null;
                    Element element2 = first.select("tr").get(6);
                    if (element2 != null) {
                        if (element2.select("td").get(1) != null) {
                            vehicleDetails.setFuelNorms(element2.select("td").get(1).text());
                        }
                        if (element2.select("td").get(3) != null) {
                            vehicleDetails.setRoadTaxPaidUpto(element2.select("td").get(3).text());
                        }
                    }
                    vehicleDetails.setRegistrationNo(str3);
                    vehicleDetails.setRegistrationDate(text);
                    vehicleDetails.setChassisNo(text2);
                    vehicleDetails.setEngineNo(text3);
                    vehicleDetails.setOwnerName(text4);
                    vehicleDetails.setFuelType(trim2);
                    vehicleDetails.setMakerModel(text6);
                    vehicleDetails.setVehicleClass(text5);
                    this.callback.onResponse(vehicleDetails);
                    return;
                }
                try {
                    this.callback.onError("Error in your request, please try again later");
                    return;
                } catch (Exception unused3) {
                    this.callback.onError("Error in your request, please try again later");
                    return;
                }
//                this.callback.onError("Error in your request, please try again later");
            }
        }
    }

    private void parseUrl() {
        try {
            Connection connect = Jsoup.connect(GlobalReferenceEngine.localSourceInitUrl);
            connect.followRedirects(true);
            connect.ignoreHttpErrors(true);
            connect.method(Connection.Method.GET);
            connect.timeout(VehicleUtils.SERVER_TIMEOUT);
            Connection.Response execute = connect.execute();
            this.cookies = execute.cookies();
            Document parse = Jsoup.parse(execute.body());
            Elements elementsByAttributeValue = parse.getElementsByAttributeValue("name", "javax.faces.ViewState");
            if (elementsByAttributeValue.size() <= 0) {
                elementsByAttributeValue = parse.getElementsByAttributeValue("id", "j_id1:javax.faces.ViewState:0");
            }
            this.viewStateStr = elementsByAttributeValue.attr("value");
            Elements select = parse.getElementsByAttributeValueStarting("id", "form_rcdl:j_idt").select("button");
            if (select == null || select.size() <= 0) {
                return;
            }
            this.btnIdStr = select.get(0).attr("id").trim();
        } catch (Exception e) {

        }
    }

    public interface IResponseCallback {
        void onError(String str);

        void onNotFound();

        void onResponse(VehicleDetails vehicleDetails);
    }
}
