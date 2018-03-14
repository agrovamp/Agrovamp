package com.agrovamp.agrovamp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment {

    public static final String TAG = "SummaryFragment";
    private TextView temperatureTextView;
    private TextView moistureTextView;
    private TextView humidityTextView;

    private User user;
    private DatabaseReference reference;
    private FirebaseDatabase database;

    public String qrId;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance() {
        SummaryFragment fragment = new SummaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FarmFragment farmFragment = (FarmFragment) getParentFragment();
        qrId = farmFragment.getQrId();

        database = FirebaseDatabase.getInstance();
        Log.d(TAG, "QR: " + qrId);
        reference = database.getReference().child(qrId).child("sensors");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        temperatureTextView = (TextView) view.findViewById(R.id.temperture_textview);
        moistureTextView = (TextView) view.findViewById(R.id.moisture_textview);
        humidityTextView = (TextView) view.findViewById(R.id.humidity_textview);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Sensor sensor = dataSnapshot.getValue(Sensor.class);
                    temperatureTextView.setText(Math.round(sensor.getTemperature()) + "\'C");
                    humidityTextView.setText(Math.round(sensor.getHumidity()) + "");
                    moistureTextView.setText(Math.round(sensor.getMoisture()) + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
