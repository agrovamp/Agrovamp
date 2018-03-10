package com.agrovamp.agrovamp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FarmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FarmFragment newInstance() {
        FarmFragment fragment = new FarmFragment();
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
        View view = inflater.inflate(R.layout.fragment_farm, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction(getString(R.string.your_farm));
        }

        ViewPager pager = (ViewPager) view.findViewById(R.id.view_pager);
        setUpViewPager(pager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.detail_tabs);
        tabLayout.setupWithViewPager(pager);
        return view;
    }

    public void setUpViewPager(ViewPager viewPager) {
        FarmTabAdapter adapter = new FarmTabAdapter(getChildFragmentManager());
        adapter.addFragment(SummaryFragment.newInstance(), getString(R.string.summary));
        adapter.addFragment(ControlsFragment.newInstance(), getString(R.string.controls));
        adapter.addFragment(DroneFragment.newInstance(), getString(R.string.drone));
        viewPager.setAdapter(adapter);
    }

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
