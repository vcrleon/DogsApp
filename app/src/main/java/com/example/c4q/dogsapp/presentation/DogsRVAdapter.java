package com.example.c4q.dogsapp.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.c4q.dogsapp.R; //class R
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by c4q on 6/6/18.
 */

public class DogsRVAdapter extends RecyclerView.Adapter<DogsRVAdapter.DogViewHolder> {
    List<String> imageUrls;

    public DogsRVAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item_view, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        holder.bindImage(imageUrls.get(position));

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder{

        private ImageView dogImageView;

        public DogViewHolder(View itemView) {
            super(itemView);
            dogImageView = itemView.findViewById(R.id.dog_image); //looking inside itemview . The R class gets recreated every time you build a project and if it's red, it means the compiler didn't recreate it
        }

        void bindImage(String imageUrl){

            Picasso.with(dogImageView.getContext()).load(imageUrl).into(dogImageView);
        }
    }

}
