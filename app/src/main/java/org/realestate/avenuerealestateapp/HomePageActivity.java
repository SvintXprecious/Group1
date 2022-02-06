package org.realestate.avenuerealestateapp;
/**
 * //Frontend and Backend  by Precious Tsetekani(BIT-029-18)
 * //Frontend and Backend  by Alinafe Mphepo(BIT-009-18)
 * //Frontend and Backend  by Simon Njewa(BIT-029-17)
 * //Frontend and Backend  by Gift Chimwendo(BIT-032-16)
 * //Frontend and Backend  by Khumbo Zimba(BIT-030-18)
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
RecyclerView recyclerView, recyclerViewVertical;
ImageButton back;
ImageView image;
private DatabaseReference ref;
ArrayList<Modelling> messagesArrayList;
private RecyclerAdapter recyclerAdapter;
private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        back = findViewById(R.id.backBtn);
        //Intent getNumber = getIntent();
        //String number= getNumber.getStringExtra("PhoneNumber");
        mytbnListerner();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerViewVertical = findViewById(R.id.recyclerViewVertical);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerViewVertical.setLayoutManager(layoutManager1);
        recyclerViewVertical.setHasFixedSize(true);


        ref = FirebaseDatabase.getInstance().getReference();

        messagesArrayList = new ArrayList<>();
        clearAll();
        getDataFromFirebase();
    }
private  void getDataFromFirebase(){
    Query query = ref.child("Image");
    query.addValueEventListener(new ValueEventListener() {

        @Override

        public void onDataChange(@NonNull DataSnapshot snapshot) {
            clearAll();
            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                for(DataSnapshot d : dataSnapshot.getChildren()) {
                    Modelling modelling = new Modelling();
                    modelling.setImageUrl(d.child("imageUrl").getValue().toString());
                  getFurtherInfo((String) d.child("targetProduct").getValue(),modelling);
                    messagesArrayList.add(modelling);

                }
            }

            recyclerAdapter = new RecyclerAdapter(getApplicationContext(),messagesArrayList);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerViewVertical.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}
private void clearAll(){
        if(messagesArrayList != null){
            messagesArrayList.clear();
            if (recyclerAdapter != null)
                recyclerAdapter.notifyDataSetChanged();
        }else
            messagesArrayList = new ArrayList<>();

}
 public void mytbnListerner(){
        Button b = findViewById(R.id.upload);
        b.setOnClickListener(view -> {
            Intent move = new Intent(HomePageActivity.this,Upload.class);
            //move.putExtra("PhoneNumber",number);
            startActivity(move);
            HomePageActivity.this.finish();
        });
    }

    public void getFurtherInfo(String id, Modelling modelling){
         DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                     for(DataSnapshot k : dataSnapshot.getChildren()) {
                        String productId = k.child("productID").getValue().toString();
                        if(id.equals(productId)){

                            modelling.setProductNAme(k.child("productName").getValue().toString());
                            modelling.setLocation(k.child("location").getValue().toString());
                            modelling.setPrice(k.child("price").getValue().toString());
                            modelling.setDescription(k.child("description").getValue().toString());
                        }else{
                            modelling.setProductNAme("Car");
                            modelling.setLocation("Lilongwe");
                            modelling.setPrice("$100");
                            modelling.setDescription("This is a nice Car in lilongwe");                        }
                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(HomePageActivity.this, ""+error, Toast.LENGTH_SHORT).show();
             }
         });
    }
}