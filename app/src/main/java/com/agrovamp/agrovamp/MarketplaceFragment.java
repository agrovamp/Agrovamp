package com.agrovamp.agrovamp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MarketplaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarketplaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketplaceFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MarketplaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MarketplaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MarketplaceFragment newInstance() {
        MarketplaceFragment fragment = new MarketplaceFragment();
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
        View view = inflater.inflate(R.layout.fragment_marketplace, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction(getString(R.string.market_place));
        }

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.detail_tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void setUpViewPager(ViewPager viewPager) {
        MarketTabAdapter adapter = new MarketTabAdapter(getChildFragmentManager());
        adapter.addFragment(BuyFragment.newInstance(), getString(R.string.buy));
        adapter.addFragment(SellFragment.newInstance(), getString(R.string.sell));
        adapter.addFragment(RentFragment.newInstance(), getString(R.string.rent));
        viewPager.setAdapter(adapter);
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
