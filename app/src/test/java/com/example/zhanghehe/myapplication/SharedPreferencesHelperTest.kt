package com.example.zhanghehe.myapplication

import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by zhanghehe on 2018/3/11.
 */
@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesHelperTest {

    private val TEST_NAME="test_name"
    private val TEST_DATEOFBIRTH=Calendar.getInstance().apply {
        set(1980,1,1)
    }
    private val TEST_EMAIL="test_email"

    private lateinit var sharedPreferenceEntry: SharedPreferenceEntry
    private lateinit var mockSharedPreferenceHelper: SharedPreferencesHelper
    private lateinit var mockBrokenSharedPreferencesHelper:SharedPreferencesHelper

    @Mock private lateinit var mockSharedPreferences:SharedPreferences
    @Mock private lateinit var mockBrokenSharedPreferences:SharedPreferences
    @Mock private lateinit var mockEditor:SharedPreferences.Editor
    @Mock private lateinit var mockBrokenEditor:SharedPreferences.Editor


    @Before
    fun setUp() {
        sharedPreferenceEntry=SharedPreferenceEntry(TEST_NAME,TEST_DATEOFBIRTH,TEST_EMAIL)

        mockSharedPreferenceHelper=createMockSharedPreference()

        mockBrokenSharedPreferencesHelper=createBrokenMockSharedPreference()

    }

    @Test fun sharedPreferencesHelper_SaveAndReadPersonalInformation() {
        assertTrue(mockSharedPreferenceHelper.savePersonalInfo(sharedPreferenceEntry))

        val savedEntry=mockSharedPreferenceHelper.getPersonalInfo()

        assertEquals(sharedPreferenceEntry.name,savedEntry.name)
        assertEquals(sharedPreferenceEntry.dateOfBirth,savedEntry.dateOfBirth)
        assertEquals(sharedPreferenceEntry.email,savedEntry.email)
    }

    @Test fun sharedPreferencesHelper_SavePersonalInfomationFailed_ReturnsFalse(){
        assertFalse(mockBrokenSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry))
    }

    private fun createBrokenMockSharedPreference(): SharedPreferencesHelper {
        given(mockBrokenEditor.commit()).willReturn(false)
        given(mockBrokenSharedPreferences.edit()).willReturn(mockBrokenEditor)
        return SharedPreferencesHelper(mockBrokenSharedPreferences)
    }


    private fun createMockSharedPreference(): SharedPreferencesHelper {
        given(mockSharedPreferences.getString(ArgumentMatchers.eq(SharedPreferencesHelper.KEY_NAME), ArgumentMatchers.anyString()))
                .willReturn(sharedPreferenceEntry.name)
        given(mockSharedPreferences.getString(ArgumentMatchers.eq(SharedPreferencesHelper.KEY_email), ArgumentMatchers.anyString()))
                .willReturn(sharedPreferenceEntry.email)
        given(mockSharedPreferences.getLong(ArgumentMatchers.eq(SharedPreferencesHelper.KEY_DOB), ArgumentMatchers.anyLong()))
                .willReturn(sharedPreferenceEntry.dateOfBirth.timeInMillis)
        given(mockEditor.commit()).willReturn(true)

        given(mockSharedPreferences.edit()).willReturn(mockEditor)
        return SharedPreferencesHelper(mockSharedPreferences)
    }


}