package com.ozupek.myapplication

import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var computation: Computation

    @Before
    fun setup () {
        computation = Computation()
    }

    @Test
    fun isSumFunctionWorkCorrect() {
        val result = computation.sum(10,2)
        assert(result == 12)
    }
}
