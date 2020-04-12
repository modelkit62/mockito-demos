package com.example.mockitodemos;

import org.assertj.core.util.Lists;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.NoInteractionsWanted;

import java.util.List;


import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Test probando Mockito 😱")
class AppTest {

    @Test
    @DisplayName("Test_1")
    public void verify_simple_invocation_on_mock(){
        List<String> mockedList = mock(App.class);
        System.out.println(mockedList.size());
        verify(mockedList).size();
    }

    @Test
    @DisplayName("Test_2")
    public void verify_number_of_interactions_with_mock(){
        List<String> mockedList = mock(App.class);
        mockedList.size();
        System.out.println(mockedList.size());
        verify(mockedList, times(2)).size();
    }

    @Test
    @DisplayName("Test_3")
    public void verify_no_interaction_with_the_whole_mock_occurred(){
        List<String> mockedList = mock(App.class);
        verifyNoInteractions(mockedList);
    }

    @Test
    @DisplayName("Test_4")
    public void verify_no_interaction_with_with_a_specific_method_occurred(){
        List<String> mockedList = mock(App.class);
        verify(mockedList, times(0)).size();
    }

    @Test
    @DisplayName("Test_5")
    public void verify_there_are_no_unexpected_interactions(){
        List<String> mockedList = mock(App.class);
        mockedList.size();
        mockedList.clear();
        verify(mockedList).size();
        // verifyNoMoreInteractions(mockedList);
        assertThrows(   NoInteractionsWanted.class, () ->
                        verifyNoMoreInteractions(mockedList),
                        "xxx"
        );
    }

    @Test
    @DisplayName("Test_6")
    public void verify_order_of_interactions(){
        List<String> mockedList = mock(App.class);
        mockedList.size();
        mockedList.add("a parameter");
        mockedList.clear();

        InOrder inOrder = Mockito.inOrder(mockedList);
        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add("a parameter");
        inOrder.verify(mockedList).clear();
    }
    
    @Test
    @DisplayName("Test_7")
    public void verify_an_interaction_has_not_occurred(){
        List<String> mockedList = mock(App.class);
        mockedList.size();
        verify(mockedList, never()).clear();
    }

    @Test
    @DisplayName("Test_8")
    public void verify_an_interaction_has_occurred_at_least_certain_number_of_times(){
        List<String> mockedList = mock(App.class);
        mockedList.clear();
        mockedList.clear();
        mockedList.clear();

        verify(mockedList, atLeast(1)).clear();
        verify(mockedList, atMost(10)).clear();
    }

    @Test
    @DisplayName("Test_9")
    public void verify_interaction_with_exact_argument(){
        List<String> mockedList = mock(App.class);
        mockedList.add("test");
        verify(mockedList).add("test");
    }

    @Test
    @DisplayName("Test_10")
    public void verify_interaction_with_flexible_any_argument(){
        List<String> mockedList = mock(App.class);
        mockedList.add("test");
        verify(mockedList).add(anyString());
    }

    @Test
    @DisplayName("Test_11")
    public void verify_interaction_using_argument_capture(){
        List<String> mockedList = mock(App.class);
        mockedList.addAll(Lists.<String> newArrayList("someElement"));
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockedList).addAll(argumentCaptor.capture());
        List<String> capturedArgument = argumentCaptor.<List<String>> getValue();
        MatcherAssert.assertThat(capturedArgument, hasItem("someElement"));
    }

    @Test
    @DisplayName("Test_12")
    public void verify_custom_1(){
        List<String> mockedList = mock(App.class);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        mockedList.get(1);
        verify(mockedList).get(argumentCaptor.capture());
        assertEquals(1, argumentCaptor.getValue());
    }
}