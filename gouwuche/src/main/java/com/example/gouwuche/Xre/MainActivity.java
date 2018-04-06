package com.example.gouwuche.Xre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gouwuche.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView xre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xre = findViewById(R.id.xre);
    }
}
