package com.example.admin.sharedpreferencesscoresave;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_Score;
    private Button btn_Increase,btn_Decrease;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeObj();

        if (loadScore() != 0){
            tv_Score.setText("Score : "+loadScore());
        }

        btn_Increase.setOnClickListener(this);
        btn_Decrease.setOnClickListener(this);



    }

    private void makeObj() {
        tv_Score = (TextView)findViewById(R.id.tv_Score);
        btn_Increase = (Button)findViewById(R.id.btn_Increase);
        btn_Decrease = (Button)findViewById(R.id.btn_Decrease);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_Increase){
           score = score + 5;
           tv_Score.setText("Score : "+score);
           saveScore(score);
        }
        else if (v.getId() == R.id.btn_Decrease){
            score = score - 5;
            tv_Score.setText("Score : "+score);
            saveScore(score);

        }

    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("getScore",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore",score);
        editor.apply();
    }
    private int loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("getScore",Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore",0);
        return score;
    }
}
