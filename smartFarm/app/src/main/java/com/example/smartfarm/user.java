package com.example.smartfarm;

public class user {
    public String first_name,last_name,email,phone;
    public boolean SwitchValestate,SwitchPumpstate,SwitchFanstate;
    public int waterprogress,humprogress,tempprogress;

    public user(String first_name, String last_name, String email, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }

    public user(String first_name, int waterprogress, int humprogress, int tempprogress) {
        this.first_name = first_name;
        this.waterprogress = waterprogress;
        this.humprogress = humprogress;
        this.tempprogress = tempprogress;
    }

    public user(boolean switchValestate, boolean switchPumpstate, boolean switchFanstate) {
        SwitchValestate = switchValestate;
        SwitchPumpstate = switchPumpstate;
        SwitchFanstate = switchFanstate;
    }
}
