package com.smartlines.buhwarfull.colon.ui.qualify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartlines.buhwarfull.R;
import com.smartlines.buhwarfull.colon.adapter.QualifyAdapater;
import com.smartlines.buhwarfull.model.GuardModel;

import java.util.ArrayList;
import java.util.List;

public class QualifyFragment extends Fragment {

    private QualifyViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private List<GuardModel> list;
    private QualifyAdapater adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(QualifyViewModel.class);

        View root = (View) inflater.inflate(R.layout.fragment_qualify, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvGuard);
        list = new ArrayList<GuardModel>();
        GuardModel guardia1 = new GuardModel("Guardia 1", "1234", "Lopez@gmail.com", "Responsable de guardias noctunas", "", false);
        GuardModel guardia2 = new GuardModel("Guardia 2", "1234", "Lopez@gmail.com", "Responsable de guardias noctunas", "", false);
        GuardModel guardia3 = new GuardModel("Guardia 3", "1234", "Lopez@gmail.com", "Responsable de guardias noctunas", "", false);
        list.add(guardia1);
        list.add(guardia2);
        list.add(guardia3);


        fillAdapter(list);
        return root;

    }

    public void fillAdapter(List<GuardModel> list) {
        adapter = new QualifyAdapater(list, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
