package com.example.catchn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;


import java.util.Random;


public class MainActivity<imageArray> extends AppCompatActivity {
    TextView Score;
    TextView Time;
    int goal;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageArray = new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        Time = findViewById(R.id.Time);
        Score = findViewById(R.id.Score);
        goal = 0;

        hideBeers();


        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                Time.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Time.setText("Time Stopped");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("U Sure Man?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Aktiviteyi baştan başlatmak için alttaki 3 satır.
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over" , Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void increaseScore (View view) {
        goal++;
        Score.setText("Score: " + goal);

    }

    public void hideBeers(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);
            }
        };

        handler.post(runnable);

    }
}