package com.agrovamp.agrovamp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Spinner languageSpinner;
    private Button nextButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        preferenceManager = new PreferenceManager(getApplicationContext());

        if (preferenceManager.isStored()) {
            Log.d(TAG, "isStored() called");
            String languageCode = preferenceManager.getLanguageCode();
            setupLocale(languageCode);
            startActivity(new Intent(this, QRCodeActivity.class));
            finish();
        }

//        if (firebaseUser != null) {
//            Log.d(TAG, "inside null check");
//            startActivity(new Intent(this, UserMainActivity.class));
//            finish();
//        }

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
                    String languageCode = "en";
                    if (preferenceManager.isStored()) {
                        languageCode = preferenceManager.getLanguageCode();
                    } else {
                        switch (language) {
                            case "English": languageCode = "en";
                                break;
                            case "Hindi": languageCode = "hi";
                                break;
                            case "Marathi": languageCode = "mr";
                                break;
                            case "Kannada": languageCode = "kn";
                                break;
                        }
                        preferenceManager.storeLanguageCode(languageCode);
                    }
                    setupLocale(languageCode);
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    finish();
                }
            }
        });
    }

    public void setupLocale(String languageCode) {
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(languageCode));
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }
}
