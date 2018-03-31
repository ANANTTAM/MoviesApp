package com.example.ananttamkumarsingh.popularmoviesapp;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by ANANTTAM KUMAR SINGH on 3/19/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>
{
    List<JSON> jsonList;
    Context context;
    final private ListItemClickListener mOnClickListener;
    private static final String TAG = MoviesAdapter.class.getSimpleName();

    public interface ListItemClickListener
    {
        void onListItemClick(JSON clickedItemIndex);
    }


    public MoviesAdapter(Context mContext, ListItemClickListener listener)
    {
        context=mContext;
        mOnClickListener =listener;
    }
    
    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener
    {

        TextView titleTextView,ratingsTextView;
        ImageView mImageView;

        public MoviesAdapterViewHolder(View view)
        {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.textTitle);
            ratingsTextView = (TextView) view.findViewById(R.id.textRatings);
            mImageView = (ImageView) view.findViewById(R.id.imagePoster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View JSONDetails)
        {

            int position=getAdapterPosition();
            JSON list=jsonList.get(position);
            mOnClickListener.onListItemClick(list);


        }
    }

   @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
   {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movies_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
       View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
       MoviesAdapterViewHolder viewHolder = new MoviesAdapterViewHolder(view);
       return viewHolder;
   }


    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position)
    {
        JSON json = jsonList.get(position);
       holder.titleTextView.setText(json.getTitle());
       holder.ratingsTextView.setText(Float.toString(json.getRatings()));
       String imagePoster = json.getPosterPath();
        Log.d(TAG, "the image url is" + imagePoster);
        Picasso.with(context).load(imagePoster).into(holder.mImageView);
        Log.d(TAG, "#" + position);

    }
    @Override
    public int getItemCount()
    {
        if(jsonList==null)
        {
            return 0;
        } else {
            return jsonList.size();
        }

    }
    public void setMoviesData(List<JSON> moviesData) {
        jsonList=moviesData;
        notifyDataSetChanged();
    }

}







