package com.smartlines.buhwarfull.guard.ui.incidents;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smartlines.buhwarfull.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class IncidentsFragment extends Fragment {

    private TextView txtDateInc;
    private TextView txtTimeInc;
    private TextView txtName;
    private Spinner spinnerPlace;
    private Spinner spinnerViews;
   private FirebaseFirestore firebaseDatabase;
    private Button btnSend;
    private  Map<String, Object> incidents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_incidents, container, false);
        txtTimeInc = root.findViewById(R.id.txtTimeInc);
        txtDateInc = root.findViewById(R.id.txtDateInc);
        spinnerPlace = root.findViewById(R.id.spinnerPlace);
        spinnerViews = root.findViewById(R.id.spinnerViews);
        txtName = root.findViewById(R.id.txtGuardName);
        btnSend = root.findViewById(R.id.btnSendInc);
        firebaseDatabase = FirebaseFirestore.getInstance();

        Calendar calender = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strTime = "Hora actual : " + mdformat.format(calender.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = "Fecha : " + dateFormat.format(calender.getTime());

        String[] opcPlace = {"Domicilio particular", "Area de esparcimeinto", "Area publica", "Otro"};
        String[] opcviews = {"Robo a casa habitación", " Robo de vehículo ", " Robo a transeúnte",
                "Daño en propiedad ajena", "Allanamiento de morada", " Lesiones", "Alteración del orden público"};

        ArrayAdapter adapterPlace = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opcPlace);
        ArrayAdapter adapterviews = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opcviews);

        spinnerViews.setAdapter(adapterviews);
        spinnerPlace.setAdapter(adapterPlace);
        txtTimeInc.setText(strTime);
        txtDateInc.setText(strDate);




        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                incidents = new HashMap<>();
                incidents.put("time", txtTimeInc.getText());
                incidents.put("date", txtDateInc.getText());
                incidents.put("observations", spinnerViews.getSelectedItem().toString());
                incidents.put("place", spinnerPlace.getSelectedItem().toString());
                incidents.put("nameGuard", "Test");

             firebaseDatabase.collection("incidents")
                        .add(incidents)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.v("IncidentsFragment", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.v("IncidentsFragment", "Error adding document", e);
                            }
                        });

            }
        });
        return root;
    }


}