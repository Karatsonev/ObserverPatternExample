package com.example.observerpatternexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private Observable mUserDataRepositoryObservable;
    private TextView tvUserFullName;
    private TextView tvUserAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        //get instance of UserDataRepository
        mUserDataRepositoryObservable = UserDataRepository.getInstance();
        //subscribe from MainActivity to observe the changes in the Observable
        mUserDataRepositoryObservable.addObserver(this);
    }

    private void initViews() {
        tvUserFullName = findViewById(R.id.tvUserFullName);
        tvUserAge = findViewById(R.id.tvUserAge);
    }

    //updating the ui when data changes
    @Override
    public void update(Observable observable, Object arg) {
        if( observable instanceof UserDataRepository){
            UserDataRepository userDataRepository = (UserDataRepository)observable;
            tvUserFullName.setText(userDataRepository.getmFullName());
            tvUserAge.setText(String.valueOf(userDataRepository.getmAge()));

        }
    }
}
