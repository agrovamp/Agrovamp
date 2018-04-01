package com.agrovamp.agrovamp;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import org.w3c.dom.Text;

/**
 * Created by Nishat Sayyed on 01-04-2018.
 */

public class AnswerViewHolder extends ChildViewHolder {
    private TextView answerTextView;
    public AnswerViewHolder(View itemView) {
        super(itemView);
        answerTextView = itemView.findViewById(R.id.answerTextView);
    }

    public void onBind(Answer answer) {
        answerTextView.setText(answer.getAnswer());
    }

}
