/**
 * 
 */
package com.ngins.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class SimpleCalcTest {

    @Test
    void sumTest() {
        int a = 2, b = 3;
        int result = a + b;
        System.out.println("result:" + result);
        assertThat(result).isEqualTo(5);
    }
}
