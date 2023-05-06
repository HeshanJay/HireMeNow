package com.example.hiremenow


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FormUtilTest{

    @Test
    fun  `empty name returns false`(){
            val result = FormUtil.validateFormInput(
                "",
                "sithum@gmail.com",
                "0987656456",
                "how to apply",
                "how to add images"
            )
        assertThat(result).isFalse()
    }
    @Test
    fun  `valid name,email,phone,subject and message returns true`(){
        val result = FormUtil.validateFormInput(
            "Sithumithu",
            "sithumithuk@gmail.com",
            "0980696406",
            "how to set job image",
            "how to make it possible to add images"
        )
        assertThat(result).isTrue()

    }
    @Test
    fun  `email already exsists returns false `(){
        val result = FormUtil.validateFormInput(
            "Kanishha",
            "chala@gmail.com",
            "0900650451",
            "how to apply",
            "how to add images"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  `phone already exsists returns false `(){
        val result = FormUtil.validateFormInput(
            "Ishi",
            "danuk@gmail.com",
            "0987465637",
            "how to get notification",
            "does job selections gives notification"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun  `phone less than 10 digits return false `(){
        val result = FormUtil.validateFormInput(
            "Joe",
            "joe@gmail.com",
            "09678789",
            "how to enter Cv",
            "enter cv in doc or pdf"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  `empty subject`(){
        val result = FormUtil.validateFormInput(
            "Hithiasha",
            "hithia@gmail.com",
            "0978998888",
            "",
            "help in using jobapp"
        )
        assertThat(result).isFalse()
    }

}