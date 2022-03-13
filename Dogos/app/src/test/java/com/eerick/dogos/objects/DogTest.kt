package com.eerick.dogos.objects

import org.junit.Test

class DogTest {
    private val name: String = "dog name"
    private val description: String = "dog description"
    private val age: Int = 1
    private val image = "fake_url"

    @Test (expected = ExceptionInInitializerError::class)
    fun `Throws an exception if name is empty`() {
        Dog("", description, age, image)
    }

    @Test (expected = ExceptionInInitializerError::class)
    fun `Throws an exception if description is empty`() {
        Dog(name, "", age, image)
    }

    @Test (expected = ExceptionInInitializerError::class)
    fun `Throws an exception if age is negative`() {
        Dog(name, description, -1, image)
    }

    @Test (expected = ExceptionInInitializerError::class)
    fun `Throws an exception if image is empty`() {
        Dog(name, description, age, "")
    }
}