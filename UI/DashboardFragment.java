package com.example.artexhibition.ui.dashboard;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artexhibition.FavAdapter;
import com.example.artexhibition.FavDB;
import com.example.artexhibition.FavItem;
import com.example.artexhibition.R;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavDB favDB;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

            favDB = new FavDB(getActivity());
            recyclerView = root.findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            loadData();

            return root;
        }

    private void loadData() {
            if (favItemList != null){
                favItemList.clear();
            }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()){
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_TITLE));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                @SuppressLint("Range") int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavDB.ITEM_IMAGE)));
                FavItem favItem = new FavItem(title,id,image);
                favItemList.add(favItem);
            }
            }finally {
                if (cursor != null && cursor.isClosed())
                    cursor.close();
                db.close();
        }
        favAdapter = new FavAdapter(favItemList, getActivity());
        recyclerView.setAdapter(favAdapter);

    }
}
