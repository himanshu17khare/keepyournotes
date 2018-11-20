package himanshukhare.keepyournotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    private EditText userPassword;
    private TextView hello_text;
    private Button showNotes;
    private TextView forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_password);
        hello_text=findViewById(R.id.hello);
        userPassword=findViewById(R.id.userPassword);
        showNotes=findViewById(R.id.showNotes);
         forget=findViewById(R.id.forget);
        hello_text.setText(" Hello "+getSharedPreferences("NAME",MODE_PRIVATE).getString("name",null));
        showNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String user_checkPassword=userPassword.getText().toString();
                  if(user_checkPassword.contentEquals(getSharedPreferences("PASSWORD",MODE_PRIVATE).getString("password",null))){
                    startActivity(new Intent(PasswordActivity.this,NotesActivity.class));
                    finish();
                }else{
                    Toast.makeText(PasswordActivity.this, "Password is not correct", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void forgetpassword(View view) {
            forget.setVisibility(View.INVISIBLE);
            userPassword.getText().clear();
            hello_text.setVisibility(View.INVISIBLE);
            userPassword.setHint("Enter Security Code ");
            showNotes.setText("Change Password");
            showNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String code = userPassword.getText().toString();
                    if (code.equals(getSharedPreferences("SECURITY", MODE_PRIVATE).getString("security", null))){
                        startActivity(new Intent(PasswordActivity.this, NotesActivity.class));
                              finish();
                    }else{
                        Toast.makeText(PasswordActivity.this, "Security code is not correct", Toast.LENGTH_LONG).show();
                }}
            });
    }
    @Override
    public void onBackPressed() {
        finish(); // finish activity
    }
}
