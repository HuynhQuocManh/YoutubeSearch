package com.huynhquocmanh.youtubesearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huynhquocmanh.youtubesearch.Model.Item;
import com.huynhquocmanh.youtubesearch.Model.ModelData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YoutubeAdapter extends ArrayAdapter<Item> {
    public YoutubeAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);

    }

    class ViewHolder{
        TextView txtTen;
        ImageView imghinh;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_video,null);
            viewHolder = new ViewHolder();
            viewHolder.imghinh = convertView.findViewById(R.id.imageviewThumbnail);
            viewHolder.txtTen = convertView.findViewById(R.id.textviewTitle);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Item item = getItem(position);

        viewHolder.txtTen.setText(item.getSnippet().getTitle());
        Picasso.get().load(item.getSnippet().getThumbnails().getMedium().getUrl()).into(viewHolder.imghinh);

        return convertView;
    }
}
