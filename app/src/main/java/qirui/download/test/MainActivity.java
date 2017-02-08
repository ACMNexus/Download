package qirui.download.test;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

import qirui.download.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private long download_id = -1;
    private static final String DOWNLOAD_URL = "https://download.alicdn.com/wangwang/AliIM2016_taobao(8.60.01C).exe?spm=a220o.1000855.0.0.vguvGV&file=AliIM2016_taobao(8.60.01C).exe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();
    }

    private void init() {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(DOWNLOAD_URL);
        request = new DownloadManager.Request(uri);
        File file = new File(Environment.getExternalStorageDirectory() + "/downTest/test.apk");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        request.setDestinationUri(Uri.fromFile(file));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//        request.setNotificationVisibility(View.GONE);
    }

    private void initView() {
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startDownload();
                break;
            case R.id.pause:
                pauseDownload();
                break;
            case R.id.cancel:
                cancelDownload();
                break;
        }
    }

    private void startDownload() {
        download_id = downloadManager.enqueue(request);
    }

    private void pauseDownload() {
    }

    private void cancelDownload() {
        downloadManager.remove(download_id);
    }
}
