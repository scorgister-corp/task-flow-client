package net.scorgister.android.taskflow;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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

    }

    public void startLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}