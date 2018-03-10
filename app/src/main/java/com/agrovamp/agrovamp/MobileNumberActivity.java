package com.agrovamp.agrovamp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MobileNumberActivity extends AppCompatActivity {

    private EditText phoneEditText;
    private Button nextButton;
    private ProgressDialog dialog;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        dialog = new ProgressDialog(getApplicationContext());

        if (firebaseUser != null) {
            // Go to main activity
            startActivity(new Intent(MobileNumberActivity.this, UserMainActivity.class));
            finish();
        }

        Intent intent = getIntent();
        String qrId = intent.getStringExtra("qr_id");

        phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

//       / reference.child(qrId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("user").child("mobilenumber").exists()) {
//                    // It means the user has logged in before.
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_a_valid_mobile_number), Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthProvider.getInstance(firebaseAuth).verifyPhoneNumber(
                            phone,
                            60,
                            TimeUnit.SECONDS,
                            MobileNumberActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.verification_success), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MobileNumberActivity.this,  UserMainActivity.class)
                                            .putExtra("mobile_number", phone));
                                    finish();
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.verification_failed), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                }
            }
        };
        nextButton.setOnClickListener(clickListener);
    }
}
