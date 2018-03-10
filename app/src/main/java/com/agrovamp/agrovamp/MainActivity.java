package com.agrovamp.agrovamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Spinner languageSpinner;
    private Button nextButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            startActivity(new Intent(this, UserMainActivity.class));
            finish();
        }

        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
        nextButton = (Button) findViewById(R.id.next_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String language = languageSpinner.getSelectedItem().toString();
                if (TextUtils.isEmpty(language)) {
                    Toast.makeText(MainActivity.this, R.string.select_a_language, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.user_selected) + language, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MobileNumberActivity.class));
                    finish();
                }
            }
        });
    }
}
