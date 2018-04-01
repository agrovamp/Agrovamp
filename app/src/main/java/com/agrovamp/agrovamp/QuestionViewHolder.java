package com.agrovamp.agrovamp;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import org.w3c.dom.Text;

/**
 * Created by Nishat Sayyed on 01-04-2018.
 */

public class QuestionViewHolder extends GroupViewHolder {
    private TextView questionTextView;
    public QuestionViewHolder(View itemView) {
        super(itemView);
        questionTextView = (TextView) itemView.findViewById(R.id.questionTextView);
    }

    public void setQuestionTextView(ExpandableGroup group) {
        questionTextView.setText(group.getTitle());
    }
}
