package com.agrovamp.agrovamp;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Nishat Sayyed on 01-04-2018.
 */

public class Question extends ExpandableGroup<Answer> {
    public Question(String title, List<Answer> items) {
        super(title, items);
    }
}
