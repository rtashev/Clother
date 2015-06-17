package com.example.radopc.clother;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.radopc.clother.R.id.editText_UserDataMain;


public class Main extends ActionBarActivity {

    private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {

        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        final EditText enterEmail = (EditText) findViewById(R.id.editText_UserDataMain);
        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        String emailFromRegister = mPrefs.getString("emailFromRegister","" );
        enterEmail.setText(emailFromRegister);

        Button logOutBtnFromMain = (Button) findViewById(R.id.button_LogOut);

        logOutBtnFromMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                startActivity(new Intent(Main.this, LogIn.class));
            }
        });

        Button takeOutfitPictureBtn = (Button) findViewById(R.id.button_TakeOutfitPicture);

        takeOutfitPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                startActivity(new Intent(Main.this, Camera.class));
            }
        });

        Button uploadOutfitBtn = (Button) findViewById(R.id.button_UploadOutfit);

        uploadOutfitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Gallery.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

        //You may also add condition if (doubleBackToExitPressedOnce || fragmentManager.getBackStackEntryCount() != 0) // in case of Fragment-based add
        if (mRecentlyBackPressed) {
            mExitHandler.removeCallbacks(mExitRunnable);
            mExitHandler = null;
            super.onBackPressed();
        }
        else
        {
            mRecentlyBackPressed = true;
            Toast.makeText(this, "press again to Log out", Toast.LENGTH_SHORT).show();
            mExitHandler.postDelayed(mExitRunnable, delay);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
