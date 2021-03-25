package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int activePlayer=0;//0 for yellow 1 for red and 2 for empty
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        counter.setTranslationY(-1500);
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&&gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {
                   gameActive=false;
                    String Winner = "";
                    if (activePlayer == 1)
                        Winner = "Yellow";
                    else
                        Winner = "Red";

                    Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText("The winner is " + Winner);
                    playAgainButton.setVisibility(view.VISIBLE);
                    winnerTextView.setVisibility(view.VISIBLE);

                }
            }
        }

    }
    public void playAgain(View view)
    {
        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
         gameActive=true;
         activePlayer=0;//0 for yellow 1 for red and 2 for empty
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}