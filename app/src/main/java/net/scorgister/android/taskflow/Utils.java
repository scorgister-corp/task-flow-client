package net.scorgister.android.taskflow;

import android.os.AsyncTask;
import android.util.JsonWriter;

import net.scorgister.android.taskflow.net.APIConnection;

import org.json.JSONObject;

import java.io.FileOutputStream;

public class Utils {

    private static APIConnection connection;

    public static void createAPIConnection(String baseURL) {
        createAPIConnection(baseURL, "");
    }
    public static void createAPIConnection(String baseURL, String token) {
        Utils.connection = new APIConnection(baseURL, token);
    }

    public static void setToken(String token) {
        connection.setToken(token);
    }

    public static JSONObject get(String endpoint, RunnableUtil<JSONObject> postExecution) {
        new AsyncTask<String, Void, JSONObject>() {

            @Override
            protected JSONObject doInBackground(String... strings) {
                return connection.get(endpoint);
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                postExecution.exec(jsonObject);
            }
        }.execute();
        return null;
    }

    public static JSONObject post(String endpoint, JSONObject data, RunnableUtil<JSONObject> postExecution) {
        new AsyncTask<String, Void, JSONObject>() {

            @Override
            protected JSONObject doInBackground(String... strings) {
                return connection.post(endpoint, data);
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                postExecution.exec(jsonObject);
            }
        }.execute();
        return null;
    }
}
