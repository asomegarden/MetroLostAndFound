package com.example.metrolostandfound;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.InputStream;

public class EnrollActivity_Other extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;
    Bitmap choice_img;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_others);
        imageView = findViewById(R.id.image);

        Button btn_Enroll = (Button) findViewById(R.id.btn_Enroll);
        Button btn_UploadPicture = (Button) findViewById(R.id.btn_UploadPicture);
        EditText editStorage, editMemo;


        editStorage = (EditText) findViewById(R.id.editStorage);
        editMemo = (EditText) findViewById(R.id.editMemo);
        Intent intent2 = new Intent();
        btn_UploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent2,0);

                 */
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        //db에 저장
        btn_Enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 5/5\n 등록완료", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                Intent intent2 = new Intent(EnrollActivity_Other.this, MainActivity.class);
                LostObject newObj = new LostObject();
                newObj.setMainCategory(intent.getStringExtra("mc"));
                newObj.setSubCategory(intent.getStringExtra("sc"));
                newObj.setLine(intent.getStringExtra("line"));
                newObj.setStation(intent.getStringExtra("station"));
                newObj.setDateTime(intent.getStringExtra("date"));
                newObj.setStorage(editStorage.getText().toString());
                newObj.setContents(editMemo.getText().toString());
                newObj.setImage(choice_img);
                DBController.postItem(newObj);
                startActivity(intent2);

                //이렇게 계속 추가해서 데이터 다 입력하면
                //DBController.postItem(obj);
                //하면 됨
                //static으로 만들어서 인스턴스 생성 안 해도 됨
            }
        });
    }
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if( requestCode == 0){
            if(resultCode == RESULT_OK){
                Glide.with(getApplicationContext()).load(data.getData()).override(150,150).into((android.widget.ImageView) ImageView);
            }
        }
    }

     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());

                choice_img = BitmapFactory.decodeStream(in);
                imageView.setImageBitmap(choice_img);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        onStop();
    }
}
