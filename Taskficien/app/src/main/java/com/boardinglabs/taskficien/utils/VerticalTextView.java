package com.boardinglabs.taskficien.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by rizkygustinaldi on 11/28/14.
 */
public class VerticalTextView extends TextView {
    public VerticalTextView(Context context) {
        super(context);
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
//        String str = text.toString();
        String newText = "";
        for (int i=0; i< text.length(); i++){
            newText += text.charAt(i)+"\n";
        }
        super.setText(newText, type);
    }
}
