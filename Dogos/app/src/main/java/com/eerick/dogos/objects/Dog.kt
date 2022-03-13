package com.eerick.dogos.objects

/**
 * Dog
 *
 * As the keyword data indicates we are only storing some info about each dog
 * we do not need extra functionality in this class so use a data class
 * should be enough.
 * @param name Dogs name
 * @param description Dogs description
 * @param age Dogs age
 * @param uri this is the URI that we must use to get the dogs image
 */
data class Dog(val name: String, val description: String, val age: Int, val uri: String) {
    init {
        if (name.isEmpty() or description.isEmpty() or uri.isEmpty() or (age < 0)) {
            throw ExceptionInInitializerError("name, description and uri cannot be empty, also does not exist a negative age")
        }
    }
}