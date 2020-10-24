package com.example.crp_recomendation.crpYojana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crp_recomendation.R;

public class YojanaDetail extends AppCompatActivity implements View.OnTouchListener {

    TextView guj ;
    TextView eng;
    TextView link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_yojana_info);

        eng = (TextView) findViewById(R.id.desEng);
        guj =(TextView) findViewById(R.id.desGuj);
        link = (TextView) findViewById(R.id.link);

        Bundle bundle = getIntent().getExtras();
        guj.setText(bundle.getString("descGuj"));
        eng.setText(bundle.getString("descEng"));
        link.setText(bundle.getString("link"));

        link.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getText().toString()));

        startActivity(browserIntent);

        return true;
    }
}
