package com.example.radopc.clother;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
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


public class Register extends ActionBarActivity {


    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        Resources res = getResources();
        Drawable shape = res. getDrawable(R.drawable.btn_shape);

        Button registerBtn = (Button) findViewById(R.id.button_Confirm);

        final EditText enterEmail = (EditText) findViewById(R.id.editText_enterEmail);
        final EditText enterPassword = (EditText) findViewById(R.id.editText_EnterPassword);
        final EditText confirmPassword = (EditText) findViewById(R.id.editText_ConfirmPassword);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(animAlpha);

                String email = enterEmail.getText().toString();
                String password = enterPassword.getText().toString();
                String passwordCheck = confirmPassword.getText().toString();

                SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
//Give any name for //preference as I have given "IDvalue" and value 0.
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("emailFromRegister", email);
                editor.putString("passwordFromRegister", password);
// give key value as "sound" you mentioned and value what you // to want give as "1" in you mentioned
                editor.commit();

                if ((email.trim().length() > 0) && (password.trim().length() > 0) && (passwordCheck.trim().length() > 0))
                {
                    if(email.contains("@"))
                    {
                        if(password.equals(passwordCheck))
                        {
                            Intent intent = new Intent(Register.this, LogIn.class);
                            intent.putExtra(EMAIL,email);
                            intent.putExtra(PASSWORD, password);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Passwords don't match",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "Email is not valid.",
                                Toast.LENGTH_LONG).show();
                        }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Please fill all fields",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
