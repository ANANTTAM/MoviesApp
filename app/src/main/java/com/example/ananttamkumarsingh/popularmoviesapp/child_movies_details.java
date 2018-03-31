package com.example.ananttamkumarsingh.popularmoviesapp;

import android.content.ContentValues;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.Locale;


/**
 * Created by ANANTTAM KUMAR SINGH on 3/23/2018.
 */

public class child_movies_details extends AppCompatActivity {


    private static final String TAG = Main2Activity.class.getSimpleName();
    private ImageView mChildPoster;
    private TextView mChildTitle;
    private TextView mChildLanguage;
    private TextView mChildRatings;
    private TextView mChildReleaseDate;
    private TextView mChildOverview;


    private String mPosterPath;
    private String mTitle;
    private String mLanguage;
    private String mRatings;
    private String mReleaseDate;
    private String mOverview;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_movies_details);

        mChildPoster=(ImageView)findViewById(R.id.iv_child_poster);
        mChildTitle=(TextView) findViewById(R.id.tv_child_title);
        mChildLanguage=(TextView) findViewById(R.id.tv_child_Language);
       mChildRatings=(TextView) findViewById(R.id.tv_child_ratings);
        mChildReleaseDate=(TextView) findViewById(R.id.tv_child_release_date);
        mChildOverview=(TextView) findViewById(R.id.tv_child_overview);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent datafromParent;
        datafromParent = getIntent();

        mPosterPath=datafromParent.getStringExtra("mDataFromIntent");
        mTitle=datafromParent.getStringExtra("mDataFromIntent1");
        mRatings= datafromParent.getStringExtra("mDataFromIntent2");
        mOverview=datafromParent.getStringExtra("mDataFromIntent3");
        mReleaseDate=datafromParent.getStringExtra("mDataFromIntent4");
        mLanguage=datafromParent.getStringExtra("mDataFromIntent5");


        Picasso.with(this)
                .load(String.valueOf(mPosterPath)).into(mChildPoster);
        mChildTitle.setText(mTitle);
        mChildLanguage.setText(mLanguage);
        mChildRatings.setText("("+mRatings+"/10)");
        mChildReleaseDate.setText(mReleaseDate);
        mChildOverview.setText(mOverview);
        Log.d(TAG, "The value of rating is::::::::::::::::::::::::::::::::::::::::::::::::::::::::: "+mRatings);
    }

    }





