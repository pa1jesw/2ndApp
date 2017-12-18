package com.pa1.a2ndproj;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv1;
    Button btnTk;
    Bitmap photo;
    FloatingActionButton fabm;


    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you really want to Exit.?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }
        );
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        builder.setTitle("Exit Confirm");
        builder.show();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.ivMain);
        btnTk = (Button) findViewById(R.id.btnTk);
        fabm = (FloatingActionButton) findViewById(R.id.fabM);


        fabm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(findViewById(android.R.id.content),"Action Required For FAB",Snackbar.LENGTH_LONG).show();
            }
        });

        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ci = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ci,100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==100){
                photo = (Bitmap) data.getExtras().get("data");
                iv1.setImageBitmap(photo);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.save :
                String state= Environment.getExternalStorageState();
                if(Environment.MEDIA_MOUNTED.equalsIgnoreCase(state)){

                    File root = Environment.getExternalStorageDirectory();
                    File dir = new File(root + "/2ndApp");

                    if(!dir.exists())
                        dir.mkdir();

                    Random r = new Random(12);
                    int rn = r.nextInt()*12526 / 23;
                    String fname = rn + ".jpg";


                    File file = new File(dir,fname);
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedOutputStream br = new BufferedOutputStream(fos);
                        photo.compress(Bitmap.CompressFormat.PNG,90,br);
                        Snackbar.make(findViewById(android.R.id.content),"Saved",Snackbar.LENGTH_LONG).show();
                        br.flush();
                        br.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"file not found",Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"IO Exception",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"External Stroge issue",Toast.LENGTH_LONG).show();
                }

                break;



        }
        return super.onOptionsItemSelected(item);
    }









}
