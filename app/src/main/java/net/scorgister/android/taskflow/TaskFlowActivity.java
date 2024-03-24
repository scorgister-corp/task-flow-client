package net.scorgister.android.taskflow;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TaskFlowActivity extends AppCompatActivity {

    public static final String CONFIG_FILE_NAME = "config.tf";

    public void saveConfig(String key, String data) {
        try {
            FileInputStream in = openFileInput(CONFIG_FILE_NAME);
            StringBuilder stringb= new StringBuilder();
            String value = null;
            int content;
            while ((content=in.read())!=-1){
                value = String.valueOf(stringb.append((char)content));
            }

            Log.println(Log.INFO, "TS", value);
        }catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createConfigFile() throws IOException {
        FileOutputStream fso = openFileOutput(CONFIG_FILE_NAME, MODE_PRIVATE);
        fso.write("Hello world!".getBytes());
        fso.close();
    }

}
