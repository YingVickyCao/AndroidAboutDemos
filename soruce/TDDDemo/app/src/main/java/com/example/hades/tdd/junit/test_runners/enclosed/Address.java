package com.example.hades.tdd.junit.test_runners.enclosed;

//import com.google.common.base.Objects;

import java.io.Serializable;

public class Address implements Serializable, Comparable<Address> {

    private static final long serialVersionUID = 1L;
    private final String address;
    private final String city;
    private final String state;
    private final String zip;

    private Address(Builder builder) {
        this.address = builder.address1;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public int compareTo(Address that) {
        /*return ComparisonChain.start().compare(this.zip, that.zip)
                .compare(this.state, that.state)
                .compare(this.city, that.city)
                .compare(this.address, that.address).result();*/
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        /*if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Address that = (Address) obj;

        return Objects.equal(this.address, that.address)
                && Objects.equal(this.city, that.city)
                && Objects.equal(this.state, that.state)
                && Objects.equal(this.zip, that.zip);*/
        return false;
    }

    @Override
    public int hashCode() {
//        return Objects.hashCode(address, city, state, getState(), zip);
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    public static class Builder {
        private String address1;
        private String city;
        private String state;
        private String zip;

        public Builder address(String address) {
            this.address1 = address;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }
    }
}
