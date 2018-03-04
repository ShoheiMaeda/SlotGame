package jp.slot777game.slotgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOver extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void RetryButton(View v){
        Intent intent = new Intent(this,Title.class);
        startActivity(intent);
    }
}
