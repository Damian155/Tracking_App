package com.dqt.thanhdu.newauthenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    Button btnDangKy;
    EditText edtEmailDangKy, edtPasswordDangKy, edtRetypePass;
    FirebaseAuth mAuthen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuthen = FirebaseAuth.getInstance();
        Anhxa();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmailDangKy.getText().toString();
                String password = edtPasswordDangKy.getText().toString();
                String retype=edtRetypePass.getText().toString();
                if (email.isEmpty() || password.isEmpty()||retype.isEmpty()) {
                    Toast.makeText(SignUp.this, "Email hoặc mật khẩu bị bỏ trống", Toast.LENGTH_SHORT).show();

                }else if(!password.equals(retype))
                {
                    Toast.makeText(SignUp.this, "Xác nhận mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
                }else
                    DangKy();
            }
        });
    }

    public void Anhxa() {
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        edtEmailDangKy = (EditText) findViewById(R.id.edtEmailDangKy);
        edtPasswordDangKy = (EditText) findViewById(R.id.edtPasswordDangKy);
        edtRetypePass = (EditText) findViewById(R.id.edtRetypePass);
    }
    public void DangKy() {
        String email = edtEmailDangKy.getText().toString();
        String password = edtPasswordDangKy.getText().toString();
        mAuthen.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUp.this, "Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.d("TAG", "onComplete: Failed=" + task.getException().getMessage());
                            String errors=task.getException().getMessage();
                            switch (errors) {
                                case "The email address is already in use by another account.":
                                    Toast.makeText(SignUp.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The email address is badly formatted.":
                                    Toast.makeText(SignUp.this, "Địa chỉ email không đúng định dạng", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The given password is invalid.":
                                    Toast.makeText(SignUp.this, "Mật khẩu có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                        // ...
                    }
                });
    }
}
