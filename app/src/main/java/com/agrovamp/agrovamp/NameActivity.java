package com.agrovamp.agrovamp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.Attributes;

public class NameActivity extends AppCompatActivity {

    public static final String TAG = NameActivity.class.getSimpleName();

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button nextButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        dialog = new ProgressDialog(NameActivity.this);

        firstNameEditText = (EditText) findViewById(R.id.first_name_edit_text);
        lastNameEditText = (EditText) findViewById(R.id.last_name_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

        dialog.show();

        Intent intent = getIntent();
        String mobileNumber = intent.getStringExtra(MobileNumberActivity.KEY_MOBILE);
        final String qrId = intent.getStringExtra(QRCodeActivity.KEY_QR);

        Log.d(TAG, "QR: " + qrId);

        reference.child(qrId).child("user").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dialog.dismiss();
                    startActivity(new Intent(NameActivity.this, UserMainActivity.class)
                            .putExtra(QRCodeActivity.KEY_QR, qrId));
                    Log.d(TAG, "QR: " + qrId);
                    finish();
                } else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            }
        });

        firstNameEditText = (EditText) findViewById(R.id.first_name_edit_text);
        lastNameEditText = (EditText) findViewById(R.id.last_name_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();

                if (TextUtils.isEmpty(firstName)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_your_name), Toast.LENGTH_SHORT).show();
                } else {
                    String name = firstName + " " + lastName;
                    reference.child(qrId).child("user").child("name").setValue(name);
                    startActivity(new Intent(NameActivity.this, UserMainActivity.class)
                            .putExtra(QRCodeActivity.KEY_QR, qrId)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });
    }
}
