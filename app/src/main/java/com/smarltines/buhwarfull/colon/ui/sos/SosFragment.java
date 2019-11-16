package com.smarltines.buhwarfull.colon.ui.sos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smarltines.buhwarfull.R;

public class SosFragment extends Fragment implements View.OnClickListener {
    private TextView tv066,tv068,tv060,tv061,tv065;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_sos, container, false);

        tv066=inflate.findViewById(R.id.tv066);
        tv068=inflate.findViewById(R.id.tv068);
        tv060=inflate.findViewById(R.id.tv060);
        tv061=inflate.findViewById(R.id.tv061);
        tv065=inflate.findViewById(R.id.tv065);

        tv066.setOnClickListener(this);
        tv068.setOnClickListener(this);
        tv060.setOnClickListener(this);
        tv061.setOnClickListener(this);
        tv065.setOnClickListener(this);
        return inflate;
    }





    @Override
    public void onClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        switch(v.getId()){
            case R.id.tv066:
                callIntent.setData(Uri.parse("tel:066"));
                break;
            case R.id.tv068:
                callIntent.setData(Uri.parse("tel:068"));
                break;
            case R.id.tv060:
                callIntent.setData(Uri.parse("tel:060"));
                break;
            case R.id.tv061:
                callIntent.setData(Uri.parse("tel:061"));
                break;
            case R.id.tv065:
                callIntent.setData(Uri.parse("tel:065"));
                break;
        }

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    public static SosFragment newInstance() {
        SosFragment frag = new SosFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
}