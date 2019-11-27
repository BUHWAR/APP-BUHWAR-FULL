package com.smartlines.buhwarfull.guard;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smartlines.buhwarfull.LoginTest;
import com.smartlines.buhwarfull.R;
import com.smartlines.buhwarfull.colon.activity.MainActivity;
import com.smartlines.buhwarfull.colon.activity.SosActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static com.smartlines.buhwarfull.guard.ui.qrreader.QRReaderFragment.textViewAddress;
import static com.smartlines.buhwarfull.guard.ui.qrreader.QRReaderFragment.textViewName;

public class GuardMainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Button btnPanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_statistics, R.id.nav_helper, R.id.nav_rodin,
                R.id.nav_visitor, R.id.nav_incidents, R.id.nav_lector_qr)
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

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guard_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Cerrando Sessión", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent =  new Intent(GuardMainActivity.this, LoginTest.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                //
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
            solicitarPermiso(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    "Sin el permiso de leer/escribir archivos no puedes acceder a tus archivos", 100, this);
        }
    }

    private void checkpermissioncall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permisos concedidos ahora puedes hacer llamadas ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, SosActivity.class);
            startActivity(intent);
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
            ActivityCompat.requestPermissions(actividad, permisos, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    Toast.makeText(this, "DATOS: " + result.getContents(), Toast.LENGTH_LONG).show();
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    textViewName.setText(obj.getString("name"));
                    textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
