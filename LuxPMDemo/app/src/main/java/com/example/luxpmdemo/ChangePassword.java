package com.example.luxpmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.luxpmdemo.databinding.ActivityChangePasswordBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding activityChangePasswordBinding;
    TextInputEditText newpass;
    TextInputLayout passcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangePasswordBinding= ActivityChangePasswordBinding.inflate(getLayoutInflater());
        View view = activityChangePasswordBinding.getRoot();
        setContentView(view);
        newpass= activityChangePasswordBinding.password;
        passcheck= activityChangePasswordBinding.passcheck;
        Button submit= activityChangePasswordBinding.submit;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newpass.getText().toString().isEmpty())
                {
                    passcheck.setErrorEnabled(true);
                    passcheck.setError("Password cannot be empty");
                }
                else
                {
                    passcheck.setErrorEnabled(false);
                    SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    // Creating an Editor object to edit(write to the file)
                    SharedPreferences.Editor myEdit = sh.edit();
                    String pass=newpass.getText().toString();

                            // Storing the key and its value as the data fetched from edittext
                            myEdit.putString("password",pass );
                            myEdit.commit();
                    Toast.makeText(ChangePassword.this,"Password Changed Sucessfully", Toast.LENGTH_LONG).show();
                    //finish();
                }
            }
        });


    }
}