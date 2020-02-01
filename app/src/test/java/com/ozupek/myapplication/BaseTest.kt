package com.ozupek.myapplication

import org.junit.ClassRule

abstract class BaseTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }
}