package com.example.musicplayer;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SongSelectAdapter extends ArrayAdapter<Song> {

    private Context context;
    private List<Song> songs;
    private SparseBooleanArray checkedItems;

    public SongSelectAdapter(Context context, List<Song> songs) {
        super(context, 0, songs);
        this.context = context;
        this.songs = songs;
        this.checkedItems = new SparseBooleanArray();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_song_select, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvArtist = convertView.findViewById(R.id.tvArtist);
        CheckBox cbSelect = convertView.findViewById(R.id.cbSelect);

        tvTitle.setText(song.getTitle());
        tvArtist.setText(song.getArtist());

        // Cập nhật trạng thái checkbox đúng với dữ liệu
        cbSelect.setOnCheckedChangeListener(null);
        cbSelect.setChecked(checkedItems.get(position, false));

        // Xử lý khi người dùng tick
        cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedItems.put(position, isChecked);
        });

        return convertView;
    }

    // Trả về danh sách các bài hát đã chọn
    public ArrayList<Song> getSelectedSongs() {
        ArrayList<Song> selected = new java.util.ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            if (checkedItems.get(i, false)) {
                selected.add(songs.get(i));
            }
        }
        return selected;
    }
}