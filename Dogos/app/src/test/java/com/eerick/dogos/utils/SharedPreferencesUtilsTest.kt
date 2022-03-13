package com.eerick.dogos.utils

import android.content.Context
import android.content.SharedPreferences
import com.eerick.dogos.R
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SharedPreferencesUtilsTest {

    private val FAKE_PREF = "fake_pref"
    private val FAKE_KEY: String = "fake_key"
    private val UNSUPPORTED_OBJECT: ArrayList<Any> = ArrayList()
    private val FAKE_BOOL: Boolean = true
    private val FAKE_INT: Int = 1
    private val FAKE_FLOAT: Float = 1.0F
    private val FAKE_LONG: Long = 1
    private val FAKE_STRING: String = "Fake str"

    @Mock
    private val context: Context? = null
    @Mock
    private val sharedPreferences: SharedPreferences? = null
    @Mock
    private val sharedPreferencesEditor: SharedPreferences.Editor? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context!!.getString(R.string.preference_file_key)).thenReturn(FAKE_PREF)
        Mockito.`when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        Mockito.`when`(sharedPreferences!!.edit()).thenReturn(sharedPreferencesEditor)
    }

    @Test (expected = UnsupportedOperationException::class)
    fun `Throws an exception with unsupported object`() {
        SharedPreferencesUtils.save(context!!, FAKE_KEY, UNSUPPORTED_OBJECT)
    }

    @Test
    fun `Has support to store boolean data`() {
        SharedPreferencesUtils.save(context!!, FAKE_KEY, FAKE_BOOL)
    }

    @Test
    fun `Has support to store int data`() {
        SharedPreferencesUtils.save(context!!, FAKE_KEY, FAKE_INT)
    }

    @Test
    fun `Has support to store float data`() {
        SharedPreferencesUtils.save(context!!, FAKE_KEY, FAKE_FLOAT)
    }

    @Test
    fun `Has support to store long data`() {
        SharedPreferencesUtils.save(context!!, FAKE_KEY, FAKE_LONG)
    }

    @Test
    fun `Has support to store string data`() {
        SharedPreferencesUtils.save(context!!, FAKE_KEY, FAKE_STRING)
    }

    @Test
    fun `Gets boolean successfully`() {
        SharedPreferencesUtils.getBoolean(context!!, FAKE_KEY, FAKE_BOOL)
    }

    @Test
    fun `Gets int successfully`() {
        SharedPreferencesUtils.getInt(context!!, FAKE_KEY, FAKE_INT)
    }

    @Test
    fun `Gets float successfully`() {
        SharedPreferencesUtils.getFloat(context!!, FAKE_KEY, FAKE_FLOAT)
    }

    @Test
    fun `Gets long successfully`() {
        SharedPreferencesUtils.getLong(context!!, FAKE_KEY, FAKE_LONG)
    }

    @Test
    fun `Gets string successfully`() {
        SharedPreferencesUtils.getString(context!!, FAKE_KEY, FAKE_STRING)
    }
}