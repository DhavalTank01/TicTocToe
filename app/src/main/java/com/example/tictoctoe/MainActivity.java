package com.example.tictoctoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //    player
    //    0 - X
    //    1 - O

    int activePlayer = 0;
    boolean gameActive = false;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //state
    //    0 - X
    //    1 - O
    //    2 - NULL
    int[][] winPositions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6},
    };

    public void PlayerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
//    if empty
        if (!gameActive) {
            gameRestart(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setTextColor(getResources().getColor(R.color.black));
                status.setText("O's Turn - Tap to play");
            } else {
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.status);
                status.setTextColor(getResources().getColor(R.color.red));
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                String WinStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    WinStr = "X has won";
                } else {
                    WinStr = "O has won";
                }
                TextView status = findViewById(R.id.status);
                status.setTextColor(getResources().getColor(R.color.purple_500));
                status.setText(WinStr);
            }
        }

//        check game is end or not
        boolean isGameEnd = true;
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 2) {
                isGameEnd = false;
            }
        }
        if (isGameEnd && gameActive == true) {
            TextView status = findViewById(R.id.status);
            status.setTextColor(getResources().getColor(R.color.purple_500));
            status.setText("Game is Over.\nStart new Game.");
            gameActive = false;
            isGameEnd = true;
        }
    }

    //    restart the game
    public void gameRestart(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setTextColor(getResources().getColor(R.color.red));
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView status = findViewById(R.id.status);
        status.setTextColor(getResources().getColor(R.color.red));
        status.setText("X's Turn - Tap to play");
    }
}
