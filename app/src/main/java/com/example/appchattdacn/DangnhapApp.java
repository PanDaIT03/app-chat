package com.example.appchattdacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class DangnhapApp extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button sendOtp;
    ProgressBar progressBar;
    String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap_app);

        countryCodePicker =findViewById(R.id.login_countrycode);
        phoneInput = findViewById(R.id.login_mobile_number);
        sendOtp = findViewById(R.id.send_otp_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.GONE);

        countryCodePicker.registerCarrierNumberEditText(phoneInput);
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCode = countryCodePicker.getSelectedCountryCodeWithPlus();
                Log.d("phone", countryCode);
            }
        });

        sendOtp.setOnClickListener((v) -> {
            if(!countryCodePicker.isValidFullNumber()){
                phoneInput.setError("Số điện thoại không đúng");
                return;
            }

            Intent intent = new Intent(DangnhapApp.this,otp.class);
            intent.putExtra("phone", countryCode + phoneInput.getText());
            Log.d("phone", countryCode + phoneInput.getText());
            startActivity(intent);
        });
    }
}