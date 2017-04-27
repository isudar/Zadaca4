package com.example.sudo.lv4;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etSearchTerm;
    ImageButton bSearch;
    ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setUpUi();
        //this.hookUpListeners();

        CitajRss citajRss = new CitajRss(this);
        citajRss.execute();
    }

    private void setUpUi() {
        this.bSearch = (ImageButton) this.findViewById(R.id.ibSearch);
        this.etSearchTerm = (EditText) this.findViewById(R.id.etSearchTerm);
        this.lvMovies = (ListView) this.findViewById(R.id.lvMovies);
    }
}