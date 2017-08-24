package edu.udayton.moivedb.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.udayton.moivedb.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtAccount2, txtPassword2;
    private Button btnRegister2, btnReturnLogin;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);

        txtAccount2 = (EditText)findViewById(R.id.txt_account2);
        txtPassword2 = (EditText)findViewById(R.id.txt_password2);
        btnRegister2 = (Button)findViewById(R.id.btn_register2);
        btnReturnLogin = (Button)findViewById(R.id.btn_returnLogin);

        btnRegister2.setOnClickListener(this);
        btnReturnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register2:
                register();
                break;
            case R.id.btn_returnLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            default:
                break;
        }
    }

    private void register(){
        String email = txtAccount2.getText().toString();
        String pass = txtPassword2.getText().toString();

        if (email.isEmpty() && pass.isEmpty()){
            displayToast("Username or password field is empty");
        }
        else {
            db.addUser(email,pass);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
