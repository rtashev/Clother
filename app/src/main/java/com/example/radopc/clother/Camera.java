package com.example.radopc.clother;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class Camera extends ActionBarActivity implements DialogInterface.OnClickListener, View.OnClickListener {

    ImageView iv;
    Button takeOutfit , uploadPic , addTag ;
    final static int cameraData = 0;
    Bitmap bmp;
    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initialize();
    }

    private void initialize() {

        iv = (ImageView) findViewById(R.id.imageView_PhotoPreview);
        takeOutfit = (Button) findViewById(R.id.button_TakeOutfit_cameraActivity);
        uploadPic = (Button) findViewById(R.id.button_UploadOutfit_cameraActivity);
        addTag = (Button) findViewById(R.id.button_AddTag_cameraActivity);

        addTag.setOnClickListener(this);

        takeOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), cameraData);
            }
        });

        }

        @Override
        public void onClick(View v) {  // code from addTag Button
            final ArrayList chosenLabels = new ArrayList();
            final String[] possibleLabels = {"Sport", "Casual", "Work", "Free time", "Official events","Party","Elegant","Home",
                    "Beach","Special","Travel", "Hiking", "Running" ,"Other"};
            dialogBuilder = new AlertDialog.Builder(this);

            dialogBuilder.setTitle("Select Tag ");
            dialogBuilder.setMultiChoiceItems(possibleLabels, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    if(isChecked){
                        chosenLabels.add(which);
                    }
                    else if(chosenLabels.contains(which)){
                        chosenLabels.remove(Integer.valueOf(which));
                    }
                }
            });
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = dialogBuilder.create();
            dialog.show();

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                bmp = (Bitmap) extras.get("data");
                iv.setImageBitmap(bmp);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }


}
