package com.example.artexhibition;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artexhibition.drawings.Impresstion;
import com.example.artexhibition.drawings.Mona;
import com.example.artexhibition.drawings.Vang;
import com.example.artexhibition.drawings.cafe;
import com.example.artexhibition.drawings.gurica;
import com.example.artexhibition.drawings.infoActivity;
import com.example.artexhibition.drawings.lilies;
import com.example.artexhibition.drawings.magpie;
import com.example.artexhibition.drawings.rocket;
import com.example.artexhibition.drawings.scream;
import com.example.artexhibition.drawings.supper;
import com.example.artexhibition.drawings.vase;

import java.util.ArrayList;


public class DrawingAdapter extends RecyclerView.Adapter<DrawingAdapter.ViewHolder> {

        private ArrayList<DrawingItem> drawingItems;
        private Context context;
        private FavDB favDB;

        public DrawingAdapter(ArrayList<DrawingItem> drawingItems, Context context) {
            this.drawingItems = drawingItems;
            this.context = context;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            favDB = new FavDB(context);

            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                createTableOnFirstStart();
            }

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                    parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawingAdapter.ViewHolder holder, int position) {
            final DrawingItem drawingItem = drawingItems.get(position);
            readCursorData(drawingItem, holder);
            holder.imageView.setImageResource(drawingItem.getImageResourse());
            holder.titleTextView.setText(drawingItem.getTitle());
            holder.titleDescView.setText(drawingItem.getDesc());
        }
        @Override
        public int getItemCount() {

            return drawingItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView imageView;
            TextView titleTextView, titleDescView;
            Button favBtn;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.image_view);
                titleTextView = itemView.findViewById(R.id.textTitleView);
                titleDescView = itemView.findViewById(R.id.textDescView);
                favBtn = itemView.findViewById(R.id.favBtn);
                itemView.setOnClickListener(this);


                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        DrawingItem drawingItem = drawingItems.get(position);
                        if (drawingItem.getFavStatus().equals("0")) {
                            drawingItem.setFavStatus("1");
                            favDB.insertIntoTheDatabase(drawingItem.getTitle(), drawingItem.getImageResourse(),
                                    drawingItem.getKey_id(), drawingItem.getFavStatus());
                            favBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);

                        } else {
                            drawingItem.setFavStatus("0");
                            favDB.remove_fav(drawingItem.getKey_id());
                            favBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                        } }}); }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position == 0) {
                    view.getContext().startActivity(new Intent(view.getContext(), infoActivity.class));
                } else if (position == 1) {
                    view.getContext().startActivity(new Intent(view.getContext(), Vang.class));
                } else if (position == 2) {
                    view.getContext().startActivity(new Intent(view.getContext(), vase.class));
                }else if (position == 3) {
                    view.getContext().startActivity(new Intent(view.getContext(), cafe.class));
                }else if (position == 4) {
                    view.getContext().startActivity(new Intent(view.getContext(), gurica.class));
                }else if (position == 5) {
                    view.getContext().startActivity(new Intent(view.getContext(), scream.class));
                }else if (position == 6) {
                    view.getContext().startActivity(new Intent(view.getContext(), rocket.class));
                }else if (position == 7) {
                    view.getContext().startActivity(new Intent(view.getContext(), Mona.class));
                }else if (position == 8) {
                    view.getContext().startActivity(new Intent(view.getContext(), Impresstion.class));
                }else if (position == 9) {
                    view.getContext().startActivity(new Intent(view.getContext(), supper.class));
                }else if (position == 10) {
                    view.getContext().startActivity(new Intent(view.getContext(), lilies.class));
                }else if (position == 11) {
                    view.getContext().startActivity(new Intent(view.getContext(), magpie.class));
                }
            }}
            private void createTableOnFirstStart() {
                favDB.insertEmpty();

                SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstStart", false);
                editor.apply();
            }
            private void readCursorData(DrawingItem drawingItem, ViewHolder viewHolder) {
                Cursor cursor = favDB.read_all_data(drawingItem.getKey_id());
                SQLiteDatabase db = favDB.getReadableDatabase();
                try {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                        drawingItem.setFavStatus(item_fav_status);

                        //check fav status
                        if (item_fav_status != null && item_fav_status.equals("1")) {
                            viewHolder.favBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                        } else if (item_fav_status != null && item_fav_status.equals("0")) {
                            viewHolder.favBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_dis_24);
                        }
                    }
                } finally {
                    if (cursor != null && cursor.isClosed())
                        cursor.close();
                    db.close(); } }}
