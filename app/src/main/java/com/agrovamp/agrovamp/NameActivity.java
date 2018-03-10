package com.agrovamp.agrovamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NameActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button nextButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        Intent intent = getIntent();
        String mobileNumber = intent.getStringExtra("mobile_number");

        if (firebaseUser != null) {
            startActivity(new Intent(NameActivity.this, UserMainActivity.class));
            finish();
        }

        firstNameEditText = (EditText) findViewById(R.id.first_name_edit_text);
        lastNameEditText = (EditText) findViewById(R.id.last_name_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();

                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_your_name), Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });
    }
}
