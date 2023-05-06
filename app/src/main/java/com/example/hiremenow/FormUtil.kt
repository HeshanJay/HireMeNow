package com.example.hiremenow

object FormUtil {

    private val existingEmail = listOf("chala@gmail.com","hashini@gmail.com")
    private val existingPhone = listOf("0784657843","0987465637")

    /**
     * the input is not valid if
     * the name /email/phone/subject/meassage is empty
     *..and the email is already taken
     * ..and phone is is already taken
     * the phone number contains less than 10 numbers
     */

    fun validateFormInput(
        name: String,
        email: String,
        phone: String,
        subject: String,
        message: String
    ):Boolean{
        if(name.isEmpty()|| email.isEmpty()|| phone.isEmpty()|| subject.isEmpty()|| message.isEmpty()){
            return false
        }
        if(email in existingEmail){
            return false
        }
        if(phone in existingPhone){
            return false
        }
        if(phone.count { it.isDigit() } <10 ){
            return false
        }
        return true
    }

}