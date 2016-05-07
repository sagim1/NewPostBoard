package com.myproject.postboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView listView1;
    private Firebase firebase;
    private List<Event> eventList;
    private List<String> eventImageList;
    private String eventName;
    private String eventDay;
    private String eventTime;
    private String eventPlace;
    private String eventBy;
    private String eventImage;
    private String eventId;
    private String going;
    private String notGoing;
    private Intent intent;
    private Query query;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        //Intent intent=new Intent(MainActivity.this, AddEventActivity.class);
        //startActivity(intent);
        init();
    }

    public void init() {
        listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setOnItemClickListener(this);
        eventImageList = new ArrayList<>();
        getAllEvents();// to show the main page with all of our events as soon as the app is opened

    }

    public void getAllEvents(){

        firebase = new Firebase("https://postboard.firebaseio.com");
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                eventList=new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    {
                        //Toast.makeText(MainActivity.this, dataSnapshot1.getRef().getKey().toString(), Toast.LENGTH_SHORT).show();
                        eventId = dataSnapshot1.getRef().getKey().toString();
                        eventName = dataSnapshot1.child("eventName").getValue(String.class);
                        eventDay = dataSnapshot1.child("eventDay").getValue(String.class);
                        eventTime = dataSnapshot1.child("eventTime").getValue(String.class);
                        eventPlace = dataSnapshot1.child("eventPlace").getValue(String.class);
                        eventBy = dataSnapshot1.child("eventBy").getValue(String.class);
                        eventImage = dataSnapshot1.child("eventImage").getValue(String.class);
                        //eventId = dataSnapshot1.child("eventId").getValue(String.class);
                        going = dataSnapshot1.child("going").getValue(String.class);
                        notGoing = dataSnapshot1.child("notGoing").getValue(String.class);
                        eventImageList.add(eventImage);
                        eventList.add(new Event(eventName, eventDay, eventTime, eventPlace, eventBy, eventImage, eventId, going, notGoing));
                    }
                }
                populateListView();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void populateListView(){
        for(Event event:eventList)
        {
            adapter=new MyAdapter(MainActivity.this, R.id.imageView1, eventList);
            listView1.setAdapter(adapter);
        }

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
