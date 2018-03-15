package com.agrovamp.agrovamp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.util.Locale;

public class QRCodeActivity extends AppCompatActivity {

    public static final String KEY_QR = "KEY_QR";

    private EditText qrIdEditText;
    private Button nextButton;
    private Button scanQRButton;

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private String qrCode;

    private IntentIntegrator qrIntent;
    private PreferenceManager preferenceManager;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        dialog = new ProgressDialog(QRCodeActivity.this);
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setIndeterminate(true);

        preferenceManager = new PreferenceManager(this);

        qrIntent = new IntentIntegrator(this);

        if (preferenceManager.isQRStored()) {
            qrCode = preferenceManager.getQRCode();
        }
        if (firebaseUser != null) {
            startActivity(new Intent(QRCodeActivity.this, UserMainActivity.class)
            .putExtra(QRCodeActivity.KEY_QR, qrCode));
            finish();
        }

        qrIdEditText = (EditText) findViewById(R.id.qr_id_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);
        scanQRButton = (Button) findViewById(R.id.scan_qr_button);

        nextButton.setEnabled(false);

        qrIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    nextButton.setEnabled(false);
                } else {
                    nextButton.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    nextButton.setEnabled(false);
                } else {
                    nextButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        scanQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrIntent.initiateScan();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                qrCode = qrIdEditText.getText().toString();
                reference.child(qrCode).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            preferenceManager.storeQRCode(qrCode);
                            Log.d(UserMainActivity.TAG, "QR: " + qrCode);
                            dialog.dismiss();
                            startActivity(new Intent(QRCodeActivity.this, MobileNumberActivity.class).putExtra(KEY_QR, qrCode));
                        } else {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), getString(R.string.no_result_found), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (preferenceManager.isStored()) {
            setupLocale(preferenceManager.getLanguageCode());
        }

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getApplicationContext(), R.string.no_result_found, Toast.LENGTH_SHORT).show();
            } else {
                qrCode = result.getContents();
                dialog.show();
                reference.child(qrCode).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            dialog.dismiss();
                            preferenceManager.storeQRCode(qrCode);
                            startActivity(new Intent(QRCodeActivity.this, MobileNumberActivity.class).putExtra(KEY_QR, qrCode));
                            finish();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), getString(R.string.no_result_found), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setupLocale(String languageCode) {
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(languageCode));
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }
}
