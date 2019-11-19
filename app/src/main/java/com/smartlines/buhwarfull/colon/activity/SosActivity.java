package com.smartlines.buhwarfull.colon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.smartlines.buhwarfull.R;

public class SosActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cv066,cv068,cv060,cv061,cv065;
    private TextView tv066,tv068,tv060,tv061,tv065;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        cv066=findViewById(R.id.cv066);
        cv068=findViewById(R.id.cv068);
        cv060=findViewById(R.id.cv060);
        cv061=findViewById(R.id.cv061);
        cv065=findViewById(R.id.cv065);

        tv066=findViewById(R.id.tv066);
        tv068=findViewById(R.id.tv068);
        tv060=findViewById(R.id.tv060);
        tv061=findViewById(R.id.tv061);
        tv065=findViewById(R.id.tv065);

        cv066.setOnClickListener(this);
        cv068.setOnClickListener(this);
        cv060.setOnClickListener(this);
        cv061.setOnClickListener(this);
        cv065.setOnClickListener(this);



        tv066.setOnClickListener(this);
        tv068.setOnClickListener(this);
        tv060.setOnClickListener(this);
        tv061.setOnClickListener(this);
        tv065.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        switch(v.getId()){
            case R.id.tv066:
            case R.id.cv066:
                callIntent.setData(Uri.parse("tel:066"));
                break;
            case R.id.tv068:
            case R.id.cv068:
                callIntent.setData(Uri.parse("tel:068"));
                break;
            case R.id.tv060:
            case R.id.cv060:
                callIntent.setData(Uri.parse("tel:060"));
                break;
            case R.id.tv061:
            case R.id.cv061:
                callIntent.setData(Uri.parse("tel:061"));
                break;
            case R.id.tv065:
            case R.id.cv065:
                callIntent.setData(Uri.parse("tel:065"));
                break;
        }

        if (ActivityCompat.checkSelfPermission(SosActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }
}
