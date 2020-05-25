package com.example.magistore.modelos;

import com.squareup.otto.Bus;

public class OttoClass {
    private static Bus bus;
    public static Bus getBus(){
        if (bus==null ){
            bus=new Bus();
        }
        return bus;
    }
}