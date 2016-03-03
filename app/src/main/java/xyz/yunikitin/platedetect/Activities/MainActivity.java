package xyz.yunikitin.platedetect.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import xyz.yunikitin.platedetect.R;

public class MainActivity extends Activity implements View.OnClickListener {

    Button loadButton;
    Button photoButton;
    Button databaseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        }
    }
}
