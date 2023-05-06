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
        if(possition.isEmpty() || address.isEmpty()){
            return false
        }
        if(vacancies.isEmpty() || name.isEmpty()){
            return false
        }
        if(main.isEmpty() || additional.isEmpty()){
            return false
        }
        if(basic.isEmpty() || allowance.isEmpty() || ot.isEmpty()) {
            return false
        }
        return true
    }
}