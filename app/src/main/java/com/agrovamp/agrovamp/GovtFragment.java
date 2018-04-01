package com.agrovamp.agrovamp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FAQFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GovtFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public GovtFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FAQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GovtFragment newInstance() {
        GovtFragment fragment = new GovtFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_govt, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction(getString(R.string.government_schemes));
        }
        Button button1 = (Button) view.findViewById(R.id.scheme1_button);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://pib.nic.in/newsite/mbErel.aspx?relid=96201"));
                startActivity(browserIntent);
            }
        });
        Button button2 = (Button) view.findViewById(R.id.scheme2_button);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://dahd.nic.in/related-links/livestock-insurance-0"));
                startActivity(browserIntent);
            }
        });
        Button button3 = (Button) view.findViewById(R.id.scheme3_button);
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://dahd.nic.in/related-links/centrally-sponsored-national-scheme-welfare-fishermen"));
                startActivity(browserIntent);
            }
        });
        Button button4 = (Button) view.findViewById(R.id.scheme4_button);
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://rkvy.nic.in/"));
                startActivity(browserIntent);
            }
        });
        Button button5 = (Button) view.findViewById(R.id.scheme5_button);
        button5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bankbazaar.com/kisan-credit-card.html"));
                startActivity(browserIntent);
            }
        });
        Button button6 = (Button) view.findViewById(R.id.scheme6_button);
        button6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mahindrafinance.com/tractor-loans.aspx"));
                startActivity(browserIntent);
            }
        });
        Button button7 = (Button) view.findViewById(R.id.scheme7_button);
        button7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sbi.co.in/portal/web/agriculture-banking/agricultural-gold-loans"));
                startActivity(browserIntent);
            }
        });

        Button button8 = (Button) view.findViewById(R.id.scheme8_button);
        button8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sbi.co.in/portal/web/agriculture-banking"));
                startActivity(browserIntent);
            }
        });


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String title) {
        if (mListener != null) {
            mListener.onFragmentInteraction(title);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String title);
    }


}
