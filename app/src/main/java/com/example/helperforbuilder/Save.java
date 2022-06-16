package com.example.helperforbuilder;

import androidx.appcompat.app.AppCompatActivity;

public class Save extends AppCompatActivity {

    private String name;
    private String need;

    protected Save(String name, String need){
        this.name = name;
        this.need = need;
    }
    protected String getName(){
        return this.name;
    }
    protected String getNeed(){
        return this.need;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setNeed(String need){
        this.need = need;
    }
}
