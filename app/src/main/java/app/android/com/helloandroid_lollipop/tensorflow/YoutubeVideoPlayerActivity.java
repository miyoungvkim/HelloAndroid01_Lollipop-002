package app.android.com.helloandroid_lollipop.tensorflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.android.com.helloandroid_lollipop.R;

public class YoutubeVideoPlayerActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
        setContentView( R.layout.activity_youtube_videoplayer_besed_tensorflow);
            if (null == savedInstanceState) {
            getFragmentManager()
                    .beginTransaction()
                    .replace( R.id.container, YoutubeVideoPlayer2BasicFragment.newInstance())
                    .commit();
        }
    }
}
