package com.example.ananttamkumarsingh.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ANANTTAM KUMAR SINGH on 3/19/2018.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class JSON
    implements Parcelable{

        @SerializedName("ID")
        @Expose
        private int mId;

        @SerializedName("vote_average")
        @Expose
        private Float mRatings;

        @SerializedName("poster_path")
        @Expose
        private String mPosterPath;

        @SerializedName("original_language")
        @Expose
        private String mLanguage;

        @SerializedName("original_title")
        @Expose
        private String mTitle;


        @SerializedName("overview")
        @Expose
        private String mOverview;

        @SerializedName("release_date")
        @Expose
        private String mReleaseDate;

        public JSON()
        {}

        public JSON(int mId, String mTitle, String mPosterPath, String mLanguage, String mOverview,
                    String mReleaseDate, Float mRatings)
        {
            this.mId = mId;
            this.mTitle = mTitle;
            this.mPosterPath = mPosterPath;
            this.mLanguage = mLanguage;
            this.mOverview = mOverview;
            this.mReleaseDate = mReleaseDate;
            this.mRatings = mRatings;
        }

        public JSON(String mTitle, String mPosterPath, String mLanguage, String mOverview,
                    String mReleaseDate, Float mRatings)
        {
            this.mTitle=mTitle;
            this.mPosterPath=mPosterPath;
            this.mLanguage=mLanguage;
            this.mOverview=mOverview;
            this.mReleaseDate=mReleaseDate;
            this.mRatings=mRatings;
        }

        public Integer getId() {
            return mId;
        }
        public String getTitle() {
            return mTitle;
        }
        public String getPosterPath()
        {
            return ("http://image.tmdb.org/t/p/w500"+mPosterPath);
        }
        public String getLanguage() {
            return mLanguage;
        }
        public Float getRatings() {
            return mRatings;
        }
        public String getOverview() {
            return mOverview;
        }
        public String getReleaseDate() {
            return mReleaseDate;
        }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(mPosterPath);
        dest.writeString(mTitle);
        dest.writeString(mLanguage);
        dest.writeFloat(mRatings);
        dest.writeString(mReleaseDate);
        dest.writeString(mOverview);
        dest.writeInt(mId);
    }
    private JSON(Parcel parcel)
    {
        mPosterPath=parcel.readString();
        mTitle=parcel.readString();
        mLanguage=parcel.readString();
        mRatings=parcel.readFloat();
        mReleaseDate=parcel.readString();
        mOverview=parcel.readString();
        mId=parcel.readInt();
    }
    public static final Parcelable.Creator<JSON> CREATOR =new Parcelable.Creator<JSON>()
    {
        @Override
        public JSON createFromParcel(Parcel parcel)
        {
            return new JSON(parcel);
        }
        @Override
        public JSON[] newArray(int i)
        {
            return new JSON[0];
        }
    };


       public int describeContents()
       {
           return hashCode();
       }

        public static List<JSON> getMovieDetailsListFromJsonResponse(String strJsonResponse)
        {
            Gson gson=new Gson();
            JsonElement jsonElement=new Gson().fromJson(strJsonResponse,JsonElement.class);
            JsonObject jsonObject=jsonElement.getAsJsonObject();
            JsonArray jsonArray=jsonObject.getAsJsonArray("results");
            Type listType=new TypeToken<List<JSON>>()
            {}.getType();
            return gson.fromJson(jsonArray.toString(),listType);
        }
    }
















