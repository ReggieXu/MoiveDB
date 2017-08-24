package edu.udayton.moivedb.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.udayton.moivedb.MainActivity;
import edu.udayton.moivedb.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnRegister;
    private EditText txtAccount, txtPassword;
    private DBHelper dbHelper;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        session = new Session(this);

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnRegister = (Button)findViewById(R.id.btn_register);
        txtAccount = (EditText)findViewById(R.id.txt_account);
        txtPassword = (EditText)findViewById(R.id.txt_password);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        if (session.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:


        }

    }

    private void login(){
        String email = txtAccount.getText().toString();
        String pass = txtPassword.getText().toString();

        if (dbHelper.getUser(email,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"Wrong password or account",Toast.LENGTH_SHORT).show();
        }

    }


}
