package com.example.observerpatternexample;

import android.os.Handler;

import java.util.Observable;
import java.util.Random;

/**
 * Observable class
 * this is the class that wants to be observed by one or more observers
 * it is SINGLETON
**/

public class UserDataRepository extends Observable {

    private String mFullName;
    private int mAge;
    private static UserDataRepository instance = null;
    private boolean stopHandler = false;

    private UserDataRepository(){
        getNewData();
    }

    public static UserDataRepository getInstance(){
        if(instance == null){
            instance = new UserDataRepository();
        }
        return instance;
    }

    private void getNewData() {
         final Random random = new Random();
         final Handler handler = new Handler();
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               int randomInt = random.nextInt(1000);
               setUserData("Bruce Wayne",randomInt);
               if(randomInt == 100){
                   stopHandler = true;
                   setUserData("A.K.K",88888888);
               }
               if (!stopHandler){
                   handler.postDelayed(this,3000);
               }
           }
       };
        handler.post(runnable);
    }

    public void setUserData(String fullName, int age){
        mFullName = fullName;
        mAge = age;
        setChanged();// this method is indicating that a data has changed
        notifyObservers();// if we want to use push style ,
        // we can pass the changed data as a argument object inside of "notifyObservers" method => notifyObservers(Object arg);
    }

    public String getmFullName() {
        return mFullName;
    }

    public int getmAge() {
        return mAge;
    }
}


