package xyz.yunikitin.platedetect.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import xyz.yunikitin.platedetect.R;


public class MainActivity extends Activity implements View.OnClickListener {

    Button loadButton;
    Button photoButton;
    Button databaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(this);

        photoButton = (Button) findViewById(R.id.photo_button);
        photoButton.setOnClickListener(this);

        databaseButton = (Button) findViewById(R.id.database_button);
        databaseButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_button:
                Intent intent = new Intent(this, TakePhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.photo_button:
                Intent intent1 = new Intent(this, PhotoActivity.class);
                startActivity(intent1);
                break;
            case R.id.database_button:
                Intent intent2 = new Intent(this, DatabaseActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
