package com.smartlines.buhwarfull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartlines.buhwarfull.colon.activity.MainActivity;
import com.smartlines.buhwarfull.guard.GuardMainActivity;

public class LoginTest extends AppCompatActivity {
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(LoginTest.this, GuardMainActivity.class);
                startActivity(intent);
            }
        });
    }

}

