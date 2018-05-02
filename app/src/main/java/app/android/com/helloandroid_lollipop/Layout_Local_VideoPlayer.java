package app.android.com.helloandroid_lollipop;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class Layout_Local_VideoPlayer extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_local_video_player);

        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        String video_url="android.resource://"+getPackageName()+"/raw/pororo01";
        // String video_url="(rtsp://r6---sn-a5m7zu7s.c.youtube.com/CiILENy73wIaGQkBLc9thpQnGRMYDSANFEgGUgZ2aWRlb3MM/0/0/0/video.3gp";
        Uri uri = Uri.parse(video_url);
        VideoView video = (VideoView)findViewById(R.id.localvideoview);
        video.setVideoURI(uri);

        MediaController mc = new MediaController( this);
        video.setMediaController(mc);
        video.start();
    }
}
