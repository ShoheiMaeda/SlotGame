package jp.slot777game.slotgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_roll;

    ImageView image1,image2,image3;

    Random r;

    int img1,img2,img3;

    int exp;

    int explotion[] = new int[3];

    int coin = 5;

    int mgamestate = 1;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        b_roll = (Button) findViewById(R.id.b_roll);

        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(String.valueOf(coin));

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);

        image1.setImageResource(R.drawable.ic_hatena);
        image2.setImageResource(R.drawable.ic_hatena);
        image3.setImageResource(R.drawable.ic_hatena);

        b_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mgamestate == 0){
                    coin = coin + 3;
                    mgamestate = 1;
                }

                coin = coin - 1;

                TextView text = (TextView) findViewById(R.id.textView);
                text.setText(String.valueOf(coin));

                image1.setImageResource(R.drawable.anim);
                final AnimationDrawable image1anim = (AnimationDrawable) image1.getDrawable();
                image1anim.start();

                image2.setImageResource(R.drawable.anim);
                final AnimationDrawable image2anim = (AnimationDrawable) image2.getDrawable();
                image2anim.start();

                image3.setImageResource(R.drawable.anim);
                final AnimationDrawable image3anim = (AnimationDrawable) image3.getDrawable();
                image3anim.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        image1anim.stop();
                        image2anim.stop();
                        image3anim.stop();

                        setImages();

                        getScore();

                    }
                }, 500);


            }
        });




    }

    public void  setImages(){

        img1 = r.nextInt(6) + 1;
        img2 = r.nextInt(6) + 1;
        img3 = r.nextInt(6) + 1;

        switch (img1){
            case 1:
                image1.setImageResource(R.drawable.ic_knife);
                break;
            case 2:
                image1.setImageResource(R.drawable.ic_ninja1);
                break;
            case 3:
                image1.setImageResource(R.drawable.ic_ninja2);
                break;
            case 4:
                image1.setImageResource(R.drawable.ic_shuriken1);
                break;
            case 5:
                image1.setImageResource(R.drawable.ic_shuriken2);
                break;
            case 6:
                image1.setImageResource(R.drawable.ic_explotion);
                explotion[0] = 1;
                break;
        }

        switch (img2){
            case 1:
                image2.setImageResource(R.drawable.ic_knife);
                break;
            case 2:
                image2.setImageResource(R.drawable.ic_ninja1);
                break;
            case 3:
                image2.setImageResource(R.drawable.ic_ninja2);
                break;
            case 4:
                image2.setImageResource(R.drawable.ic_shuriken1);
                break;
            case 5:
                image2.setImageResource(R.drawable.ic_shuriken2);
                break;
            case 6:
                image2.setImageResource(R.drawable.ic_explotion);
                explotion[1] = 1;
                break;
        }

        switch (img3){
            case 1:
                image3.setImageResource(R.drawable.ic_knife);
                break;
            case 2:
                image3.setImageResource(R.drawable.ic_ninja1);
                break;
            case 3:
                image3.setImageResource(R.drawable.ic_ninja2);
                break;
            case 4:
                image3.setImageResource(R.drawable.ic_shuriken1);
                break;
            case 5:
                image3.setImageResource(R.drawable.ic_shuriken2);
                break;
            case 6:
                image3.setImageResource(R.drawable.ic_explotion);
                explotion[2] = 1;
                break;
        }
    }


    public void getScore(){

        exp = explotion[0]+explotion[1]+explotion[2];

        if(exp == 3){
            Toast ts = Toast.makeText(this,"大爆発!　-５",Toast.LENGTH_SHORT);
            ts.setGravity(Gravity.TOP, 0, 630);
            ts.show();
            coin = coin - 5;
        }else if(exp == 2){
            Toast ts = Toast.makeText(this,"爆発!　-3",Toast.LENGTH_SHORT);
            ts.setGravity(Gravity.TOP, 0, 630);
            ts.show();
            coin = coin - 3;
        }else if(exp == 1) {
            Toast ts = Toast.makeText(this, "小爆発!　-1", Toast.LENGTH_SHORT);
            ts.setGravity(Gravity.TOP, 0, 630);
            ts.show();
            coin = coin - 1;

            if (img1 == img2 || img2 == img3 || img1 == img3) {
                Toast t = Toast.makeText(this, "小当たり!　＋２!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP, 0, 800);
                t.show();
                coin = coin + 2;
            }
        }else {

            if (img1 == img2 && img2 == img3) {
                Toast ts = Toast.makeText(this, "大当たり!　＋５", Toast.LENGTH_SHORT);
                ts.setGravity(Gravity.TOP, 0, 750);
                ts.show();
                coin = coin + 5;
            } else if (img1 == img2 || img2 == img3 || img1 == img3) {
                Toast ts = Toast.makeText(this, "小当たり!　＋２!", Toast.LENGTH_SHORT);
                ts.setGravity(Gravity.TOP, 0, 750);
                ts.show();
                coin = coin + 2;
            }

        }

        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(String.valueOf(coin));

        if(coin < 1){

            Intent intent = new Intent(MainActivity.this, Title.class);
            mgamestate = 0;

        }

        for (int i = 0;i<=2;i++) {
            explotion[i] = 0;
        }

        exp = 0;

    }

}