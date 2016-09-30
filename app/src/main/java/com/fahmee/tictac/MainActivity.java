package com.fahmee.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int playerId = 0;

    boolean gameActive = true;

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] gameOver = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

    public void dropIn(View view){


        ImageView counter = (ImageView) view;


        int tap = Integer.parseInt(counter.getTag().toString());


        if (gameState[tap]==2 && gameActive) {

            gameState[tap] = playerId;

            counter.setTranslationY(-1000f);
            if (playerId == 0) {

                counter.setImageResource(R.drawable.bubble);
                playerId = 1;


            } else {
                counter.setImageResource(R.drawable.panda);
                playerId = 0;


            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
        }

        for (int[] game: gameOver){
            if (gameState[game[0]]==gameState[game[1]]&&gameState[game[1]]==gameState[game[2]]&& gameState[game[0]]!=2){

                gameActive = false;
                Toast.makeText(getApplicationContext(),"You Have won",Toast.LENGTH_LONG).show();
                String winner = "Panda";

                if (gameState[game[0]]==0){



                    winner = "SmileFace";
                }



                TextView winnerMessage = (TextView)findViewById(R.id.Winner);

                winnerMessage.setText("Player " + winner + " has won! ");

                LinearLayout Layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                Layout.animate().rotation(1800f).setDuration(300);
                Layout.setVisibility(View.VISIBLE);
            }else{
                boolean gameStatus = true;
                for (int draw: gameState){
                    if (draw==2)gameStatus = false;


                }
                if(gameStatus){
                    TextView winnerMessage = (TextView)findViewById(R.id.Winner);

                    winnerMessage.setText("Its a draw!!! ");

                    LinearLayout Layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    Layout.animate().rotation(1800f).setDuration(300);
                    Layout.setVisibility(View.VISIBLE);

                }
            }

        }
    }

    public void playAgain(View view){
        gameActive = true;
        LinearLayout Layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        Layout.animate().rotation(-1800f).setDuration(300);
        Layout.setVisibility(View.INVISIBLE);

        playerId = 0;
        for (int i =0; i < gameState.length;i++){
            gameState[i]=2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i =0;i< gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
