package com.example.anmol.dillikebhukkad;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;

//import com.google.firebase.ui.auth.AuthUI;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Post> postArrayList;
    FloatingActionButton floatingActionButton;
    EditText etName, etTime, etTitle, etContent, etid;
    Button dp, image;
    String ImgUrl, DPUrl;
    Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        postArrayList = new ArrayList<>();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fabAdd);


        final LinearLayout l = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_layout, null);


        postArrayList.add(new Post("ImageUrl", "Content on how the place is where it is what dish you had how was the serviece and all", "Title", "Anmol Singh", "DPUrl", "40 mins ago", false, false, "" + 0));

        FirebaseAuth fbAuth = FirebaseAuth.getInstance();

        if (fbAuth.getCurrentUser() == null) {

            Intent signInIntent = AuthUI
                    .getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())).build();

            startActivityForResult(signInIntent, 111);
        }

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Upload")
                .setView(l)
                .setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etName = (EditText) l.findViewById(R.id.etName);
                        etTime = (EditText) l.findViewById(R.id.etTime);
                        etTitle = (EditText) l.findViewById(R.id.etTitle);
                        etContent = (EditText) l.findViewById(R.id.etContent);
                        etid = (EditText) l.findViewById(R.id.id);

                        dp = (Button) l.findViewById(R.id.btnDP);
                        image = (Button) l.findViewById(R.id.btnImage);
                        Log.e("MainActivity",String.valueOf(image));

                        /*name = etName.getText().toString();
                        time = etTime.getText().toString();
                        title = etTitle.getText().toString();
                        content = etContent.getText().toString();*/
                        /* databaseReference.child("post").child("username").setValue(name);
                        databaseReference.child("post").child("time").setValue(time);
                        databaseReference.child("post").child("title").setValue(title);
                        databaseReference.child("post").child("content").setValue(content);*/


                        Post currPost = new Post("ImageURl",
                                etContent.getText().toString(),
                                etTitle.getText().toString(),
                                etName.getText().toString(),
                                "dpurl",
                                etTime.getText().toString(),
                                false, false, etid.getText().toString());

                        FirebaseDatabase fb = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = fb.getReference();
                        FirebaseAuth a = FirebaseAuth.getInstance();
                        String id = a.getCurrentUser().getUid();
                        databaseReference.child("info").push();

                        /*databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                DataSnapshot snapshot = dataSnapshot.child("post");

                                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.child("post").getChildren();

                                for (DataSnapshot ds : snapshotIterator){
                                    Post currPost = ds.getValue(Post.class);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });*/


                        databaseReference.child("post").child(currPost.getId()).setValue(currPost)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getBaseContext(), "Uploaded The Post", Toast.LENGTH_SHORT).show();
                                    }
                                });


                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        StorageReference ImagesRef = storageRef.child("images");


                        dp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });

                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA)
                                        != PackageManager.PERMISSION_GRANTED) {

                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1887);
                                }

                                Intent intent = new Intent();

                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);

                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1887);


                            }
                        });


                    }
                }).create();


//        image.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA)
//                        != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1887);
//                }
//
//                Intent intent = new Intent();
//
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1887);
//
//
//            }
//        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CustomAdapter customAdapter = new CustomAdapter(postArrayList, this);
        recyclerView.setAdapter(customAdapter);


    }
}
