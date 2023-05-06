package com.example.mad

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtil_addjobTest{

    @Test
    fun `empty position return false`(){
        val result = RegistrationUtil_addjob.validateRegistrationInput(
            "",
            "sss",
            "ddd",
            "rrr",
            "ttt",
            "yyy",
            "222",
            "333",
            "444"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `filled position return true`(){
        val result = RegistrationUtil_addjob.validateRegistrationInput(
            "Dilshan",
            "sss",
            "ddd",
            "rrr",
            "ttt",
            "yyy",
            "222",
            "333",
            "444"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty address return false`(){
        val result = RegistrationUtil_addjob.validateRegistrationInput(
            "Dilshan",
            "",
            "ddd",
            "rrr",
            "ttt",
            "yyy",
            "222",
            "333",
            "444"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `filled address return true`(){
        val result = RegistrationUtil_addjob.validateRegistrationInput(
            "Dilshan",
            "sss",
            "ddd",
            "rrr",
            "ttt",
            "yyy",
            "222",
            "333",
            "444"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty vacancies return false`(){
        val result = RegistrationUtil_addjob.validateRegistrationInput(
            "Dilshan",
            "sss",
            "",
            "rrr",
            "ttt",
            "yyy",
            "222",
            "333",
            "444"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `filled vacancies return true`(){
        val result = RegistrationUtil_addjob.validateRegistrationInput(
            "Dilshan",
            "sss",
            "ddd",
            "rrr",
            "ttt",
            "yyy",
            "222",
            "333",
            "444"
        )
        assertThat(result).isTrue()
    }
}