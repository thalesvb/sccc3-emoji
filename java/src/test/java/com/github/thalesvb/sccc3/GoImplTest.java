package com.github.thalesvb.sccc3;

import com.github.thalesvb.sccc3.emoji.jni.GoLibWrapper;
import org.junit.jupiter.api.BeforeEach;

/**
 * Verify Go implementation.
 * Tests could be written in that language, but for simplicity is reusing Java one.
 */
public class GoImplTest extends ImplementationTest {

    @BeforeEach
    void beforeEach() {
        cut = new GoLibWrapper();
    }

}