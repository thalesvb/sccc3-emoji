package com.github.thalesvb.sccc3.emoji.jni;

/**
 * GoLang Emoji library API interface.
 * It must be public otherwise JNR loader can't properly load native library.
 * 
 * Even both signature are the same from challenge, it's not wise to use that
 * interface to load native code.
 */
public interface IGoEmojiLib {
    public String AddJoiner(String emojis);
    public String RemoveJoiner(String emoji);
}