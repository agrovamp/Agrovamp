package com.agrovamp.agrovamp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
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

    public static final String TAG = MobileNumberActivity.class.getSimpleName();

    public static final String KEY_MOBILE = "KEY_MOBILE";
    public static final String KEY_QR = "KEY_QR";

    private EditText phoneEditText;
    private Button nextButton;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ProgressDialog dialog;

    public String qrId;
    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        dialog = new ProgressDialog(MobileNumberActivity.this);
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setIndeterminate(true);


        if (firebaseUser != null) {
            // Go to main activity
            startActivity(new Intent(MobileNumberActivity.this, UserMainActivity.class)
            .putExtra(QRCodeActivity.KEY_QR, qrId));
            finish();
        }

        final Intent intent = getIntent();
        qrId = intent.getStringExtra(QRCodeActivity.KEY_QR);

        Log.d(TAG, "QR: " + qrId);

        phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_a_valid_mobile_number), Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phone,
                            60,
                            TimeUnit.SECONDS,
                            MobileNumberActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.verification_success), Toast.LENGTH_SHORT).show();
                                    signInWithPhoneCredentials(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.verification_failed), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    Toast.makeText(getApplicationContext(), R.string.verification, Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                }
            }
        };
        nextButton.setOnClickListener(clickListener);
    }

    private void signInWithPhoneCredentials(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Log.d(TAG, "QR: " + qrId);
                            reference.child(qrId).child("user").child("phone").setValue(phone);
                            Intent i = new Intent(MobileNumberActivity.this, NameActivity.class);
                            i.putExtra(QRCodeActivity.KEY_QR, qrId);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.verification_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
