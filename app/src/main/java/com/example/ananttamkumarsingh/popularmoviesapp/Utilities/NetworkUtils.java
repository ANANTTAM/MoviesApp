    package com.example.ananttamkumarsingh.popularmoviesapp.Utilities;


    import android.net.Uri;
    import android.util.Log;
    import java.io.IOException;
    import java.io.InputStream;
    import java.net.HttpURLConnection;
    import java.net.MalformedURLException;
    import java.net.URL;
    import java.util.Scanner;

/**
 * Created by ANANTTAM KUMAR SINGH on 3/18/2018.
 */

    public class NetworkUtils {


    final static String STATIC_POPULAR_MOVIES_URL= "http://api.themoviedb.org/3/movie/popular";
    final static String STATIC_TOP_RATED_MOVIES_URL = "http://api.themoviedb.org/3/movie/top_rated";
    final static String PARAM_MOVIE = "q";
    final static String PARAM_API_KEY = "api_key";
    final static String key="1aebbca3b1432c1fb3264a8b652c12e9";
    final static String Tag = NetworkUtils.class.getSimpleName();

    /**
     * Builds the URL used to query popularMovies.
     * @return The URL to use to query the Movies server.
     * @param
     */

    public static URL buildUrl()
    {
        Uri builtUri=
                Uri.parse(STATIC_POPULAR_MOVIES_URL).buildUpon()

                        .appendQueryParameter(PARAM_API_KEY,key)
                        .build();

        URL url=null;
        try
        {
            url = new URL(builtUri.toString());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        Log.d(Tag,"Error:"+url);
        return url;

    }


    /**
     * Builds the URL used to query topratedMovies.
     * @return The URL to use to query the Movies server.
     * @param
     */





    public static URL buildTopRatedUrl(){
        Uri builtUri=
                Uri.parse(STATIC_TOP_RATED_MOVIES_URL).buildUpon()

                        .appendQueryParameter(PARAM_API_KEY,key)
                        .build();

        URL url=null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        Log.d(Tag,"Error:"+url);
        return url;

    }




    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}




