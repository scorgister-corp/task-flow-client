package net.scorgister.android.taskflow;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.scorgister.android.taskflow.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
/*
        Utils.createAPIConnection("http://192.168.1.95:8100");
       Utils.get("/version", new RunnableUtil<JSONObject>() {
            @Override
            public void exec(JSONObject... data) {
                if(data[0] == null)
                    return;
                Log.println(Log.INFO, "TASKFLOW", data[0].toString());
            }
        });

        TextView text = findViewById(R.id.welcome);
        text.setTextSize(20);
        text.setText("Welcome, " + "Scorgister");

        ImageButton view = findViewById(R.id.profileIcon);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin(v);
            }
        });
*/
    }

    public void startLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}