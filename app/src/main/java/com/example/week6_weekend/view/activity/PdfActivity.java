package com.example.week6_weekend.view.activity;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import com.example.week6_weekend.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfActivity extends AppCompatActivity {
    private static final String FILE_NAME = "millenial_apartment_hunters_cache.pdf";
    private PdfRenderer mPdfRenderer;
    private PdfRenderer.Page mPdfPage;
    private ImageView ivpdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        ivpdf = findViewById(R.id.ivpdf);
        try{
            openPdfWithAndroidSDK(ivpdf, 0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPdfPage != null) {
            mPdfPage.close();
        }
        if (mPdfRenderer != null) {
            mPdfRenderer.close();
        }
    }

    void copyToLocalCache(File outputFile, @RawRes int pdfResource) throws IOException {
        if (!outputFile.exists()) {
            InputStream input = getResources().openRawResource(pdfResource);
            FileOutputStream output;
            output = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int size;
            // Just copy the entire contents of the file
            while ((size = input.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            input.close();
            output.close();
        }
    }

    void openPdfWithAndroidSDK(ImageView imageView, int pageNumber) throws IOException {
        // Copy sample.pdf from 'res/raw' folder into local cache so PdfRenderer can handle it
        File fileCopy = new File(getCacheDir(), FILE_NAME);
        copyToLocalCache(fileCopy, R.raw.millenial_apartment_hunters);

        // We will get a page from the PDF file by calling openPage
        ParcelFileDescriptor fileDescriptor =
                ParcelFileDescriptor.open(fileCopy,
                        ParcelFileDescriptor.MODE_READ_ONLY);
        mPdfRenderer = new PdfRenderer(fileDescriptor);
        mPdfPage = mPdfRenderer.openPage(pageNumber);

        // Create a new bitmap and render the page contents on to it
        Bitmap bitmap = Bitmap.createBitmap(mPdfPage.getWidth(),
                mPdfPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        mPdfPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        // Set the bitmap in the ImageView so we can view it
        imageView.setImageBitmap(bitmap);
    }
}
