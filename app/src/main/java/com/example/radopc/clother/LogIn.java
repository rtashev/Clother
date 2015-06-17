package com.example.radopc.clother;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LogIn extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
       // Resources res = getResources();
        //Drawable shape = res. getDrawable(R.drawable.btn_shape);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        Button registerBtn = (Button) findViewById(R.id.button_Register);
       // registerBtn.setBackground(shape);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                startActivity(new Intent(LogIn.this, Register.class));
            }
        });


        Button logInBtn = (Button) findViewById(R.id.button_LogIn);

        final EditText emailLogIn = (EditText) findViewById(R.id.editText_email);
        final EditText passwordLogIn = (EditText) findViewById(R.id.editText_Password);


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                v.startAnimation(animAlpha);

                SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
                String emailFromRegister = mPrefs.getString("emailFromRegister","" );
                String passwordFromRegister = mPrefs.getString("passwordFromRegister", "");

                String emailLogInStr = emailLogIn.getText().toString();
                String passwordLogInStr = passwordLogIn.getText().toString();
                if(emailLogInStr.equals(emailFromRegister) && passwordLogInStr.equals(passwordFromRegister)){

                    startActivity(new Intent(LogIn.this, Main.class));

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Email or Password not correct",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
