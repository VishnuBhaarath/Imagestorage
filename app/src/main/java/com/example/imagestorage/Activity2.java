package com.example.imagestorage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class Activity2 extends AppCompatActivity {
    private StorageReference storageReference,ref;
    private FirebaseStorage firebaseStorage;
    private ImageView imageView;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        imageView=(ImageView)findViewById(R.id.displayimage);
        tv1=(TextView)findViewById(R.id.text);
        download();
}

  public void download(){
        storageReference=firebaseStorage.getInstance().getReference("uploads");
        ref=storageReference.child("images.jpg");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("as",""+uri);
                Picasso.get().load(uri).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                 Toast.makeText(Activity2.this,"Failure to display",Toast.LENGTH_SHORT).show();
            }
        });
  }
}
