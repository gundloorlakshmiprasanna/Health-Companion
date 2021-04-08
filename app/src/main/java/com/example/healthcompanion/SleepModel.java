package com.example.healthcompanion;

public class SleepModel {



    String id;
    String date;
    String analysis;


    public  SleepModel(){
        this.date=date;
        this.analysis=analysis;
        this.id=id;
    }

    public String getid(){
        return id;
    }

    public void setid(String id){
        this.id=id;
    }


    public String getdate(){
        return date;
    }

    public void setdate(String date){
        this.date=date;
    }

    public String getanalysis(){
        return analysis;
    }

    public void setanalysis(String analysis){
        this.analysis=analysis;
    }


}
