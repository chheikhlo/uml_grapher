package fr.lernejo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SampleTest {

    private final Sample sample = new Sample();

    @Test
    void add_2_and_2_should_return_4() {
        int result = sample.op(Sample.Operation.ADD, 2, 2);

        Assertions.assertThat(result)
            .as("Addition of 2 and 2")
            .isEqualTo(4);
    }

    @Test
    void mult_2_and_3_should_return_6() {
        int result = sample.op(Sample.Operation.MULT, 2, 3);

        Assertions.assertThat(result)
            .as("Multiplication of 2 and 3")
            .isEqualTo(6);
    }

    @Test
    void fact_4_return_24() {
        int result = sample.fact( 4);

        Assertions.assertThat(result)
            .as("Factorielle de 4")
            .isEqualTo(24);
    }

    @Test
    void fact_return_int() {
        int result = sample.fact( 4);

        boolean isInt = (result>=0);

        Assertions.assertThat(isInt)
            .as("est il un entier")
            .isEqualTo(true);
    }
}
