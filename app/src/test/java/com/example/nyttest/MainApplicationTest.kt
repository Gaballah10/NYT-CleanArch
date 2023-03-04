package com.example.nyttest

import com.example.nyttest.testUtils.Injector
import io.mockk.clearAllMocks
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach


class MainApplicationTest {

    val injector = Injector()
    lateinit var instance: MainApplication

    @BeforeEach
    fun beforeEachTest() {
        instance = MainApplication()
    }

    @AfterEach
    fun afterEachTest() {
        clearAllMocks()
    }

    @BeforeAll
    fun beforeGroup() {
        injector.start()
    }

    @AfterAll
    fun afterGroup() {
        injector.stop()
    }


}