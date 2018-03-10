package com.agrovamp.agrovamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QRCodeActivity extends AppCompatActivity {

    private EditText qrIdEditText;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        qrIdEditText = (EditText) findViewById(R.id.qr_id_edit_text);
        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qrId = qrIdEditText.getText().toString();
                Intent intent = new Intent(QRCodeActivity.this, MobileNumberActivity.class);
                intent.putExtra("qr_id", qrId);
                startActivity(new Intent(intent));
                finish();
            }
        });
    }
}
