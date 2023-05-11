package com.example.mad

import android.hardware.SensorAdditionalInfo

object RegistrationUtil_addjob {

    private val existingUsers = listOf("Perera","Dilshan")

    /**
     * the input is not valid if ...
     * ... the possition/address/vacancies is empty
     * ... and other datails already taken
     *
     */

    fun validateRegistrationInput(
        possition: String,
        address:String,
        vacancies: String,
        name:String,
        main: String,
        additional: String,
        basic: String,
        allowance:String,
        ot :String
    ): Boolean{
        if(possition.isEmpty() || address.isEmpty()){   //check if fields are empty or not
            return false
        }
        if(vacancies.isEmpty() || name.isEmpty()){   //check if fields are empty or not
            return false
        }
        if(main.isEmpty() || additional.isEmpty()){   //check if fields are empty or not
            return false
        }
        if(basic.isEmpty() || allowance.isEmpty() || ot.isEmpty()) {   //check if fields are empty or not
            return false
        }
        return true
    }
}