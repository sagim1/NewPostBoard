package com.myproject.postboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView1;
    private Firebase firebase;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this, AddEventActivity.class);
        //startActivity(intent);
        init();
    }

    public void init()
    {
        listView1=(ListView)findViewById(R.id.listView1);
        eventList=new ArrayList<Event>();
        firebase = new Firebase("https://postboard.firebaseio.com");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Event event = dataSnapshot1.getValue(Event.class);
                    eventList.add(event);
                }
                ImageView imageView=(ImageView)findViewById(R.id.imageView1);
                byte[] image=eventList.get(0).getEventImage().getBytes();
                Img.bitmap2=eventList.get(0).getEventImage();

                Bitmap bitmap= BitmapFactory.decodeByteArray(image, 0, image.length);
                imageView.setImageBitmap(bitmap);
                Log.i("IMG", eventList.get(0).getEventImage());
                //listView1.setAdapter(new MyAdapter(MainActivity.this, R.id.imageView1, eventList));
                if((Img.bitmap1).equals(Img.bitmap2))
                    Toast.makeText(MainActivity.this, "Equal", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Not Equal", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_1:
                displayOptionsDialog(1);
                break;
            case R.id.item_2:
                displayOptionsDialog(2);
                break;
            case R.id.item_3:
                Intent intent=new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
        }
        return true;
    }

    public void displayOptionsDialog(int index)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        final String[] options;
        if(index==1)
        {
            options=new String[]{"Science CLubs", "Art Clubs", "Sports Clubs", "Honor Clubs", "Greek Groups", "Charity Clubs", "Religious Groups", "All Clubs"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, options[which], Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            options=new String[]{"Now or soon first", "Most upvotes", "Most downvotes", "Alphabetically", "Include past events for today"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, options[which], Toast.LENGTH_SHORT).show();
                }
            });
        }
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
