package com.github.thalesvb.sccc3.emoji.jni;

import jnr.ffi.LibraryLoader;

import com.github.thalesvb.sccc3.emoji.EmojiManipulator;
import com.sap.ICommunityChallenge3;


public class GoLibWrapper implements ICommunityChallenge3 {

    private static final IGoEmojiLib EMOJI_LIB;
    
    static {
        // Relative workspace linking path, since it's been a long time that I coded Java and
        // I already forgot to to do the Maven way (copy/link into target folder).
        EMOJI_LIB = loadNativeLibrary();
    }
    @Override
    public String removeJoiner(String emoji) {
        if (!EmojiManipulator.isRemoveInputValid(emoji)) {
            return EmojiManipulator.MSG_ERROR_INPUT;
        }
        return EMOJI_LIB.RemoveJoiner(emoji);
    }

    @Override
    public String addJoiner(String emojis) {
        if (!EmojiManipulator.isAddInputValid(emojis)) {
            return EmojiManipulator.MSG_ERROR_INPUT;
        }
        return EMOJI_LIB.AddJoiner(emojis);
    }
    /**
     * Load GoLang compiled library into Java.
     */
    private static IGoEmojiLib loadNativeLibrary() {
        return LibraryLoader.create(IGoEmojiLib.class).load("./native/libgoemoji.so");
    }

    public static boolean isLibraryAvailable() {
        return EMOJI_LIB != null;
    }

}
