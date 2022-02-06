package org.realestate.avenuerealestateapp;
//Component by Khumbo Zimba(BIT-030-18)

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Upload extends AppCompatActivity {
    EditText name,price,location,numerOfRooms,description;
    ImageButton b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2);
        goback(findViewById(R.id.backBtn));
        location = findViewById(R.id.rectangle_2);
        name = findViewById(R.id.rectangle_1);
        price = findViewById(R.id.rectangle_3);
        numerOfRooms = findViewById(R.id.rectangle_6);
        description = findViewById(R.id.rectangle_8);
        b = findViewById(R.id.rectangle_4);
        b.setOnClickListener(view -> {



        String area  = location.getText().toString();
        String dzina = name.getText().toString();
        String desc = description.getText().toString();
        String mtengo = price.getText().toString();
        String num =  numerOfRooms.getText().toString();

    if(ValidateDate(area)){
        location.setError("Location is required");
        return;
    }
    if(ValidateDate(dzina)){
        location.setError("Name is required");
        return;
    }if(ValidateDate(desc)){
        location.setError("Description is required");
                return;
    }if(ValidateDate(mtengo)){
        location.setError("Price is required");
                return;
    }if(ValidateDate(num)){
        location.setError("Fill in this field");
                return;
    }
    FinishUpload(area,dzina,desc,mtengo,num);
        });
    }

public boolean ValidateDate(String element){

    return element.isEmpty();
}


    public void goback(ImageButton back){
        back.setOnClickListener(view -> {
            // Intent move = new Intent(String.valueOf(getApplicationContext()));
            startActivity(new Intent(getApplicationContext(),HomePageActivity.class ));
            Upload.this.finish();
        });
    }
    public void FinishUpload(String area, String dzina, String desc,String mtengo,String num){
        ImageButton b = findViewById(R.id.rectangle_4);
        b.setOnClickListener(view -> {
            //Toast.makeText(Upload.this,"wawa"+dzina+" "+desc,Toast.LENGTH_LONG).show();
            Intent getNumber = getIntent();
             //getNumber.getStringExtra("phoneNumber");
            Intent another =   new Intent(Upload.this,Finish.class);
            another.putExtra("Area",area);
            another.putExtra("Dzina",dzina);
            another.putExtra("Description",desc);
            another.putExtra("Price",mtengo);
            another.putExtra("Number",num);
            another.putExtra("phoneNumber","+265994037380");
            startActivity(another);

           // Upload.this.finish();
        });
    }
}