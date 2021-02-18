package com.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.newsapp.myapplication.R;


public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent i = getIntent();
        String img_url = i.getStringExtra("image_url");
        String image_title = i.getStringExtra("image_title");
        final String image_desc = i.getStringExtra("image_desc");

        ImageView imageView = findViewById(R.id.img_banner1);
        TextView txt_title = findViewById(R.id.txt_title1);
        txt_title.setText(image_title);
        final TextView txt_desc = findViewById(R.id.txt_desc);
        txt_desc.setText(image_desc);
        Glide.with(this)
                .load(img_url)
                .into(imageView);
        Button btn_share = findViewById(R.id.btn_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent();
                send.setAction(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_TEXT,image_desc);
                send.setType("text/plain");
                Intent.createChooser(send,"Share Via");
                startActivity(send);
            }
        });

        Button btn_save = findViewById(R.id.btn_sve);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DisplayActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}