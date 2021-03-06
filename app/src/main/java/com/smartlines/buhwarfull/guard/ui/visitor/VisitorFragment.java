package com.smartlines.buhwarfull.guard.ui.visitor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smartlines.buhwarfull.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class VisitorFragment extends Fragment {

    private VisitorViewModel shareViewModel;
    private TextView txtNameVisitante;
    private TextView txtLatsNameVistante;
    private TextView txtMatriculaVisitante;
    private TextView txtDateVistante;
    private TextView txtTimeVisitante;
    private TextView txtNameVistado;
    private TextView txtLatsNameVistado;
    private TextView txtAdressVisitado;
    private TextView txtEmalVisitado;
    private Button btnVisistante;
    private Button btnVisitado;
    private Spinner spinnerSopecha;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(VisitorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visitor, container, false);
        txtNameVisitante = root.findViewById(R.id.txtNameVisitante);
        txtLatsNameVistante = root.findViewById(R.id.txtLastNameVisitante);
        txtMatriculaVisitante = root.findViewById(R.id.txtMatriculaVisitante);
        txtDateVistante = root.findViewById(R.id.txtDateVisitante);
        txtTimeVisitante = root.findViewById(R.id.txtTimeVisitante);
        txtAdressVisitado = root.findViewById(R.id.txtxAdressVisitado);
        txtEmalVisitado = root.findViewById(R.id.txtEmailVisitado);
        btnVisistante = root.findViewById(R.id.btnSubmitVisitante);
        btnVisitado = root.findViewById(R.id.btnVisitado);
        spinnerSopecha = root.findViewById(R.id.spinnerSopecha);

        Calendar calender = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strTime = "" + mdformat.format(calender.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = "" + dateFormat.format(calender.getTime());
        txtDateVistante.setText(strDate);
        txtTimeVisitante.setText(strTime);



        txtAdressVisitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final String[] listItems = {"one", "two", "three", "four", "five"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose item");

                builder.setItems(listItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtAdressVisitado.setText( listItems[which]);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        String[] opcSospecha = {"Bajo", "Moderado", "Alto"};
        ArrayAdapter adapterPlace = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opcSospecha);
        spinnerSopecha.setAdapter(adapterPlace);
        return root;
    }
}