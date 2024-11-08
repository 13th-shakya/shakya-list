package com.example.shakyalist;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.shakyalist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] transportationMethods = {"腳踏車", "機車", "汽車", "巴士", "飛機", "輪船"};
        int[] transportationImages = {R.drawable.trans1, R.drawable.trans2, R.drawable.trans3, R.drawable.trans4, R.drawable.trans5, R.drawable.trans6};
        Data[] transportationData = new Data[transportationMethods.length];

        for (int i = 0; i < transportationData.length; i++) {
            transportationData[i] = new Data();
            transportationData[i].name = transportationMethods[i];
            transportationData[i].photo = transportationImages[i];
        }

        MyAdapter transportationAdapter = new MyAdapter(transportationData, R.layout.transportation_spinner);
        binding.spinner.setAdapter(transportationAdapter);

        String[] cubeeEmotes = {"哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑", "很棒", "你好", "驚嚇", "大笑"};
        int[] cubeeImages = {R.drawable.cubee1, R.drawable.cubee2, R.drawable.cubee3, R.drawable.cubee4, R.drawable.cubee5, R.drawable.cubee6, R.drawable.cubee7, R.drawable.cubee8, R.drawable.cubee9, R.drawable.cubee10};
        Data[] cubeeData = new Data[cubeeImages.length];

        for (int i = 0; i < cubeeData.length; i++) {
            cubeeData[i] = new Data();
            cubeeData[i].name = cubeeEmotes[i];
            cubeeData[i].photo = cubeeImages[i];
        }

        MyAdapter cubeeAdapter = new MyAdapter(cubeeData, R.layout.cubee_grid);
        binding.gridView.setAdapter(cubeeAdapter);
        binding.gridView.setNumColumns(3);

        String[] messages = {"訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6",};
        ArrayAdapter<String> messageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        binding.listView.setAdapter(messageAdapter);
    }

    class Data {
        int photo;
        String name;
    }

    public class MyAdapter extends BaseAdapter {

        private final Data[] data;
        private final int view;

        public MyAdapter(Data[] data, int view) {
            this.data = data;
            this.view = view;
        }

        public int getCount() {
            return data.length;
        }

        public Object getItem(int position) {
            return data[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(view, parent, false);
            TextView name = convertView.findViewById(R.id.name);
            name.setText(data[position].name);
            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setImageResource(data[position].photo);

            return convertView;
        }
    }
}
