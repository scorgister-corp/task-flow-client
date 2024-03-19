package net.scorgister.android.taskflow.net;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class APIConnection {

    private String url;
    private String token;

    public APIConnection(String apiURL, String token) {
        this.url = apiURL;
        this.token = token;
    }

    public JSONObject get(String endpoint, boolean useToken) {
        try {
            return get(new URL(this.url + endpoint), useToken?this.token:"");
        } catch (MalformedURLException e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    public JSONObject get(String endpoint) {
       return get(endpoint, true);
    }

    public JSONObject post(String endpoint, boolean useToken, JSONObject data) {
        try {
            return post(new URL(this.url + endpoint), useToken?this.token:"", data);
        } catch (MalformedURLException e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    public JSONObject post(String endpoint, JSONObject data) {
       return post(endpoint, true, data);
    }

    public static JSONObject post(URL url, String token, JSONObject data) {
        HttpURLConnection connection = createConnection(url, token, "POST");
        assert connection != null;
        connection.setDoOutput(true);

        connection.setFixedLengthStreamingMode(data.toString().getBytes().length);
        connection.setChunkedStreamingMode(0);

        try {
            OutputStream outputPost = connection.getOutputStream();
            outputPost.write(data.toString().getBytes());
            outputPost.flush();
            outputPost.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append("\n");

            bufferedReader.close();

            return new JSONObject(stringBuilder.toString());
        }catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }finally {
            connection.disconnect();
        }
    }

    public static JSONObject get(URL url, String token) {
        HttpURLConnection connection = createConnection(url, token, "GET");
        assert connection != null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append("\n");

            bufferedReader.close();

            return new JSONObject(stringBuilder.toString());
        }catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }finally {
            connection.disconnect();
        }
    }

    public static HttpURLConnection createConnection(URL url, String token, String method) {
        HttpURLConnection connection = null;

        try {
            if (url.getProtocol().equals("https"))
                connection = (HttpsURLConnection) url.openConnection();
            else
                connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Charset", "utf-8,*");

            if(token != null && !token.isEmpty())
                connection.setRequestProperty("X-Application-Auth", token);

        return connection;
        }catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    public String getURL() {
        return url;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
