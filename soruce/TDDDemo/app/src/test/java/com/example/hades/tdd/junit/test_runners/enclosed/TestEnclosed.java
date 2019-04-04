package com.example.hades.tdd.junit.test_runners.enclosed;

import com.example.hades.tdd.junit.test_runners.enclosed.testhelpers.ComparabilityTestCase;
import com.example.hades.tdd.junit.test_runners.enclosed.testhelpers.EqualsHashCodeTestCase;
import com.example.hades.tdd.junit.test_runners.enclosed.testhelpers.SerializabilityTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.Serializable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * The Class TestEnclosed.
 */
@RunWith(Enclosed.class)
public class TestEnclosed {

    /**
     * The Class AddressComparabilityTest.
     */
    public static class AddressComparabilityTest extends ComparabilityTestCase<Address> {

        @Override
        protected Address createEqualInstance() throws Exception {
            return new Address.Builder().address("2802 South Havana Street").city("Aurora").state("CO").zip("80014").build();
        }

        @Override
        protected Address createGreaterInstance() throws Exception {
            return new Address.Builder().address("9839 Carlisle Boulevard NE").city("Albuquerque").state("NM").zip("87110").build();
        }

        @Override
        protected Address createLessInstance() throws Exception {
            return new Address.Builder().address("14 Broad St").city("Nashua").state("NH").zip("03064").build();
        }
    }

    /**
     * The Class AddressEqualsHashCodeTest.
     */
    public static class AddressEqualsHashCodeTest extends EqualsHashCodeTestCase {

        @Override
        protected Address createInstance() throws Exception {
            return new Address.Builder().address("2802 South Havana Street").city("Aurora").state("CO").zip("80014").build();
        }

        @Override
        protected Address createNotEqualInstance() throws Exception {
            return new Address.Builder().address("9839 Carlisle Boulevard NE").city("Albuquerque").state("NM").zip("87110").build();
        }
    }

    /**
     * The Class AddressSerializabilityTest.
     */
    public static class AddressSerializabilityTest extends SerializabilityTestCase {

        @Override
        protected Serializable createInstance() throws Exception {
            return new Address.Builder().address("9839 Carlisle Boulevard NE").city("Albuquerque").state("NM").zip("87110").build();
        }
    }

    public static class AddressMiscTest {

        private Address address;

        /**
         * Setup.
         *
         * @throws Exception the exception
         */
        @Before
        public void setUp() throws Exception {
            address = new Address.Builder().address("9839 Carlisle Boulevard NE").city("Albuquerque").state("NM").zip("87110").build();
        }

        /**
         * Test builder.
         */
        @Test
        public void testBuilder() {
            assertThat(address.getAddress(), is("9839 Carlisle Boulevard NE"));
            assertThat(address.getCity(), is("Albuquerque"));
            assertThat(address.getState(), is("NM"));
            assertThat(address.getZip(), is("87110"));
        }

        @Test
        public void testToString() {
            assertThat(address.toString(), is("Address{9839 Carlisle Boulevard NE, Albuquerque, NM, 87110}"));
        }
    }
}