package com.example.heartmonitor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebService {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    final OkHttpClient client = new OkHttpClient();

    public String Post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        System.out.println(body.contentType());
        System.out.println(body.toString());
        System.out.println(request.toString());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String Get(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }

    public void Get2() throws IOException {
        URL url = new URL("https://wssecurity.herokuapp.com/api-seguridad/componentes/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            System.out.println("Se pudo jaja: " + in.toString());
        }
        finally {
            urlConnection.disconnect();
        }
    }
}
