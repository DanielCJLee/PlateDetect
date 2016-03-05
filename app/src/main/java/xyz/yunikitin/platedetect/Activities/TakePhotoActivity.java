package xyz.yunikitin.platedetect.Activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import xyz.yunikitin.platedetect.R;

public class TakePhotoActivity extends AppCompatActivity {

    static final int GALLERY_REQUEST = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        Button openButton = (Button)findViewById(R.id.open_button);
        imageView = (ImageView)findViewById(R.id.open_plate_imageView);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST) {
            Uri dataUri = data.getData();
            int w = imageView.getWidth();
            int h = imageView.getHeight();

            try {
                Bitmap bm = loadScaledBitmap(dataUri, w, h);
                imageView.setImageBitmap(bm);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    reference:
    Load scaled bitmap
    http://android-er.blogspot.com/2013/08/load-scaled-bitmap.html
     */
    private Bitmap loadScaledBitmap(Uri src, int req_w, int req_h) throws FileNotFoundException {

        Bitmap bm = null;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getBaseContext().getContentResolver().openInputStream(src),
                null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, req_w, req_h);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeStream(
                getBaseContext().getContentResolver().openInputStream(src), null, options);

        return bm;
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}
