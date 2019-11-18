package com.smartlines.buhwarfull.guard.ui.incidents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smartlines.buhwarfull.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class IncidentsFragment extends Fragment {

    private IncidentsViewModel sendViewModel;
    private TextView txtDateInc;
    private TextView txtTimeInc;
    private Spinner spinnerPlace;
    private Spinner spinnerViews;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(IncidentsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_incidents, container, false);
        txtTimeInc = root.findViewById(R.id.txtTimeInc);
        txtDateInc = root.findViewById(R.id.txtDateInc);
        spinnerPlace = root.findViewById(R.id.spinnerPlace);
        spinnerViews = root.findViewById(R.id.spinnerViews);
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strTime = "Hora actual : " + mdformat.format(calender.getTime());
        SimpleDateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = "Fecha : "+dateFormat.format(calender.getTime());

        String[] opcPlace = {"Domicilio particular","Area de esparcimeinto","Area publica","Otro"};
        String[] opcviews = {"Robo a casa habitación"," Robo de vehículo "," Robo a transeúnte",
                "Daño en propiedad ajena","Allanamiento de morada"," Lesiones","Alteración del orden público"};

        ArrayAdapter adapterPlace = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opcPlace);
        ArrayAdapter adapterviews = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opcviews);

        spinnerViews.setAdapter(adapterviews);
        spinnerPlace.setAdapter(adapterPlace);
        txtTimeInc.setText(strTime);
        txtDateInc.setText(strDate);

        return root;
    }
}