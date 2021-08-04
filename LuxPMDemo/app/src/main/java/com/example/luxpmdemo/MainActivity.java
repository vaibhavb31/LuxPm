package com.example.luxpmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luxpmdemo.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    TextInputEditText email, password;
    TextInputLayout emailcheck, passwordcheck;
    //Button loginButton;
    TextView forgotPasssword;
    String password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        View view= activityMainBinding.getRoot();
        setContentView(view);

        email= activityMainBinding.email;
        password= activityMainBinding.password;


        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        if(!sharedPreferences.contains("password"))
        {
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // Storing the key and its value as the data fetched from edittext

            myEdit.putString("email","test@luxpmsoft.com" );
            myEdit.putString("password",password.getText().toString());

            // Once the changes have been made,
            // we need to commit to apply those changes made
            myEdit.commit();
        }


        // Fetching the stored data
        // from the SharedPreference


        String email1 = sharedPreferences.getString("email","test@luxpmsoft.com");
        String password1 = sharedPreferences.getString("password", "test1234!");

        if(password1.isEmpty())
        {
            password1="test1234!";
        }


        // Setting the fetched data
        // in the EditTexts
        email.setText(email1);
        password.setText(password1);


        forgotPasssword= activityMainBinding.forgot;


        Button submit = activityMainBinding.submit;
        passwordcheck = activityMainBinding.passwordcheck;
        emailcheck = activityMainBinding.emailcheck;
        password = activityMainBinding.password;
        email= activityMainBinding.email;
        password.addTextChangedListener( new ValidationTextWatcher(password));
        email.addTextChangedListener(new ValidationTextWatcher(email));

        //forgot password link
        forgotPasssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        // Capture button clicks
        String finalPassword = password1;
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!validatePassword()) {
                    return;
                }
                if (!validateEmail()) {
                    return;
                }
                String s= emailcheck.getEditText().getText().toString();
                String pass= passwordcheck.getEditText().getText().toString();
                if(s.equals(email1) && pass.equals(finalPassword))
                {
                    Intent intent= new Intent(MainActivity.this,LoginSucess.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(activityMainBinding.getRoot().getContext(), "id password donot match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            passwordcheck.setError("Password is required");
            requestFocus(password);
            return false;
        }
        else {
            passwordcheck.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateEmail() {
        if (email.getText().toString().trim().isEmpty()) {
            emailcheck.setErrorEnabled(false);
        } else {
            String emailId = email.getText().toString();
            Boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                emailcheck.setError("Invalid Email address");
                requestFocus(email);
                return false;
            } else {
                emailcheck.setErrorEnabled(false);
            }
        }
        return true;
    }
    class ValidationTextWatcher implements TextWatcher {
        private View view;
        private ValidationTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }



        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.password:
                    validatePassword();
                    break;
                case R.id.email:
                    validateEmail();
                    break;
            }
        }

    }

}