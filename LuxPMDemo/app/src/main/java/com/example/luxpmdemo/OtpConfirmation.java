package com.example.luxpmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.luxpmdemo.databinding.ActivityForgotPasswordBinding;
import com.example.luxpmdemo.databinding.ActivityOtpConfirmationBinding;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class OtpConfirmation extends AppCompatActivity {
    ActivityOtpConfirmationBinding activityOtpConfirmationBinding;
    private OtpTextView otpTextView;
    String otpfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOtpConfirmationBinding= ActivityOtpConfirmationBinding.inflate(getLayoutInflater());
        View view= activityOtpConfirmationBinding.getRoot();

        setContentView(view);
        Button submit= activityOtpConfirmationBinding.submit;

        otpTextView = findViewById(R.id.otp_view);
        otpfinal= otpTextView.getOTP();
//        otpTextView.setOtpListener(new OTPListener() {
//            @Override
//            public void onInteractionListener() {
//                // fired when user types something in the Otpbox
//            }
//            @Override
//            public void onOTPComplete(String otp) {
//                // fired when user has entered the OTP fully.
//                Toast.makeText(OtpConfirmation.this, "The OTP is " + otp,  Toast.LENGTH_SHORT).show();
//                otpfinal= otp;
//            }
//        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpfinal.equals("123456"))
                {
                    Intent intent= new Intent(OtpConfirmation.this,ChangePassword.class);
                    startActivity(intent);
                }
                else

                    {
                        Toast.makeText(OtpConfirmation.this, "Invalid OTP",  Toast.LENGTH_SHORT).show();


                    }
            }
        });

    }
}