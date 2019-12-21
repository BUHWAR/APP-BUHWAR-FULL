package com.smartlines.buhwarfull.guard.ui.rondin;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartlines.buhwarfull.R;
import com.smartlines.buhwarfull.model.MapsModel;

import java.util.ArrayList;


public class RodinFragment extends Fragment implements OnMapReadyCallback {
    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private MapView mapView;
    private Context context;
    private FusedLocationProviderClient fuseLocationProvaiderClient;
    private Button button;
    private DatabaseReference firebaseDatabase;
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> tmpMarkers = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        View root = inflater.inflate(R.layout.fragment_rondin, container, false);
        mapView = (MapView) root.findViewById(R.id.mapView);
        button = (Button) root.findViewById(R.id.btnGetGeo);
        fuseLocationProvaiderClient = LocationServices.getFusedLocationProviderClient(context);


        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Mostrar diálogo explicativo
        } else {
            // Solicitar permiso
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }
        fuseLocationProvaiderClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {



                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.v("Geo","Latitud: "+location.getLatitude()+"Logitud: "+location.getLongitude());
                            firebaseDatabase.child("Guardia").push().child("lnglat").setValue(location.getLongitude()+","+location.getLatitude());
                        }
                    }

                });
        // Controles UI
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }


         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {



             }
         });



        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getCameraPosition();

        firebaseDatabase.child("Guardia").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(Marker marker:realTimeMarkers){
                    marker.remove();
                }

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    MapsModel mapsModel = snapshot.getValue(MapsModel.class);
                    Double latitude = mapsModel.getLatitude();
                    Double longitude = mapsModel.getLongitude();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitude,longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_police));
                    tmpMarkers.add(mMap.addMarker(markerOptions));
                }
                realTimeMarkers.clear();
                realTimeMarkers.addAll(tmpMarkers);
                new CountDownTimer(50000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        Log.e("seconds remaining: " ,""+ millisUntilFinished / 1000);
                        onMapReady(mMap);
                    }

                    public void onFinish() {
                        Toast.makeText(getContext(),"Puntos actualzados",Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


        @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(getContext(), "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void countDownTimer(){

    }

}