package com.dqt.thanhdu.newauthenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnDangky, btnDangNhap;
    EditText edtEmail, edtPassword, edtEmailDangNhap, edtPasswordDangNhap;
    FirebaseAuth mAuthen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuthen = FirebaseAuth.getInstance();
        Anhxa();
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email hoặc mật khẩu bị bỏ trống ", Toast.LENGTH_SHORT).show();
                }else
                DangKy();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmailDangNhap.getText().toString();
                String password = edtPasswordDangNhap.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email hoặc mật khẩu bị bỏ trống ", Toast.LENGTH_SHORT).show();
                }else
                DangNhap();
            }
        });

    }

    public void Anhxa() {
        btnDangky = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtEmailDangNhap = (EditText) findViewById(R.id.edtEmailDangNhap);
        edtPasswordDangNhap = (EditText) findViewById(R.id.edtPasswordDangNhap);
    }

    public void DangKy() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        mAuthen.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.d("TAG", "onComplete: Failed=" + task.getException().getMessage());
                            String errors=task.getException().getMessage();
                            switch (errors) {
                                case "The email address is already in use by another account.":
                                    Toast.makeText(MainActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The email address is badly formatted.":
                                    Toast.makeText(MainActivity.this, "Địa chỉ email không đúng định dạng", Toast.LENGTH_SHORT).show();
                                        break;
                                case "The given password is invalid.":
                                    Toast.makeText(MainActivity.this, "Mật khẩu có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                        // ...
                    }
                });
    }

    public void DangNhap() {
        String email = edtEmailDangNhap.getText().toString();
        String password = edtPasswordDangNhap.getText().toString();
        mAuthen.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,NextActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            //Toast.makeText(MainActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "onComplete: Failed=" + task.getException().getMessage());
                            String errors=task.getException().getMessage();
                            switch (errors) {
                                case "There is no user record corresponding to this identifier. The user may have been deleted.":
                                    Toast.makeText(MainActivity.this, "Địa chỉ email không chính xác", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The password is invalid or the user does not have a password.":
                                    Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                        // ...
                    }
                });
    }
}
