package com.example.bmicalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
public class MainActivity extends AppCompatActivity {
    android.widget.Button mcalculatebmi;
    TextView mcurrentheight;
    TextView mcurrentage, mcurrentweight;
    ImageView mincrementage, mincrementweight, mdecrementweight, mdecrementage;
    SeekBar mseekbarforheight;
    RelativeLayout mmale, mfemale;
    ImageButton mhistory;
    int intweight = 55;
    int intage = 22;
    int currentprogress;
    String mintprogress = "170";
    String typeofuser = "0";
    String weight2 = "55";
    String age2 = "22";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        // Initializing the variable for image button
        mhistory = (ImageButton) findViewById(R.id.history);
        mcalculatebmi = findViewById(R.id.calculatebmi);
        mcurrentage = findViewById(R.id.currentage);
        mcurrentweight = findViewById(R.id.currentweight);
        mcurrentheight = findViewById(R.id.currentheight);
        mincrementage = findViewById(R.id.incrementage);
        mdecrementage = findViewById(R.id.decrementage);
        mincrementweight = findViewById(R.id.incrementweight);
        mdecrementweight = findViewById(R.id.decrementweight);
        mseekbarforheight = findViewById(R.id.seekbarforheight);
        mmale = findViewById(R.id.male);
        mfemale = findViewById(R.id.female);
        mhistory.setOnClickListener(view -> {
            // Creating a toast to display the message
            Toast.makeText(MainActivity.this, "About BMI",
                    Toast.LENGTH_SHORT).show();
            String url = "https://avicennaint.com/wp-content/uploads/2022/01/bmi-chart-imp.webp";
            // Creating an explicit intent to open the
            // link stored in variable url
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.malefemalenotfocus));
                typeofuser = "Male";
            }
        });
        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.malefemalenotfocus));
                typeofuser = "Female";
            }
        });
        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new
                                                             SeekBar.OnSeekBarChangeListener() {
                                                                 @Override
                                                                 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                                     currentprogress = progress;
                                                                     mintprogress = String.valueOf(currentprogress);
                                                                     mcurrentheight.setText(mintprogress);
                                                                 }
                                                                 @Override
                                                                 public void onStartTrackingTouch(SeekBar seekBar) {
                                                                 }
                                                                 @Override
                                                                 public void onStopTrackingTouch(SeekBar seekBar) {
                                                                 }
                                                             });
        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage = intage+1;
                age2 =String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });
        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight = intweight+1;
                weight2 =String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });
        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight = intweight-1;
                weight2 =String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });
        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage = intage-1;
                age2 =String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });
        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (typeofuser.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Select Your Gender First",
                            Toast.LENGTH_SHORT).show();
                }
                else if (mintprogress.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Select Your Height First",
                            Toast.LENGTH_SHORT).show();
                }
                else if (intage==0 || intage<0) {
                    Toast.makeText(getApplicationContext(), "Age is Incorrect",
                            Toast.LENGTH_SHORT).show();
                }
                else if (intweight==0 || intweight<0) {
                    Toast.makeText(getApplicationContext(), "Weight is Incorrect",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, bmiactivity.class);
                    intent.putExtra("gender",typeofuser);
                    intent.putExtra("height", mintprogress);
                    intent.putExtra("weight", weight2);
                    intent.putExtra("age", age2);
                    startActivity(intent);
                }
            }
        });
    }
}
