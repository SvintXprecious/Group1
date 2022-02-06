package org.realestate.avenuerealestateapp;

class Users {
    public String email;
    public String password;
    public  String UID;
    public Users(){

    }
    public Users(String email, String password, String UID){
        this.email = email;
        this.password = password;
        this.UID = UID;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}