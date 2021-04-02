package com.example.healthcompanion;

public class ExerciseModel {
     String creationDate;
     String START;
     String STOP;

    public ExerciseModel(){
        this.creationDate= creationDate;
        this.START=START;
        this.STOP=STOP;
    }

    public String getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(String creationDate){
        this.creationDate=creationDate;
    }

    public String getSTART(){
        return START;
    }

    public void setSTART(String START){
        this.START=START;
    }

    public String getSTOP(){
        return STOP;
    }

    public void setSTOP(String STOP){
        this.STOP=STOP;
    }


}
