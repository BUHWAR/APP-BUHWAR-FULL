package com.smarltines.buhwarfull.colon.activity.detailGuard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarltines.buhwarfull.R;
import com.smarltines.buhwarfull.colon.adapter.QualifyAdapater;
import com.smarltines.buhwarfull.colon.ui.qualify.QualifyViewModel;
import com.smarltines.buhwarfull.model.GuardModel;

import java.util.ArrayList;
import java.util.List;

public class InfoTabFragment extends Fragment {

    private QualifyViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private List<GuardModel> list;
    private QualifyAdapater adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(QualifyViewModel.class);

        View root = (View) inflater.inflate(R.layout.fragment_info_guardia, container, false);

        return root;

    }


}
