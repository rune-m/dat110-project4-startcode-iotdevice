package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class RestClient {

    public RestClient() {
        // TODO Auto-generated constructor stub
    }

    private static String logpath = "/accessdevice/log";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public void doPostAccessEntry(String message) {

        // TODO: implement a HTTP POST on the service to post the message

        AccessMessage accessMessage = new AccessMessage(message);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(accessMessage));

        Request request = new Request.Builder().url("http://localhost:8080" + logpath).post(body).build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String codepath = "/accessdevice/code";

    public AccessCode doGetAccessCode() {

        AccessCode code = null;

        // TODO: implement a HTTP GET on the service to get current access code

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8080" + codepath)
                .get()
                .build();

        System.out.println(request.toString());

        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            System.out.println(responseString);
            code = new Gson().fromJson(responseString, AccessCode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }
}
