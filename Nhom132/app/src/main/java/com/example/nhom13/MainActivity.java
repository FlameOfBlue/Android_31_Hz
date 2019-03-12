package com.example.nhom13;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtRetype;
    private EditText edtBirthday;
    private Button btnSelect;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private CheckBox chbTennis;
    private CheckBox chbFootball;
    private CheckBox chbOthers;
    private Button btnReset;
    private Button btnSignUp;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtRetype = findViewById(R.id.edtRetype);
        edtBirthday = findViewById(R.id.edtBirthday);
        btnSelect = findViewById(R.id.btnSelect);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        chbTennis = findViewById(R.id.chbTennis);
        chbFootball = findViewById(R.id.chbFootball);
        chbOthers = findViewById(R.id.chbOthers);
        btnReset = findViewById(R.id.btnReset);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnExit = findViewById(R.id.btnExit);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUsername.setText("");
                edtPassword.setText("");
                edtRetype.setText("");
                edtBirthday.setText("");
                rbMale.setChecked(false);
                rbFemale.setChecked(false);
                chbTennis.setChecked(false);
                chbFootball.setChecked(false);
                chbOthers.setChecked(false);
                edtUsername.requestFocus();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidDate(edtBirthday.getText().toString())==false)
                    Toast.makeText(MainActivity.this, "Ngày tháng không hợp lệ, vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
                else
                if (edtPassword.getText().toString().equals(edtRetype.getText().toString()))
                {
                    String s = "Username: "+edtUsername.getText().toString()+"\n"
                            + "Password: "+edtPassword.getText().toString()+"\n"
                            + "Birthdate: "+edtBirthday.getText().toString()+"\n"
                            + "Gender: ";
                    if (rbMale.isChecked()) s+=rbMale.getText().toString()+"\n";
                    if (rbFemale.isChecked()) s+=rbFemale.getText().toString()+"\n";
                    s+="Hobbies: ";
                    if (chbTennis.isChecked()) s+=chbTennis.getText().toString()+" ";
                    if (chbFootball.isChecked()) s+=chbFootball.getText().toString()+" ";
                    if (chbOthers.isChecked()) s+=chbOthers.getText().toString()+" ";
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Mật khẩu không khớp, vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void ChonNgay()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtBirthday.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },1989,9,20);
        datePickerDialog.show();
    }

    private boolean isValidDate(String input)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setLenient(false);
            format.parse(input);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}

