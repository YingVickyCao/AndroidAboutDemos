package com.example.hades.tdd.junit.test_runners.theories.custom;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.Arrays;
import java.util.List;

public class AgeSupplier extends ParameterSupplier {
    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
        PotentialAssignment ageAssignment1 = PotentialAssignment.forValue("age", 10);
        PotentialAssignment ageAssignment2 = PotentialAssignment.forValue("age", 20);
        return Arrays.asList(new PotentialAssignment[]{ageAssignment1, ageAssignment2});
    }
};
