package com.agrovamp.agrovamp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView productRecyclerView;
    private ProductAdapter adapter;

    private Context context;
    private List<Product> products;

    public BuyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BuyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyFragment newInstance() {
        BuyFragment fragment = new BuyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        context = getParentFragment().getActivity().getApplicationContext();
        products = getDummyProducts();
        adapter = new ProductAdapter(context, products);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        productRecyclerView = view.findViewById(R.id.buy_list);
        productRecyclerView.setAdapter(adapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String title) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public List<Product> getDummyProducts() {
        List<Product> products = new ArrayList<Product>();

        products.add(new Product(context.getString(R.string.tractor), 4000, "tractor"));
        products.add(new Product(context.getString(R.string.knife), 150, "knife"));
        products.add(new Product(context.getString(R.string.showel), 350, "showel"));
        products.add(new Product(context.getString(R.string.rice), 140, "rice"));
        products.add(new Product(context.getString(R.string.axe), 400, "axe"));
        products.add(new Product(context.getString(R.string.tractor), 4000, "tractor"));
        products.add(new Product(context.getString(R.string.knife), 150, "knife"));
        products.add(new Product(context.getString(R.string.showel), 350, "showel"));
        products.add(new Product(context.getString(R.string.rice), 140, "rice"));
        products.add(new Product(context.getString(R.string.axe), 400, "axe"));

        return products;
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
