package com.github.thalesvb.sccc3.emoji;

import java.util.stream.IntStream;
import com.sap.ICommunityChallenge3;

/**
 * This implementation mainly works with Unicode code points to handle emojis,
 * since they can't be directly mapped into a single character on Java.
 */
public class EmojiManipulator implements ICommunityChallenge3 {

    /** Zero-Width Joiner. */
    private static final int ZWJ = 0x200D;
    public static final String MSG_ERROR_INPUT = "ERROR! Your input have something that doesn't look like an emoji.";

    @Override
    public String removeJoiner(String emoji) {
        if (!isRemoveInputValid(emoji)) {
            return MSG_ERROR_INPUT;
        }
        IntStream codePointsFiltered = emoji.codePoints().filter( cp -> { return cp == ZWJ ? false : true; } );
        return codePointsFiltered
            .collect(
                StringBuilder :: new, 
                StringBuilder :: appendCodePoint, 
                StringBuilder :: append )
            .toString();
    }

    @Override
    public String addJoiner(String emojis) {
        if (!isAddInputValid(emojis)) {
            return MSG_ERROR_INPUT;
        }
        final StringBuilder builder = new StringBuilder();
        emojis.codePoints().forEach(cp -> {
            builder.appendCodePoint(cp);
            builder.appendCodePoint(ZWJ);
        } );
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    public static boolean isAddInputValid(String input) {
        EmojiChecker checker = EmojiChecker.getInstance();
        boolean allEmojisAreKnown = input.codePoints().allMatch(checker::emojiIsKnown);
        if(!allEmojisAreKnown) {
            return false;
        }
        return true;
    }
    public static boolean isRemoveInputValid(String input) {
        EmojiChecker checker = EmojiChecker.getInstance();
        return checker.emojiIsKnown(input);
    }
}