package com.example.hiremenow_job

object ApplicationUtil {
    private val existingEmail = listOf("heshan@gmail.com","hashani@gmail.com")
    private val existingPhone = listOf("0764657843","0997465637")

    /**
     * the input is not valid if
     * the name /email/phone/subject/meassage is empty
     *..and the email is already taken
     * ..and phone is is already taken
     * the phone number contains less than 10 numbers
     */

    fun validateFormInput(
        cname: String,
        fname: String,
        email: String,
        phone: String,

        ):Boolean {
        if (cname.isEmpty() || fname.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            return false
        }
        if (email in existingEmail) {
            return false
        }
        if (phone in existingPhone) {
            return false
        }
        if (phone.count { it.isDigit() } < 10) {
            return false
        }
        return true
    }
}