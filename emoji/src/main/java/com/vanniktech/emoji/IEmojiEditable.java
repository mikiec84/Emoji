package com.vanniktech.emoji;

import com.vanniktech.emoji.emoji.Emoji;

/**
 * Created by Kevin Read <me@kevin-read.com> on 05/10/16 for Emoji.
 * Copyright (c) 2016 BörseGo AG. All rights reserved.
 */

public interface IEmojiEditable {
    void input(Emoji emoji);

    void backspace();

    void setFocusableInTouchMode(boolean b);
}
