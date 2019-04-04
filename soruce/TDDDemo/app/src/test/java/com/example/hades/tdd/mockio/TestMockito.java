package com.example.hades.tdd.mockio;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestMockito {
    @Test
    public void test1() throws Exception {
        List<String> mockedList = mock(List.class);
        mockedList.add("One");
        mockedList.clear();

        verify(mockedList).add("One");
        verify(mockedList).clear();
//        verify(mockedList).remove("One"); // ERROR:Wanted but not invoked:
    }
}