package org.realestate.avenuerealestateapp;
//Component by Alinafe Mphepo(BIT-009-18)

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity
{
    private TextView conti;
    private TextView txt_email;
    private FirebaseAuth mAuth;
    private ImageButton backBtn;
// Initialize Firebase Auth




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                ForgetPassword.this.finish();
            }
        });
        conti=(TextView) findViewById(R.id.continue_b);
        txt_email=(TextView) findViewById(R.id.email_reset);

        mAuth = FirebaseAuth.getInstance();

        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPassword();

            }
        });

    }

    private void ResetPassword() {
        String Email = txt_email.getText().toString().trim();

        if(Email.isEmpty()){
            txt_email.setError("Email is required");
            txt_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            txt_email.setError("Please enter a valid email");
            txt_email.requestFocus();
            return;

        }

        mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this, "Check your email to reset password!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgetPassword.this, "Try again, something wrong happened!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


}


