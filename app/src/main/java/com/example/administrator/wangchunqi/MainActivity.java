package com.example.administrator.wangchunqi;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.textView)
    TextView total;
    @BindView(R.id.progressBar)
    ProgressBar mprogressBar;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.delete)
    Button pause;
    @BindView(R.id.jiecao_Player)
    JCVideoPlayerStandard jiecaoPlayer;
    private int max;
    private DownloadUtil mDownloadUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        String url = "http://mp4.vjshi.com/2018-01-11/89194254d6ada11d3d24c7b460bba503.mp4";
        String localpath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local2";
        mDownloadUtil = new DownloadUtil(4, localpath, "chy0115.mp4", url, this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {
            @Override
            public void downloadStart(int fileSize) {
                Log.w(TAG, "fileSize::" + fileSize);
                max = fileSize;
                mprogressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                Log.w(TAG, "Compelete::" + downloadedSize);
                mprogressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");
            }

            @Override
            public void downloadEnd() {
                Log.w(TAG, "ENd");
                String s1="http://mp4.vjshi.com/2018-01-11/89194254d6ada11d3d24c7b460bba503.mp4";
                jiecaoPlayer.setUp(s1,jiecaoPlayer.SCREEN_LAYOUT_NORMAL,"视频标题");
            }
        });
    }

    @OnClick({R.id.start, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                mDownloadUtil.start();
                break;
            case R.id.delete:
                mDownloadUtil.pause();
                break;
        }
    }
}