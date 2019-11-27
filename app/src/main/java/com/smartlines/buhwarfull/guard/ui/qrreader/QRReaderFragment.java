package com.smartlines.buhwarfull.guard.ui.qrreader;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smartlines.buhwarfull.R;
import com.smartlines.buhwarfull.guard.GuardMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class QRReaderFragment extends Fragment implements View.OnClickListener {

    private QrreaderViewModel mViewModel;

    private Button buttonScan;
    public static TextView textViewName, textViewAddress;

    //qr code scanner object
    private IntentIntegrator qrScan;

    public static QRReaderFragment newInstance() {
        return new QRReaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.qrreader_fragment, container, false);

        buttonScan = (Button) root.findViewById(R.id.buttonScan);
        textViewName = (TextView) root.findViewById(R.id.textViewName);
        textViewAddress = (TextView) root.findViewById(R.id.textViewAddress);

        buttonScan.setOnClickListener(this);

        //intializing scan object
        qrScan = new IntentIntegrator(getActivity());
        qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        qrScan.setBeepEnabled(false);
        //qrScan.setOrientationLocked(false);

        qrScan.initiateScan();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Getting the scan results
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            //if qrcode has nothing in it
//            if (result.getContents() == null) {
//                Toast.makeText(getActivity(), "Result Not Found", Toast.LENGTH_LONG).show();
//            } else {
//                //if qr contains data
//                try {
//                    //converting the data to json
//                    Toast.makeText(getActivity(),"DATOS:"+ result.getContents(),Toast.LENGTH_LONG).show();
//                    JSONObject obj = new JSONObject(result.getContents());
//                    //setting values to textviews
//                    textViewName.setText(obj.getString("name"));
//                    textViewAddress.setText(obj.getString("address"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    //if control comes here
//                    //that means the encoded format not matches
//                    //in this case you can display whatever data is available on the qrcode
//                    //to a toast
//                    Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_LONG).show();
//                }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }

    //{"name":"Belal Khan", "address":"Ranchi,Jhark"}
}
