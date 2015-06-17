package com.example.radopc.clother;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class Gallery extends ActionBarActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    private AlertDialog.Builder dialogBuilderGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Button btnOpenGallery = (Button) findViewById(R.id.button_OpenGallery_galleryActivity);
        btnOpenGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        Button addTagGallery = (Button) findViewById(R.id.button_AddTag_galleryActivity);

        addTagGallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) { // code for addTagGallery
        final ArrayList chosenLabels = new ArrayList();
        final String[] possibleLabels = {"Sport", "Casual", "Work", "Free time", "Official events","Party","Elegant","Home",
                "Beach","Special","Travel", "Hiking", "Running" ,"Other"};
        dialogBuilderGallery = new AlertDialog.Builder(this);

        dialogBuilderGallery.setTitle("Select Tag ");
        dialogBuilderGallery.setMultiChoiceItems(possibleLabels, null, new DialogInterface.OnMultiChoiceClickListener() {
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
        dialogBuilderGallery.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogBuilderGallery.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = dialogBuilderGallery.create();
        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  // code for btnOpenGallery
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageViewGallery = (ImageView) findViewById(R.id.imageView_PhotoPreview_Gallery);
            imageViewGallery.setImageBitmap(BitmapFactory.decodeFile(picturePath));

             }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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
