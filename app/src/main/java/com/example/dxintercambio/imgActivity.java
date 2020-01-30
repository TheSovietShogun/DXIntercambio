package com.example.dxintercambio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.carrier.CarrierMessagingService;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;






public class imgActivity extends AppCompatActivity {

    private ImageView tractor , noEconomico, izqRemolqueP1 , vin , chasisFrontalIzq , chasisTraseroIzq , llantasIzqEje1
            ,llantasIzqEje2 , izqRemolqueP2 , puertas , placas , sello1 , sello2 , derRemolqueP1 , llantasDerEje2
            , llantasDerEje1 , chasisTraseroDer , chasisFrontalDER , derRemolqueP2;
    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final String TAG = "envioActivity";
    Drive mService;
    Drive googleDriveService;


    private File imageFile ;
    Uri photoURI;


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);


        tractor = (ImageView) findViewById(R.id.imageView3);

        tractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoURI = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".provider", createImageFile());
                camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(camera,0);
            }

            private File createImageFile() {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());

                try {
                    imageFile = File.createTempFile(
                            imageFileName,  // prefix
                            ".jpeg",         // suffix
                            destPath      // directory
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return imageFile;
            }
        });

        requestSignIn();

    }



    private void requestSignIn() {

        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                        .build();
        GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);

        // The result of the sign-in Intent is handled in onActivityResult.
        startActivityForResult(client.getSignInIntent(), REQUEST_CODE_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    handleSignInResult(resultData);
                }
                break;
            case 0:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    Picasso.get().load(photoURI).into(tractor);

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            // Create URI from real path
                            String path = imageFile.getPath();
                            String nueva = path.replace("/storage/emulated/0/Android/data/com.example.dxintercambio/files/","");
                            //Uri mFileUri = Uri.fromFile(new File(path));
                            //java.io.File fileContent = new java.io.File(mFileUri.getPath());


                            com.google.api.services.drive.model.File metadata = new com.google.api.services.drive.model.File();
                            metadata.setName("prueba.jpeg");

                            java.io.File filePath = new java.io.File(path);
                            FileContent mediaContent = new FileContent("image/jpeg", filePath);


                            try {
                                com.google.api.services.drive.model.File file = googleDriveService.files().create(metadata, mediaContent)
                                        .setFields("id")
                                        .execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();


                    photoURI = null ;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, resultData);
    }

    private void handleSignInResult(Intent result) {
        GoogleSignIn.getSignedInAccountFromIntent(result)
                .addOnSuccessListener(googleAccount -> {
                    Log.d(TAG, "Signed in as " + googleAccount.getEmail());

                    // Use the authenticated account to sign in to the Drive service.
                    GoogleAccountCredential credential =
                            GoogleAccountCredential.usingOAuth2(
                                    this, Collections.singleton(DriveScopes.DRIVE_FILE));
                    credential.setSelectedAccount(googleAccount.getAccount());
                    googleDriveService =
                            new Drive.Builder(
                                    AndroidHttp.newCompatibleTransport(),
                                    new GsonFactory(),
                                    credential)
                                    .setApplicationName("Drive API Migration")
                                    .build();

                    // The DriveServiceHelper encapsulates all REST API and SAF functionality.
                    // Its instantiation is required before handling any onClick actions.
                    //mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
                })
                .addOnFailureListener(exception -> Log.e(TAG, "Unable to sign in.", exception));

    }
}
