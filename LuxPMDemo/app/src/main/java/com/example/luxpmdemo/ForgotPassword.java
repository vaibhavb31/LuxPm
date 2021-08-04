package com.example.luxpmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.luxpmdemo.databinding.ActivityForgotPasswordBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword extends AppCompatActivity {
    ActivityForgotPasswordBinding activityForgotPasswordBinding;
    TextInputLayout emailcheck;
    TextInputEditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        View view = activityForgotPasswordBinding.getRoot();
        setContentView(view);

        emailcheck = activityForgotPasswordBinding.emailcheck;
        email = activityForgotPasswordBinding.email;
        email.addTextChangedListener(new ForgotPassword.ValidationTextWatcher(email));

        Button Submit = activityForgotPasswordBinding.submit;
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail())
                {
                    return;
                }
                String s= emailcheck.getEditText().getText().toString();

                if(s.equals("test@luxpmsoft.com"))
                {
                    Intent intent= new Intent(ForgotPassword.this,OtpConfirmation.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(activityForgotPasswordBinding.getRoot().getContext(), "Email not found", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
        private boolean validateEmail () {
            if (email.getText().toString().trim().isEmpty()) {
                emailcheck.setErrorEnabled(false);
            } else {
                String emailId = email.getText().toString();
                Boolean isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
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
        private void requestFocus (View view){
            if (view.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        private class ValidationTextWatcher implements TextWatcher {
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

                    case R.id.email:
                        validateEmail();
                        break;
                }
            }

        }

    }
