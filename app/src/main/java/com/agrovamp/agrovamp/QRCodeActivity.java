package com.agrovamp.agrovamp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QRCodeActivity extends AppCompatActivity {

    public static final String KEY_QR = "KEY_QR";

    private EditText qrIdEditText;
    private Button nextButton;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        if (firebaseUser != null) {
            startActivity(new Intent(QRCodeActivity.this, UserMainActivity.class));
            finish();
        }

        qrIdEditText = (EditText) findViewById(R.id.qr_id_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qrId = qrIdEditText.getText().toString();
                if (qrId == null)
                    qrId = "12345";

                Log.d(UserMainActivity.TAG, "QRActivity: QR: " + qrId);
                startActivity(new Intent(QRCodeActivity.this, MobileNumberActivity.class).putExtra(KEY_QR, qrId));
                finish();
            }
        });
    }
}
