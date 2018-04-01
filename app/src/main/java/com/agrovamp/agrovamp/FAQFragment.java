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
 * {@link FAQFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Context context;
    private RecyclerView faqRecyclerView;

    public FAQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FAQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQFragment newInstance() {
        FAQFragment fragment = new FAQFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction(getString(R.string.faq));
        }
        faqRecyclerView = view.findViewById(R.id.faqRecyclerView);
        List<Question> questions = getQuestions();
        QuestionAdapter adapter = new QuestionAdapter(context, questions);
        faqRecyclerView.setAdapter(adapter);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;    }

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

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<Question>();

        List<Answer> ans1 = new ArrayList<Answer>();
        ans1.add(new Answer(getString(R.string.ans1)));
        questions.add(new Question(getString(R.string.ques1), ans1));

        List<Answer> ans2 = new ArrayList<Answer>();
        ans2.add(new Answer(getString(R.string.ans2)));
        questions.add(new Question(getString(R.string.ques2), ans2));

        List<Answer> ans3 = new ArrayList<Answer>();
        ans3.add(new Answer(getString(R.string.ans3)));
        questions.add(new Question(getString(R.string.ques3), ans3));

        List<Answer> ans4 = new ArrayList<Answer>();
        ans4.add(new Answer(getString(R.string.ans4)));
        questions.add(new Question(getString(R.string.ques4), ans4));

        List<Answer> ans5 = new ArrayList<Answer>();
        ans5.add(new Answer(getString(R.string.ans5)));
        questions.add(new Question(getString(R.string.ques5), ans5));

        List<Answer> ans6 = new ArrayList<Answer>();
        ans6.add(new Answer(getString(R.string.ans6)));
        questions.add(new Question(getString(R.string.ques6), ans6));

        List<Answer> ans7 = new ArrayList<Answer>();
        ans7.add(new Answer(getString(R.string.ans7)));
        questions.add(new Question(getString(R.string.ques7), ans7));

        List<Answer> ans8 = new ArrayList<Answer>();
        ans8.add(new Answer(getString(R.string.ans8)));
        questions.add(new Question(getString(R.string.ques8), ans8));

        List<Answer> ans9 = new ArrayList<Answer>();
        ans9.add(new Answer(getString(R.string.ans9)));
        questions.add(new Question(getString(R.string.ques9), ans9));

        List<Answer> ans10 = new ArrayList<Answer>();
        ans10.add(new Answer(getString(R.string.ans10)));
        questions.add(new Question(getString(R.string.ques10), ans10));
        return questions;
    }
}
