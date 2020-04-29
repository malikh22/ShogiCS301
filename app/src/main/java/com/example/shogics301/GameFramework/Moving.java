package com.example.shogics301.GameFramework;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shogics301.R;


public class Moving extends Activity {


    private Button toGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moving);


        toGame = findViewById(R.id.button10);
        toGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V){
                openGame();

            }
        });

    }

    public void openGame(){

        Intent intent =  new Intent ( this, GameMainActivity.class);
        startActivity(intent);
    }

}
