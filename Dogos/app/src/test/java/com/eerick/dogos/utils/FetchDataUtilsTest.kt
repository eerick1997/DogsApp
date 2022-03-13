package com.eerick.dogos.utils

import com.eerick.dogos.objects.Dog
import com.eerick.dogos.utils.FetchDataConstants.Companion.AGE
import com.eerick.dogos.utils.FetchDataConstants.Companion.DESCRIPTION
import com.eerick.dogos.utils.FetchDataConstants.Companion.IMAGE
import com.eerick.dogos.utils.FetchDataConstants.Companion.NAME
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.assertEquals

class FetchDataUtilsTest {

    private val name: String = "dog name"
    private val description: String = "dog description"
    private val age: Int = 1
    private val image = "fake_url"

    @Test
    fun `Application continues with empty JSON array`() {
        val jsonArray = JSONArray()
        val dataParsed: ArrayList<Dog>? = FetchDataUtils.maybeParseData(jsonArray)
        assert(dataParsed.isNullOrEmpty())
    }

    @Test
    fun `Is parsing successfully`() {
        val jsonArray = JSONArray()
        val testDogData = JSONObject()
        val expectedOutput = ArrayList<Dog>()
        testDogData.put(NAME, name)
        testDogData.put(DESCRIPTION, description)
        testDogData.put(AGE, age)
        testDogData.put(IMAGE, image)
        jsonArray.put(testDogData)
        val resultParseData: ArrayList<Dog> = FetchDataUtils.maybeParseData(jsonArray)!!
        expectedOutput.add(Dog(name, description, age, image))
        assertEquals(expectedOutput, resultParseData)
    }
}