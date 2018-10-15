package com.example.jonathan.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText bin, dec, hex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dec = findViewById(R.id.editText1);
        bin = findViewById(R.id.editText2);
        hex = findViewById(R.id.editText3);
        final Button calculate = findViewById(R.id.button);
        Button rst = findViewById(R.id.button2);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimalNum = dec.getText().toString();
                String binaryNum = bin.getText().toString();
                String hexNum = hex.getText().toString();
                if (decimalNum.length() > 0) {
                    if (checkDecimal(decimalNum)) {
                        String convertedBin = decimalToBinary(decimalNum);
                        String convertedHex = decimalToHex(decimalNum);
                        bin.setText(convertedBin);
                        hex.setText(convertedHex);
                        calculate.setEnabled(false);
                        ableAll(false);
                    }
                }
                else if (binaryNum.length() > 0) {
                    if (checkBinary(binaryNum)) {
                        String convertedDec = binaryToDecimal(binaryNum);
                        String convertedHex = decimalToHex(convertedDec);
                        dec.setText(convertedDec);
                        hex.setText(convertedHex);
                        calculate.setEnabled(false);
                        ableAll(false);
                    }
                }
                else if (hexNum.length() > 0) {
                    if (checkHex(hexNum)) {
                        String convertedDec = hexToDecimal(hexNum);
                        String convertedBin = decimalToBinary(convertedDec);
                        dec.setText(convertedDec);
                        bin.setText(convertedBin);
                        calculate.setEnabled(false);
                        ableAll(false);
                    }
                }

                else {
                    checkAll(decimalNum, binaryNum, hexNum);
                }
            }
        });
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ableAll(true);
                dec.setText("");
                bin.setText("");
                hex.setText("");
            }
        });
    }

    public void checkAll(String s1, String s2, String s3) {
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            Toast toast = Toast.makeText(MainActivity.this, "Input a Decimal, Binary, or Hex Number.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean checkDecimal(String str) {
        if (str.isEmpty())  {
            Toast toast = Toast.makeText(MainActivity.this, "Empty Decimal Number. Try Again.", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }

    public boolean checkBinary(String str) {
        if (!(str.contains("0") || str.contains("1"))) {
            Toast toast = Toast.makeText(MainActivity.this, "Empty or Invalid Binary Number. Try Again.", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }

    public boolean checkHex(String str) {
        if (!str.matches("^[0-9A-F]+$")) {
            Toast toast = Toast.makeText(MainActivity.this, "Empty or Invalid Hexadecimal Number. Try Again.", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }

    public String decimalToBinary (String input) {
        Integer num = Integer.parseInt(input);
        return Integer.toBinaryString(num);
    }

    public String decimalToHex (String input) {
        Integer num = Integer.parseInt(input);
        return Integer.toHexString(num).toUpperCase();
    }

    public String binaryToDecimal (String input) {
        String result = "";
        result += Integer.parseInt(input, 2);
        return result;
    }

    public String hexToDecimal (String input) {
        String result = "";
        result += Integer.parseInt(input, 16);
        return result;
    }

    public void ableAll (boolean b) {
        dec.setEnabled(b);
        bin.setEnabled(b);
        hex.setEnabled(b);
    }
}
