package com.example.hiremenow

// pass data to the db
data class CustomerModel (
    var customerId:String? = null,
    var customerName:String? = null,
    var customerEmail:String?=null,
    var customerPhone:String?=null,
    var customerSubject:String?=null,
    var customerMessage:String?=null,

    )