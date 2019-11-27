package com.smartlines.buhwarfull;


import android.content.Intent;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartlines.buhwarfull.colon.activity.MainActivity;
import com.smartlines.buhwarfull.guard.GuardMainActivity;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(500);

        mAuth = FirebaseAuth.getInstance();

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
                            Intent intent = new Intent(SplashActivity.this, GuardMainActivity.class);
                            if (dataSnapshot.hasChild(user_id)) {
                                intent = new Intent(SplashActivity.this, MainActivity.class);
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
                }else{
                    startActivity(new Intent(SplashActivity.this,LoginTest.class));
                }
            }
        };

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
