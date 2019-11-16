package com.smarltines.buhwarfull.colon.ui.qualify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.smarltines.buhwarfull.R;

public class GuardFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);

        View root = (View) inflater.inflate(R.layout.fragment_qualify, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvGuard);
        return root;
    }
}