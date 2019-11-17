package com.smarltines.buhwarfull.colon.ui.resgister;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.smarltines.buhwarfull.R;
import com.smarltines.buhwarfull.colon.adapter.QualifyAdapater;
import com.smarltines.buhwarfull.colon.adapter.RegisterAdapater;
import com.smarltines.buhwarfull.colon.ui.visit.VisitViewModel;
import com.smarltines.buhwarfull.model.GuardModel;
import com.smarltines.buhwarfull.model.RegisterModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<RegisterModel> list;
    private RegisterAdapater adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvRegister);
        list = new ArrayList<RegisterModel>();
        RegisterModel registerModel1 = new RegisterModel("Martes 6","...");
        RegisterModel registerModel2 = new RegisterModel("Miercoles 7","...");
        RegisterModel registerModel3 = new RegisterModel("Jueves 6","...");
        list.add(registerModel1);
        list.add(registerModel2);
        list.add(registerModel3);



        fillAdapter(list);
        return root;

    }

    public void  fillAdapter(List<RegisterModel> list){
        adapter = new RegisterAdapater(list,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
