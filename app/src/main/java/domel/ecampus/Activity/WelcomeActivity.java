package domel.ecampus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import domel.ecampus.R;


public class WelcomeActivity  extends AppCompatActivity {

    private final static int START_DELAY = 2000;
    private Timer t;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        t = new Timer();

    }

    @Override
    protected void onResume(){
        super.onResume();
        task = new TimerTask(){
            @Override
            public void run() {
                //Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        };
        t.schedule(task, START_DELAY);
    }

    @Override
    protected void onPause(){
        super.onPause();
        task.cancel();
    }

}