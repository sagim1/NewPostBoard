package com.myproject.postboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by root on 3/27/16.
 */
public class MyAdapter extends ArrayAdapter {

    private Context context;
    private List<Event> eventList;

    public MyAdapter(Context context, int resource, List<Event> eventList) {
        super(context, resource, eventList);
        this.context=context;
        this.eventList=eventList;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.row, parent, false);
        TextView textView=(TextView)view.findViewById(R.id.textView1);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView1);
        textView.setText(eventList.get(position).getEventName());
        byte[] bytes=eventList.get(position).getEventImage().getBytes();
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageView.setImageBitmap(bitmap);
        return view;
    }
}
