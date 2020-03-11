package com.example.layouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setup dropdown menu
        final Spinner optionSpinner = (Spinner) findViewById(R.id.optionSpinner);

        ArrayAdapter<String> optionAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.optionsArray));

        optionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionSpinner.setAdapter(optionAdapter);

        //setup button to do math
        Button mathBtn = (Button) findViewById(R.id.mathBtn);
        mathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide number keypad when not in use
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = getCurrentFocus();
                if( focusedView != null ){
                    imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
                }

                //get the 1st and 2nd number
                EditText numOneEditTxt = findViewById(R.id.numOneEditTxt);
                EditText numTwoEditTxt = findViewById(R.id.numTwoEditTxt);
                //convert them to strings
                String input1 = numOneEditTxt.getText().toString();
                String input2 = numTwoEditTxt.getText().toString();

                //where the result is displayed
                TextView resultTxtView = findViewById(R.id.resultTxtView);

                //check if either field is empty
                if(TextUtils.isEmpty(input1) || TextUtils.isEmpty(input2)){
                    resultTxtView.setText("enter 2 numbers before pressing button");
                }
                //else do the math
                else {
                    //get selected operator
                    String optionSelect = optionSpinner.getSelectedItem().toString();
                    //get the array of options for comparison
                    String[] optionsArray = getResources().getStringArray(R.array.optionsArray);

                    int num1 = Integer.parseInt(input1);
                    int num2 = Integer.parseInt(input2);
                    int result = 0;

                    if (optionSelect.equals(optionsArray[0])) {  //spinner is add
                        result = num1 + num2;
                    } else if (optionSelect.equals(optionsArray[1])) {  //spinner is subtract
                        result = num1 - num2;
                    } else if (optionSelect.equals(optionsArray[2])) {  //spinner is multiply
                        result = num1 * num2;
                    } else if (optionSelect.equals(optionsArray[3])) {  //spinner is divide
                        result = num1 / num2;
                    } else if (optionSelect.equals(optionsArray[4])) {  //spinner is modulo
                        result = num1 % num2;
                    }

                    //display result
                    resultTxtView.setText(result + "");
                }
            }
        });


    }
}
