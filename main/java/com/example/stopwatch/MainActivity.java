package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {


    int seconds = 0;
    boolean running;
    boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if(savedInstanceState !=null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");

        }

        runTimer();
    }


    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running; //if the activity's paused, stop the stopwatch
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning); //save the state of the wasRunning variable
        super.onSaveInstanceState(outState);
    }

    public void onClickStart(View view) {
        running = true; //start the stopwatch running
    }


    public void onClickStop(View view) {
        running = false; //stop the stopwatch running
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.timeview);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;


                String time = String.format("%d:%02d:%02d",hours, minutes, secs);
                timeView.setText(time);

                if(running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }




}
