package org.realestate.avenuerealestateapp;
//Frontend and Backend by Khumbo Zimba()

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Finish extends AppCompatActivity {
    private ImageView v;
    private ProgressBar bar;
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Image");
    private final DatabaseReference ProductReference = FirebaseDatabase.getInstance().getReference("Products");

    private final StorageReference storage = FirebaseStorage.getInstance().getReference();
    private Uri imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_upload);

        ImageButton upload = findViewById(R.id.rectangle_4);

        v = findViewById(R.id.imageView);
        bar = findViewById(R.id.progerssBar);
        ImageButton backBtn = findViewById(R.id.backBtn);
        goback(backBtn);
        Intent getData = getIntent();
        String location = getData.getStringExtra("Area");
        String name = getData.getStringExtra("Dzina");
        String Description = getData.getStringExtra("Description");
        String prices = getData.getStringExtra("Price");
        String Number = getData.getStringExtra("Number");
        String phoneNumber = getData.getStringExtra("phoneNumber");


        Products product = new Products();
        product.setDescription(Description);
        product.setProductName(name);
        product.setLocation(location);
        product.setPrice(prices);
        product.setOwner(phoneNumber);
        product.setNumberOfRooms(Number);
        product.setStatus("NO");
        product.setProductID(ProductReference.push().getKey());



        String StudentRecordIDFromServer = ProductReference.push().getKey();
        assert StudentRecordIDFromServer != null;
        ProductReference.child(phoneNumber).child(product.getProductID()).setValue(product);

        ActivityResultLauncher<String> start = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    // Handle the returned Uri
                    if (uri != null){
                        imageUrl = uri;
                        v.setImageURI(imageUrl);
                    }else{
                        Toast.makeText(Finish.this,"No url set",Toast.LENGTH_SHORT).show();
                    }
                });
        v.setOnClickListener(view -> start.launch("image/*"));
        upload.setOnClickListener(view -> {
            if(imageUrl != null){

                uploadPhotos(imageUrl,product);
            }else
                Toast.makeText(Finish.this,"Please choose an Image",Toast.LENGTH_SHORT).show();
        });

    }

    public void goback(ImageButton back){
        back.setOnClickListener(view -> {
            // Intent move = new Intent(String.valueOf(getApplicationContext()));
            startActivity(new Intent(getApplicationContext(),Upload.class ));
            Finish.this.finish();
        });
    }
    private  void uploadPhotos(Uri uri, Products k){
        StorageReference files = storage.child(System.currentTimeMillis()+" "+getFileExtension(uri));
        files.putFile(uri).addOnSuccessListener(taskSnapshot -> files.getDownloadUrl().addOnSuccessListener(uri1 -> {
            Model model = new Model();
            model.setImageUrl(imageUrl.toString());
            model.setTargetProduct(k);
            String modeID = reference.push().getKey();
            reference.child(k.getOwner()).child(modeID).setValue(model);
            Toast.makeText(Finish.this,"Uploaded successfully",Toast.LENGTH_SHORT).show();
            bar.setVisibility(View.INVISIBLE);
            startActivity(new Intent(getApplicationContext(),HomePageActivity.class));

        })).addOnProgressListener(snapshot -> bar.setVisibility(View.VISIBLE)).addOnFailureListener(e -> {
            bar.setVisibility(View.INVISIBLE);
            Toast.makeText(Finish.this,"Error: "+e,Toast.LENGTH_SHORT).show();
        });
    }

private String getFileExtension(Uri uri){
    ContentResolver c =  getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(c.getType(uri));

}




}

class  Model{
    private String imageUrl;
    private String targetProduct;
    public  Model(){

    }

    public  Model(String imageUrl){
        this.imageUrl = imageUrl;

    }

    public void setTargetProduct(Products obj) {
        this.targetProduct = obj.getProductID();
    }

    public String getTargetProduct() {
        return targetProduct;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

class Products{

   private String productName;
    private String location;
    private String description;
    private String numberOfRooms;
    private String price;
    private String owner;
    private String status;
    private String ProductID;
    public Products(){

    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getProductName() {
        return productName;
    }

    public String getOwner() {
        return owner;
    }

    public String getProductID() {
        return ProductID;
    }
}