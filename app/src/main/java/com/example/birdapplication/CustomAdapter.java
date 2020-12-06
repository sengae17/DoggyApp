package com.example.birdapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdapplication.data.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Dog> listOfDogs;
    private OnItemClickListener mListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dogName;
        private final ImageView dogImage;

        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.dogName = itemView.findViewById(R.id.tv_listItemName);
            this.dogImage = itemView.findViewById(R.id.iv_listItemAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public TextView getDogName() {
            return dogName;
        }

        public ImageView getDogImage() {
            return dogImage;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public CustomAdapter(Context context, List<Dog> dataSet) {
        this.context = context;
        this.listOfDogs = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bird_list_item, viewGroup, false);

        return new ViewHolder(view, mListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getDogName().setText(listOfDogs.get(position).getName());
        Drawable icon = getRandomIcon(position);
        viewHolder.getDogImage().setImageDrawable(icon);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfDogs.size();
    }

    private Drawable getRandomIcon(int position) {
        ArrayList<Drawable> icons = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            int id;

            if (i < 10) {
                id = context.getResources().getIdentifier("dog_00" + i, "drawable", context.getPackageName());
            } else {
                id = context.getResources().getIdentifier("dog_0" + i, "drawable", context.getPackageName());
            }
            Drawable d = ContextCompat.getDrawable(context, id);
            icons.add(d);
        }

        int index = position % icons.size();
        return icons.get(index);
    }
}

