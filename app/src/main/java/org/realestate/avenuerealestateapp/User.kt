package org.realestate.avenuerealestateapp

data class User(val emailF:String, val passwordF:String, val UIDF:String){
    var email = emailF
    var password = passwordF
    var UID = UIDF


    fun getUserPassword(): String {
        return password
    }
    fun setUserEmail(email: String) {
        this.email = email
    }


}
