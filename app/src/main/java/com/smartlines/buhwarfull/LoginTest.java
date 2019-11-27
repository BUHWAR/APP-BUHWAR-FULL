package com.smartlines.buhwarfull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartlines.buhwarfull.colon.activity.MainActivity;
import com.smartlines.buhwarfull.guard.GuardMainActivity;

public class LoginTest extends AppCompatActivity {

    private Button btnLogin;
    private EditText mEmail, mPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
//
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    final String user_id = mAuth.getCurrentUser().getUid();

                    DatabaseReference usermode = FirebaseDatabase.getInstance().getReference().child("Users").child("Colonos");
                    Log.i("PARENTE", usermode.toString());
//                    Toast.makeText(LoginTest.this, usermode.getKey(), Toast.LENGTH_LONG).show();

//                    Intent intent = new Intent(LoginTest.this, GuardMainActivity.class);
//                    if (usermode.getKey() != null)
//                        intent = new Intent(LoginTest.this, MainActivity.class);
//
//                    startActivity(intent);
//                    finish();

                    usermode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Intent intent = new Intent(LoginTest.this, GuardMainActivity.class);
                            if (dataSnapshot.hasChild(user_id)) {
                                intent = new Intent(LoginTest.this, MainActivity.class);
                                //Toast.makeText(LoginTest.this, "SIP", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(LoginTest.this, "NOP", Toast.LENGTH_SHORT).show();
                            }

                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        };


        btnLogin = (Button) findViewById(R.id.btnLogin);
        mEmail = (EditText) findViewById(R.id.txtUser);
        mPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
//                Intent intent = new Intent(LoginTest.this, GuardMainActivity.class);
//                startActivity(intent);

                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginTest.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginTest.this, "Sus credenciales no coinciden con nuestros registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginTest.this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}

