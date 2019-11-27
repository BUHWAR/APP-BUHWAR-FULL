package com.smartlines.buhwarfull.colon.ui.resgister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartlines.buhwarfull.R;
import com.smartlines.buhwarfull.colon.adapter.RegisterAdapater;
import com.smartlines.buhwarfull.model.RegisterModel;

import java.util.ArrayList;
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
        RegisterModel registerModel1 = new RegisterModel("Martes 6", "...");
        RegisterModel registerModel2 = new RegisterModel("Miercoles 7", "...");
        RegisterModel registerModel3 = new RegisterModel("Jueves 6", "...");
        list.add(registerModel1);
        list.add(registerModel2);
        list.add(registerModel3);


        fillAdapter(list);
        return root;

    }

    public void fillAdapter(List<RegisterModel> list) {
        adapter = new RegisterAdapater(list, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
