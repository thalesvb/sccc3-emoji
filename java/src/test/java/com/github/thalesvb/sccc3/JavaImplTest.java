package com.github.thalesvb.sccc3;

import com.github.thalesvb.sccc3.emoji.EmojiManipulator;

import org.junit.jupiter.api.BeforeEach;

/**
 * Verify Java implementation (reference).
 */
public class JavaImplTest extends ImplementationTest {

    @BeforeEach
    void beforeEach() {
        cut = new EmojiManipulator();
    }

}