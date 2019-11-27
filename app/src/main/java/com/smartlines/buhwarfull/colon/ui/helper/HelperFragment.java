package com.smartlines.buhwarfull.colon.ui.helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smartlines.buhwarfull.R;

import static com.smartlines.buhwarfull.util.Constanst.INFO;
import static com.smartlines.buhwarfull.util.Constanst.MISION;
import static com.smartlines.buhwarfull.util.Constanst.OBJETIVO;
import static com.smartlines.buhwarfull.util.Constanst.PERSONA;
import static com.smartlines.buhwarfull.util.Constanst.VISION;

public class HelperFragment extends Fragment {

    private HelperViewModel shareViewModel;
    private Button btnInfo;
    private Button btnMision;
    private Button btnVision;
    private Button btnPersonal;
    private Button btnObjetivo;
    private TextView txtInfo;
    private TextView txtMision;
    private TextView txtVision;
    private TextView txtPersonal;
    private TextView txtObjetivo;
    private boolean flagInfo = false;
    private boolean flagMision = false;
    private boolean flagVision = false;
    private boolean flagPersonal = false;
    private boolean flagObjetivo = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(HelperViewModel.class);
        View root = inflater.inflate(R.layout.fragment_helper, container, false);
        btnInfo = (Button) root.findViewById(R.id.btnInfo);
        btnMision = (Button) root.findViewById(R.id.btnMision);
        btnVision = (Button) root.findViewById(R.id.btnVsion);
        btnPersonal = (Button) root.findViewById(R.id.btnPersonal);
        btnObjetivo = (Button) root.findViewById(R.id.btnObjetivo);
        txtInfo = (TextView) root.findViewById(R.id.txtInfo);
        txtMision = (TextView) root.findViewById(R.id.txtMision);
        txtVision = (TextView) root.findViewById(R.id.txtVision);
        txtPersonal = (TextView) root.findViewById(R.id.txtPerosnal);
        txtObjetivo = (TextView) root.findViewById(R.id.txtObjetivo);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!flagInfo) {
                    txtInfo.setText(INFO);
                    flagInfo = true;
                } else {
                    txtInfo.setText("");
                    flagInfo = false;
                }
            }
        });

        btnMision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!flagMision) {
                    txtMision.setText(MISION);
                    flagMision = true;
                } else {
                    txtMision.setText("");
                    flagMision = false;


                }
            }
        });

        btnVision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!flagVision) {
                    txtVision.setText(VISION);

                    flagVision = true;
                } else {
                    txtVision.setText("");
                    flagVision = false;


                }
            }
        });


        btnPersonal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!flagPersonal) {
                    txtPersonal.setText(PERSONA);
                    flagPersonal = true;
                } else {
                    txtPersonal.setText("");
                    flagPersonal = false;


                }
            }
        });

        btnObjetivo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!flagObjetivo) {
                    txtObjetivo.setText(OBJETIVO);
                    flagObjetivo = true;
                } else {
                    txtObjetivo.setText("");
                    flagObjetivo = false;


                }
            }
        });
        return root;
    }
}