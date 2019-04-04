package com.example.hades.tdd.junit.test_runners.theories.custom;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.Arrays;
import java.util.List;

public class NameSupplier extends ParameterSupplier {

    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
        PotentialAssignment nameAssignment1 = PotentialAssignment.forValue("name", "Tony");
        PotentialAssignment nameAssignment2 = PotentialAssignment.forValue("name", "Jim");
        return Arrays.asList(new PotentialAssignment[]{nameAssignment1, nameAssignment2});
    }

}