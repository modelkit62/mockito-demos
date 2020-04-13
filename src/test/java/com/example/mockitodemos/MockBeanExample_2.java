package com.example.mockitodemos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MockBeanExample_2 {

    @Autowired // AQUI SI QUE SE QUEJA PUES ME LO PIDE QUE EXISTA!!!
    private CustomerService customerService;

    @MockBean(name="customerRepository_test")
    private CustomerRepository customerRepository;

    @Test
    @Ignore
    public void test(){
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(customerService.findAll().isEmpty());
    }

    private class CustomerService {
        public Collection<Object> findAll() {
            return null;
        }
    }

    private class CustomerRepository {
        public Object findAll() {
            return null;
        }
    }
}