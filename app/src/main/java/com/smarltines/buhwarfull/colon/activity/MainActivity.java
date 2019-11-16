package com.smarltines.buhwarfull.colon.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.smarltines.buhwarfull.R;
import com.smarltines.buhwarfull.colon.ui.sos.SosFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SosFragment sosFragment;
    private FragmentManager fragmentManager;
    private Button btnPanic;
    private  DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sosFragment = SosFragment.newInstance();
        fragmentManager = getSupportFragmentManager();

         drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        btnPanic = (Button) findViewById(R.id.btnPanico);
        btnPanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setTitle("S.O.S");
                checkpermissioncall();
                drawer.closeDrawer(GravityCompat.START);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    ////PERMISOS Y ESAS MADRES
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido ahora puedes acceder a tus archivos ", Toast.LENGTH_LONG).show();
                //fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,visitaFragment).addToBackStack(null).commit();
            } else {
                Toast.makeText(this, "Oops permiso denegado", Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido ahora puedes hacer llamadas ", Toast.LENGTH_LONG).show();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment,sosFragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                Toast.makeText(this, "Oops permiso denegado", Toast.LENGTH_LONG).show();
                //finish();
            }
        }
    }


    private void checkpermissionwriteread() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permisos concedidos ahora puedes leer/escribir tus archivos ", Toast.LENGTH_LONG).show();
            //fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,visitaFragment).addToBackStack(null).commit();
        } else {
            solicitarPermiso(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    "Sin el permiso de leer/escribir archivos no puedes acceder a tus archivos", 100, this);
        }
    }

    private void checkpermissioncall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permisos concedidos ahora puedes hacer llamadas ", Toast.LENGTH_LONG).show();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment,sosFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            solicitarPermiso(new String[]{Manifest.permission.CALL_PHONE},
                    "Sin el permiso de llamadas no puedes hacer llamadas de panico", 200, this);
        }
    }


    private void solicitarPermiso(final String[] permisos, final String justificacion,
                                  final int requestCode, final Activity actividad) {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
//                permisos[0]) && ActivityCompat.shouldShowRequestPermissionRationale(actividad,
//                permisos[1])) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permisos[0])) {
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    permisos, requestCode);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad, permisos,requestCode);
        }
    }
}