package com.example.artexhibition.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artexhibition.DrawingAdapter;
import com.example.artexhibition.DrawingItem;
import com.example.artexhibition.R;
import java.util.ArrayList;

public class HomeFragment extends Fragment{


        private ArrayList<DrawingItem> drawingItems = new ArrayList<>();

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_home, container, false);

            RecyclerView recyclerView = root.findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new DrawingAdapter(drawingItems, getActivity()));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            drawingItems.add(new DrawingItem(R.drawable.girl_with_a_pearl_earing, "Girls with a pearl Earring","0","0",getString(R.string.drawing1_desc)));
            drawingItems.add(new DrawingItem(R.drawable.self_portait_ear, "Self Portrait Ear","1","0",getString(R.string.drawing2_desc)));
            drawingItems.add(new DrawingItem(R.drawable.vase, "Vase with 12 Sunflowers","2","0",getString(R.string.drawing3_desc)));
            drawingItems.add(new DrawingItem(R.drawable.night_cafe, "Coffee Terrace at Night","3","0",getString(R.string.drawing4_desc)));
            drawingItems.add(new DrawingItem(R.drawable.gurnica, "Guernica","4","0",getString(R.string.drawing5_desc)));
            drawingItems.add(new DrawingItem(R.drawable.the_scream, "The Scream","5","0",getString(R.string.drawing6_desc)));
            drawingItems.add(new DrawingItem(R.drawable.the_falling_rocket, "Nocturne in Black and Gold, the Falling Rocket","6","0",getString(R.string.drawing7_desc)));
            drawingItems.add(new DrawingItem(R.drawable.monalisa, "Monalisa","7","0",getString(R.string.drawing8_desc)));
            drawingItems.add(new DrawingItem(R.drawable.impresstion_sunrise, "Impression, Sunrise","8","0",getString(R.string.drawing9_desc)));
            drawingItems.add(new DrawingItem(R.drawable.the_last_supper,"The Last Supper", "9","0",getString(R.string.drawing9_desc)));
            drawingItems.add(new DrawingItem(R.drawable.water_lilies, "Water Lilies","10","0",getString(R.string.drawing10_desc)));
            drawingItems.add(new DrawingItem(R.drawable.the_magpie, "The Magpie","11","0",getString(R.string.drawing11_desc)));
            return root;
        }}
