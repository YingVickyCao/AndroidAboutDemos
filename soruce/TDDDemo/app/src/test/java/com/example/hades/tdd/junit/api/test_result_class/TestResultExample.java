package com.example.hades.tdd.junit.api.test_result_class;

import junit.framework.AssertionFailedError;
import junit.framework.TestResult;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestResultExample extends TestResult {
    // add the error
    public synchronized void addError(Test test, Throwable t) {
        super.addError((junit.framework.Test) test, t);
    }

    // add the failure
    public synchronized void addFailure(Test test, AssertionFailedError t) {
        super.addFailure((junit.framework.Test) test, t);
    }
    @Test
    public void testAdd() {
        // add any moreReadableAndTypeable
        assertEquals(5, 2 + 2);
        assertNotEquals(0, 2 + 2);
    }

    // Marks that the moreReadableAndTypeable run should stop.
    public synchronized void stop() {
        //stop the moreReadableAndTypeable here
    }
}
