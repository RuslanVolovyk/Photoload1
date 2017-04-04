package com.softgroup.photoload1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private String contents;

    @Bind(R.id.edit_text_input)
    EditText editText;

    @Bind(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start)
    public void loadImage() {
        Glide.with(this)
                .load(editText.getText().toString())
                .into(imageView);
    }

    @OnClick(R.id.btn_qr_code)
    public void getQRcode() {
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
            } else {
                contents = result.getContents();
                editText.setText(contents);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
