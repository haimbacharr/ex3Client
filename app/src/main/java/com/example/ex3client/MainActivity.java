package com.example.ex3client;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REGISTER_ID = 1;
    private EditText edCall;
    private EditText edSurf;
    private EditText edEmail;
    private TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCall = (EditText) findViewById(R.id.edTelephone);
        edSurf = (EditText) findViewById(R.id.edAddress);
        edEmail = (EditText) findViewById(R.id.edEmail);
        tv1 = (TextView) findViewById(R.id.textView);
    }

    public void callBtnClicked(View view) {

            String phoneNumberStr = edCall.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumberStr));
            startActivity(intent);
        }


    public void surfBtnClicked(View view) {

            String websiteAddressStr = edSurf.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteAddressStr));
            startActivity(intent);

    }

    public void emailBtnClicked(View view) {

        String emailStr = edEmail.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailStr)); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "subjerweect");
        intent.putExtra(Intent.EXTRA_TEXT, "gfdgfdgdfgdfgdfgdfgdf");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void registerBtnClicked(View view) {
        Intent intent = new Intent("com.action.register"); //implicit activity.
        startActivityForResult(intent, REGISTER_ID);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REGISTER_ID) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {

                    String firstName = data.getStringExtra("first_name");
                    String lastName = data.getStringExtra("last_name");
                    String genderType = data.getStringExtra("gender_from_rg");

                    if (genderType.equals("Male"))
                        genderType = "Mr.";
                    else genderType = "Ms.";

                    tv1.setText(genderType + " " + firstName + " " + lastName);
                }
            }
        }
    }
}