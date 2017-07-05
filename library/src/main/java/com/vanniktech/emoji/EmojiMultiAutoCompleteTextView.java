package com.vanniktech.emoji;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.MultiAutoCompleteTextView;

import com.vanniktech.emoji.emoji.Emoji;

/**
 * Created by Kevin Read <me@kevin-read.com> on 05/10/16 for Emoji.
 * Copyright (c) 2016 Kevin Read. All rights reserved.
 */

public class EmojiMultiAutoCompleteTextView extends MultiAutoCompleteTextView
            implements IEmojiEditable {
    private int emojiSize;

    public EmojiMultiAutoCompleteTextView(final Context context) {
        super(context);
        init(null);
    }

    public EmojiMultiAutoCompleteTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmojiMultiAutoCompleteTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(@Nullable final AttributeSet attrs) {
        if (attrs == null) {
            emojiSize = (int) getTextSize();
        } else {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.emoji);

            try {
                emojiSize = (int) a.getDimension(R.styleable.emoji_emojiSize, getTextSize());
            } finally {
                a.recycle();
            }
        }

        setText(getText());
    }

    @Override
    protected void onTextChanged(final CharSequence text, final int start, final int lengthBefore, final int lengthAfter) {
        EmojiHandler.addEmojis(getContext(), getText(), emojiSize);
    }

    public void setEmojiSize(final int pixels) {
        emojiSize = pixels;
    }

    @Override
    public void backspace() {
        final KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
        dispatchKeyEvent(event);
    }

    @Override
    public void input(final Emoji emoji) {
        if (emoji != null) {
            final int start = getSelectionStart();
            final int end = getSelectionEnd();
            if (start < 0) {
                append(emoji.getEmoji());
            } else {
                getText().replace(Math.min(start, end), Math.max(start, end), emoji.getEmoji(), 0, emoji.getEmoji().length());
            }
        }
    }
}
