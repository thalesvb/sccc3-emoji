package com.github.thalesvb.sccc3;

import java.util.Scanner;

import com.github.thalesvb.sccc3.emoji.EmojiManipulator;
import com.github.thalesvb.sccc3.emoji.jni.GoLibWrapper;
import com.sap.ICommunityChallenge3;
/**
 * SCCC3
 */
public class App 
{
    public static void main( String[] args ) {
        ICommunityChallenge3 javaImplementation = new EmojiManipulator();
        answerChallenge(javaImplementation);
        System.out.println("Now answering the challenge with Go (native library)...");
        ICommunityChallenge3 goImplementation = new GoLibWrapper();
        answerChallenge(goImplementation);

        innerChallenge((GoLibWrapper)goImplementation);
    }

    private static void answerChallenge(ICommunityChallenge3 impl) {
        System.out.println("The emojis that make up 👨‍👩‍👧‍👦 are: " + impl.removeJoiner("👨‍👩‍👧‍👦"));
        System.out.println("Combining 👨 and 👧 creates: " + impl.addJoiner("👨👧"));
    }
    
    private static void innerChallenge(GoLibWrapper impl) {
        System.out.println("-----[ Perpetual Easter Egg challenge!! ]-----");
        System.out.println("Keep trying until you unlock it, or give up and finish the application with a blank line.");
        String input = "0";
        Scanner inputReader = new Scanner(System.in);
        try {
            while((input = inputReader.nextLine()).length() > 0) {
                System.out.println("Result: " + impl.addJoiner(input));
            }
        } finally {
            inputReader.close();
        }

    }
}
