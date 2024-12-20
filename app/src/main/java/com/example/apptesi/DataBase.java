package com.example.apptesi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.graphics.Bitmap.Config.ARGB_8888;

public class DataBase {

    DatabaseReference dbRef;
    DatabaseReference userRef;
    ArrayList<Integer> personForArea = new ArrayList<Integer>();
    String areaUser;
    int contAree=0;

    Context context;
    DataBase(final Context context) {
        for(int i =0;i<10;i++){
            personForArea.add(0);
        }

        this.context = context;
        dbRef = FirebaseDatabase.getInstance().getReference();
        userRef = FirebaseDatabase.getInstance().getReference().child("user");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(GPS.android_id)) {
                    areaUser= dataSnapshot.child(GPS.android_id).child("area").getValue().toString();
                }
                for (int i = 0; i < GPS.unical.getListAree().size(); i++) {
                    contAree = 0;
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        if (postSnapshot.child("area").getValue().equals(GPS.unical.listAree.get(i).getName())) {
                            contAree++;
                        }
                    }

                    Log.d("Area", contAree + " " + GPS.unical.listAree.get(i).getName());
                    personForArea.add(i, contAree);

                }

                GPS.gmap.clear();
                GPS.unical.drawAreaUnical();

                for(int k=0; k<GPS.unical.getListAree().size();k++) {
                   GPS.unical.getListAree().get(k).setMarker(personForArea.get(k));
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

      /*  Query prova = FirebaseDatabase.getInstance().getReference().child("user");
        prova.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < aree.size(); i++) {
                    int contaAree = 0;
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        if (postSnapshot.child("area").getValue().equals(aree.get(i))) {
                            contaAree++;
                        }

                    }

                    Log.d("ISIN", contaAree + " " + aree.get(i));

                    personePerArea.set(i, contaAree);
                    contaAree = 0;


                }



                // marker.get(j).setTitle(personePerArea.get(j) + "persone nell'aerea"+aree.get(j));

                // marker.get(j).showInfoWindow();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
*/


        /*dbRef.child("user").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //UserLocation userHeat = (UserLocation) postSnapshot.getValue();

                    if (postSnapshot.child("area").getValue().toString() == areaUtente){
                        c++;
                        Log.d("ISIN", String.valueOf(c));

                    }
                }

               // Log.d("ISIN", String.valueOf(c));

                //Toast.makeText(context, "ci sono" + c + "persone/a nella tua zona", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/



/*
    public void  headMap(Context context ){
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<LatLng>temp = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //UserLocation userHeat = (UserLocation) postSnapshot.getValue();
                    double latitudP = Double.valueOf(postSnapshot.child("latitude").getValue().toString());
                    double longituP = Double.valueOf(postSnapshot.child("longitude").getValue().toString());
                    LatLng l = new LatLng(latitudP, longituP);
                    temp.add(l);


                }
                HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                        .data(temp).build();
                TileOverlay overlay = GPS.gmap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
                overlay.clearTileCache();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

    });

}*/
 /*   public void isExist(final String android_id){
        areaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //UserLocation userHeat = (UserLocation) postSnapshot.getValue();
                    if (postSnapshot.hasChild(android_id)) {
                       area = postSnapshot.getKey().toString();
                       break;


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            }
*/
/*
            public boolean existIntheDb (final String android_id){
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(android_id)){
                            isInTheDb = true;
                        } else {
                            isInTheDb = false;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                return isInTheDb;
            }

            public void setValue (String areaN,String android_id, UserLocation user){


               // areaRef.child(areaN).child(android_id).setValue(user);
            }

            public void removeValue (String areaN,String android_id){
                Log.d("ISIN","rimuovo"+areaN);
              //  areaRef.child(areaN).child(android_id).removeValue();

            }


            public void  getNumberOfPerson (final Context context) {

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        numPerson = dataSnapshot.getChildrenCount();

                        Log.d("db", String.valueOf(numPerson));
                        Toast.makeText(context, "ci sono" + numPerson + " persone all'unical!", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }*/


    public DatabaseReference getUserRef(){
        return userRef;
    }

        }