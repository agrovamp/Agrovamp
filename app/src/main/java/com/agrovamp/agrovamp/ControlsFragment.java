package com.agrovamp.agrovamp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ControlsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String qrId;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private Switch motorSwitch;
    private Switch pumpSwitch;

    public ControlsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ControlsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlsFragment newInstance() {
        ControlsFragment fragment = new ControlsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FarmFragment farmFragment = (FarmFragment) getParentFragment();
        qrId = farmFragment.getQrId();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(qrId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_controls, container, false);
        motorSwitch = view.findViewById(R.id.motor_switch);
        pumpSwitch = view.findViewById(R.id.pump_switch);

        reference.child("controls").child("motor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    boolean checked = (boolean) dataSnapshot.getValue();
                    motorSwitch.setChecked(checked);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference.child("controls").child("pump").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    boolean checked = (boolean) dataSnapshot.getValue();
                    pumpSwitch.setChecked(checked);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        motorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                reference.child("controls").child("motor").setValue(isChecked);
                if(isChecked)
                    Toast.makeText(getContext(), "Motor turned ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Motor turned OFF", Toast.LENGTH_SHORT).show();
            }
        });

        pumpSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                reference.child("controls").child("pump").setValue(isChecked);
                if(isChecked)
                    Toast.makeText(getContext(), "Pump turned ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Pump turned OFF", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
