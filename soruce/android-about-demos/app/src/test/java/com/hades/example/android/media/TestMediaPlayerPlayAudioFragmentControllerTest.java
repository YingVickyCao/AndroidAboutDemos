package com.hades.example.android.media;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMediaPlayerPlayAudioFragmentControllerTest {

    TestMediaPlayerPlayAudioFragmentController mController;
    @Before
    public void setUp() throws Exception {
        mController = new TestMediaPlayerPlayAudioFragmentController();
    }

    @Test
    public void convertProgress2Percent() {
        int total=323161,current = 4207;
        System.out.println(current / total);
    }
}