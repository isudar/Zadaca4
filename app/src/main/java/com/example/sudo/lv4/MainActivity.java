package com.example.sudo.lv4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    EditText etSearchTerm;
    ImageButton ibSearch;
    Button bOsvježi;

    String prazno  = "";
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = (RecyclerView) findViewById(R.id.rvNews);
        this.etSearchTerm = (EditText) findViewById(R.id.etSearchTerm);
        this.ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        this.bOsvježi = (Button) findViewById(R.id.bOsvježi);

        CitajRss citajRss = new CitajRss(this, recyclerView, prazno);
        citajRss.execute();

        bOsvježi.setOnClickListener(this);
        ibSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bOsvježi:
                CitajRss citajRss = new CitajRss(this, recyclerView, prazno);
                citajRss.execute();
                break;
            case R.id.ibSearch:
                category = etSearchTerm.getText().toString();
                CitajRss filter = new CitajRss(this, recyclerView, category);
                filter.execute();
                break;
        }
    }
}