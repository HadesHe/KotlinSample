package com.example.zhanghehe.myapplication

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by zhanghehe on 2018/3/11.
 */
class EmailValidatorTest{

    @Test fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test fun emailValidator_CorrectEmailSUbDomain_ReturnsTrue(){
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test fun emailValidator_InvalidEmailNoTld_ReturnsFalse(){
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }

    @Test fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse(){
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }

    @Test fun emailValidator_InvalidEmailNoUsername_ReturnFalse(){
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test fun emailValidator_EmptyString_ReturnFalse(){
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test fun emailValidator_NullEmail_ReturnFalse(){
        assertFalse(EmailValidator.isValidEmail(null))
    }
}