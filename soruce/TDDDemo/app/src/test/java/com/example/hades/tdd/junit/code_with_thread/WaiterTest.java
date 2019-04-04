package com.example.hades.tdd.junit.code_with_thread;

import net.jodah.concurrentunit.Waiter;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WaiterTest {
    @Test
    public void shouldSupportMultipleThreads() throws Throwable {
        final Waiter waiter = new Waiter();

        for (int i = 0; i < 5; i++)
            new Thread(new Runnable() {
                public void run() {
                    waiter.assertTrue(true);
                    waiter.resume();
                }
            }).start();

        waiter.await(0);
    }

    /**
     * Should support resume.
     */
    public void waitShouldSupportResume() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                w.resume();
            }
        }).start();

        w.await();
    }

    /**
     * Should throw an assertion error caused by the failure.
     */
    public void waitShouldSupportFail() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                w.fail(new IllegalArgumentException());
            }
        }).start();

        try {
            w.await();
            fail();
        } catch (AssertionError e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    /**
     * Should throw an assertion error.
     */
    @Test(expected = AssertionError.class)
    public void waitShouldSupportAssertionErrors() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                w.assertTrue(false);
            }
        }).start();

        w.await();
    }

    /**
     * Should timeout.
     */
    @Test(expected = TimeoutException.class)
    public void waitShouldSupportTimeouts() throws Throwable {
        final Waiter w = new Waiter();
        w.await(10);
    }

    /**
     * Ensures that waiting for multiple resumes works as expected.
     */
    public void shouldSupportMultipleResumes() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++)
                    w.resume();
            }
        }).start();

        w.await(500, 5);
    }

    public void shouldSupportThreadWait0WithResumeCount() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++)
                    w.resume();
            }
        }).start();

        w.await(0, 5);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailNullAssertionWithReason() throws Throwable {
        Waiter w = new Waiter();
        w.assertNull("test");
    }

    @Test(expected = AssertionError.class)
    public void shouldFailNullAssertionFromWorkerThreadWithReason() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                w.assertNull("test");
            }
        }).start();

        w.await();
    }

    public void shouldHandleResumesThenAwait() throws Throwable {
        final Waiter w = new Waiter();
        final AtomicInteger expectedResumes = new AtomicInteger();

        new Thread(new Runnable() {
            public void run() {
                try {
                    w.resume();
                    expectedResumes.incrementAndGet();
                    Thread.sleep(400);
                    expectedResumes.incrementAndGet();
                    w.resume();
                } catch (InterruptedException e) {
                }
            }
        }).start();

        Thread.sleep(200);
        w.await(0, 2);
    }

    @Test(expected = AssertionError.class)
    public void shouldHandleFailThenAwait() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                w.fail();
            }
        }).start();

        Thread.sleep(400);
        w.await(0, 5);
    }

    @Test(expected = AssertionError.class)
    public void shouldHandleResumeFailAwait() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                try {
                    w.resume();
                    w.fail();
                } catch (Throwable ignore) {
                }
            }
        }).start();

        Thread.sleep(400);
        w.await();
    }

    @Test(expected = AssertionError.class)
    public void shouldHandleFailResumeAwait() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                try {
                    w.fail();
                } catch (Throwable ignore) {
                }
                w.resume();
            }
        }).start();

        Thread.sleep(400);
        w.await();
    }

    @Test(expected = IOException.class)
    public void shouldRethrow() throws Throwable {
        final Waiter w = new Waiter();

        new Thread(new Runnable() {
            public void run() {
                w.rethrow(new IOException());
            }
        }).start();

        w.await();
    }

    public void shouldSupportSuccessiveResumeAwaits() throws Throwable {
        final Waiter w = new Waiter();
        w.resume();
        w.await();
        w.resume();
        w.await();
    }

    public void shouldSupportSuccessiveResumes() throws Throwable {
        final Waiter w = new Waiter();
        w.resume();
        w.resume();
        w.resume();
        w.await();
    }
}
