package himanshukhare.keepyournotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {
private EditText name;
private EditText password;
private EditText confirmPassword;
private EditText securityPassword;
private String user_name;
private String user_password;
private String user_confirmpassword;
private String user_security;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);
        SharedPreferences sha=getSharedPreferences("PREFS",MODE_PRIVATE);
        if(sha.getInt("INTRO",0)==1){
        startActivity(new Intent(IntroActivity.this,PasswordActivity.class));
        finish();}
        Button create=findViewById(R.id.normalButton);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.repassword);
        securityPassword=findViewById(R.id.security);
        create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(IntroActivity.this,NotesActivity.class));
            user_name=name.getText().toString();
            user_password=password.getText().toString();
            user_confirmpassword=confirmPassword.getText().toString();
            user_security=securityPassword.getText().toString();
            if(!user_name.isEmpty()&&!user_password.isEmpty()&&!user_confirmpassword.isEmpty()&&!user_security.isEmpty()) {
                if(user_password.equals(user_confirmpassword)){
                    if(user_name.length()>5&&user_password.length()>5&&user_security.length()>5){
                Toast.makeText(IntroActivity.this, "Congrats", Toast.LENGTH_SHORT).show();
                SharedPreferences  sha= getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor= sha.edit();
                editor.putInt("INTRO",1);
                editor.apply();
                getSharedPreferences("NAME",MODE_PRIVATE).edit().putString("name",user_name).apply();
                getSharedPreferences("PASSWORD",MODE_PRIVATE).edit().putString("password",user_password).apply();
                getSharedPreferences("SECURITY",MODE_PRIVATE).edit().putString("security",user_security).apply();
                startActivity(new Intent(IntroActivity.this, NotesActivity.class));
                finish();}else {
                        if(user_name.length()<5){
                            Toast.makeText(IntroActivity.this, "Name is too short", Toast.LENGTH_LONG).show();
                        }else if(user_password.length()<5)
                            Toast.makeText(IntroActivity.this, "Password is too weak", Toast.LENGTH_LONG).show();
                        else if(user_security.length()<5)
                            Toast.makeText(IntroActivity.this, "Security code is weak", Toast.LENGTH_LONG).show();
                    }}
                else{
                    Toast.makeText(IntroActivity.this, "Password not match", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(IntroActivity.this, "Please fill the required fields", Toast.LENGTH_LONG).show();
            }
        }
    });
  }
}