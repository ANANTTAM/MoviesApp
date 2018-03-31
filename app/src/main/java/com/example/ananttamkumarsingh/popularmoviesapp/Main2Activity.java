package com.example.ananttamkumarsingh.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananttamkumarsingh.popularmoviesapp.Utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.transform.Result;

    public class Main2Activity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener
    {
        private static final int MOVIES_NUM_LIST_ITEMS = 20;
//        private MoviesAdapter mAdapter;
//        private RecyclerView mNumbersList;
//        private Toast mToast;
        private RecyclerView mRecyclerView;
        private MoviesAdapter mMoviesAdapter;
        private TextView mErrorMessageDisplay;
        private ProgressBar mLoadingIndicator;
        GridLayoutManager gridLayoutManager;
        private static final String TAG1 = MainActivity.class.getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int column=2;

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator =  findViewById(R.id.pb_loading_indicator);

        gridLayoutManager
                = new GridLayoutManager(this,column, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMoviesAdapter = new MoviesAdapter(Main2Activity.this,this);
        mRecyclerView.setAdapter(mMoviesAdapter);
        makePopularSearch();
        makeTopRatedSearch();


    }
        private void showMoviesDataView() {

        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage()
    {

        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }


    private void makePopularSearch()
    {
       // String topRatedMoviesQuery = mSearchBoxEditText.getText().toString();
        URL popularMoviesSearchUrl = NetworkUtils.buildUrl();
       // mUrlDisplayTextView.setText(topRatedMoviesSearchUrl.toString());
      //  String popularMoviesSearchResult = null;
        new Movies().execute(popularMoviesSearchUrl);

    }
    private void makeTopRatedSearch()
    {
        // String topRatedMoviesQuery = mSearchBoxEditText.getText().toString();
        URL topRatedMoviesSearchUrl = NetworkUtils.buildTopRatedUrl();
        // mUrlDisplayTextView.setText(topRatedMoviesSearchUrl.toString());
        //  String popularMoviesSearchResult = null;
        new Movies().execute(topRatedMoviesSearchUrl);

    }


    public class Movies extends AsyncTask<URL , Void ,List<JSON>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<JSON> doInBackground(URL... urls)
        {
            URL topRatedMoviesSearchUrl = urls[0];
            String result = null;
            List<JSON> jsonList=null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(topRatedMoviesSearchUrl);
                jsonList=JSON.getMovieDetailsListFromJsonResponse(result);
                return jsonList;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override

        protected void onPostExecute(List<JSON> s)
        {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(s!=null)
            {
                showMoviesDataView();
                mMoviesAdapter.setMoviesData(s);
                mMoviesAdapter.notifyDataSetChanged();
            } else
            {
                showErrorMessage();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.movies_poster, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThat=item.getItemId();
        if (itemThat == R.id.action_mostpopular)
        {


           makePopularSearch();

            return true;
        }
        if (itemThat == R.id.action_toprated)
        {
            makeTopRatedSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
        public void onListItemClick(JSON clickedItemIndex)
        {
            Context parentClass =Main2Activity.this;
            Class childclass = child_movies_details.class;
            Intent dataPassToChildClass=new Intent(parentClass,childclass);


            dataPassToChildClass.putExtra("mDataFromIntent",clickedItemIndex.getPosterPath());
            dataPassToChildClass.putExtra("mDataFromIntent1",clickedItemIndex.getTitle());
            dataPassToChildClass.putExtra("mDataFromIntent2",clickedItemIndex.getRatings().toString());
            dataPassToChildClass.putExtra("mDataFromIntent3",clickedItemIndex.getOverview());
            dataPassToChildClass.putExtra("mDataFromIntent4",clickedItemIndex.getReleaseDate());
            dataPassToChildClass.putExtra("mDataFromIntent6",clickedItemIndex.getLanguage());


            startActivity(dataPassToChildClass);
//            if(mToast!=null){
//                mToast.cancel();
//
//            }
//            String toastMessage = "Item #" + clickedItemIndex + " clicked.";
//            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//
//            mToast.show();

        }



    }



