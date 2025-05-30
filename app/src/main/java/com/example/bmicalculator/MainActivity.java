package com.example.bmicalculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ageInput = findViewById(R.id.ageInput);
        EditText weightInput = findViewById(R.id.weightInput);
        EditText heightInput = findViewById(R.id.heightInput);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultView = findViewById(R.id.result);
        ImageView categoryImage = findViewById(R.id.categoryImage);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageStr = ageInput.getText().toString();
                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();

                if (TextUtils.isEmpty(ageStr) || TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(heightStr)) {
                    Toast.makeText(MainActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int age = Integer.parseInt(ageStr);
                    float weight = Float.parseFloat(weightStr);
                    float height = Float.parseFloat(heightStr);

                    if (age <= 0 || height <= 0 || weight <= 0) {
                        Toast.makeText(MainActivity.this, "Enter valid age, weight, and height values!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calculate BMI
                    float bmi = weight / (height * height);

                    // Determine BMI category
                    String category;
                    int imageResId;
                    if (bmi < 18.5) {
                        category = "Underweight";
                        imageResId = R.drawable.bmi_underweight;
                    } else if (bmi < 24.9) {
                        category = "Normal weight";
                        imageResId = R.drawable.bmi_normal;
                    } else if (bmi < 29.9) {
                        category = "Overweight";
                        imageResId = R.drawable.bmi_overweight;
                    } else {
                        category = "Obese";
                        imageResId = R.drawable.bmi_obese;
                    }

                    // Display result
                    String resultText = "Age: " + age + "\nYour BMI: " + String.format("%.2f", bmi) + "\nCategory: " + category;
                    resultView.setText(resultText);

                    // Update ImageView
                    categoryImage.setImageResource(imageResId);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input format!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
