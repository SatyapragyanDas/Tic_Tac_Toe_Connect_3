package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0:Yellow, 1:Red, 2:Empty
    boolean game_active=true;
    int active_player=0;
    int[] game_status={2,2,2,2,2,2,2,2,2};
    int[][] winning_position={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void drop(View view) {
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if((game_status[tappedCounter]==0 || game_status[tappedCounter]==1) && game_active){
            return;
        }
        counter.setTranslationY(-1500);
        if(active_player==0){
            counter.setImageResource(R.drawable.yellow);
            game_status[tappedCounter]=0;
            active_player=1;
        }
        else if(active_player==1){
            counter.setImageResource(R.drawable.red);
            game_status[tappedCounter]=1;
            active_player=0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
        for(int[] winning_position:winning_position){
            if(game_status[winning_position[0]]==game_status[winning_position[1]] && game_status[winning_position[1]]==game_status[winning_position[2]] &&  game_status[winning_position[0]]!=2){
                game_active=false;
                String winner="";
                if(active_player==1)
                    winner="Yellow";
                else
                    winner="Red";
                Toast.makeText(this, winner+"has WON!", Toast.LENGTH_SHORT).show();
                Button play_again=(Button) findViewById(R.id.button);
                TextView winner_display=(TextView)findViewById(R.id.textView);
                winner_display.setText(winner+"has WON!");
                play_again.setVisibility(View.VISIBLE);
                winner_display.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view) {
        Button play_again = (Button) findViewById(R.id.button);
        TextView winner_display = (TextView) findViewById(R.id.textView);
        play_again.setVisibility(View.INVISIBLE);
        winner_display.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        game_active = true;
        active_player = 0;
        for (int i = 0; i < game_status.length; i++) {
            game_status[i] = 2;
        }
    }

}