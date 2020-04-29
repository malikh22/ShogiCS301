package com.example.shogics301.GameFramework;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.shogics301.R;



public class Rules extends Activity {

    private Button toGame;
    private Button moving;
    private Button promotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);


        toGame = findViewById(R.id.button7);
        toGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V){
                openGame();

            }
        });


        moving = findViewById(R.id.button3);
        moving.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V){
                openMoving();

            }
        });

        promotion = findViewById(R.id.button5);
        promotion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V){
                openPromotion();

            }
        });

    }

    public void openGame(){

        Intent intent =  new Intent ( this, GameMainActivity.class);
        startActivity(intent);
    }

    public void openPromotion(){

        Intent intent =  new Intent ( this, Promotion.class);
        startActivity(intent);
    }

    public void openMoving(){

        Intent intent =  new Intent ( this, Moving.class);
        startActivity(intent);
    }
}
