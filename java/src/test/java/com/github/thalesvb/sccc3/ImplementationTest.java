package com.github.thalesvb.sccc3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sap.ICommunityChallenge3;

/**
 * The worst test case ever: "If it works for case A, I trust that it will also work for case B".
*/
public abstract class ImplementationTest {

    protected ICommunityChallenge3 cut;

    @BeforeEach
    /**
     * Instantiate related implementation to check.
     */
    abstract void beforeEach();
    /**
     * Validate AddJoiner implementation based on Challenge example.
     */
    @Test
    final void testAddJoiner() {
        assertEquals("👩‍💻", cut.addJoiner("👩💻"));
    }
    /**
     * Validate RemoveJoiner implementation based on Challenge example.
     */
    @Test
    final void testRemoveJoiner() {
        assertEquals("👩💻", cut.removeJoiner("👩‍💻"));
    }
}