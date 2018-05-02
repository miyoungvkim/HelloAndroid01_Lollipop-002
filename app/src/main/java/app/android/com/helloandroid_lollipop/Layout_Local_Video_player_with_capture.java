package app.android.com.helloandroid_lollipop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Layout_Local_Video_player_with_capture extends Activity {

    private static final String TAG = "TAG";

    MediaMetadataRetriever mediaMetadataRetriever;
    MediaController myMediaController;
    VideoView myVideoView;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_local_video_player_with_capture);

        mContext = getApplicationContext();
        String video_url = "android.resource://"+getPackageName()+"/raw/pororo01";
        Uri video_uri = Uri.parse(video_url);
        mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(mContext, video_uri);
        
        myVideoView = (VideoView) findViewById(R.id.localplayer_videoview);
        myVideoView.setVideoURI(video_uri);
        myMediaController = new MediaController(this);
        myVideoView.setMediaController(myMediaController);

        myVideoView.setOnCompletionListener(myVideoViewCompletionListener);
        myVideoView.setOnPreparedListener(MyVideoViewPreparedListener);
        myVideoView.setOnErrorListener(myVideoViewErrorListener);

        myVideoView.requestFocus();
        myVideoView.start();

        Button buttonCapture = (Button)findViewById(R.id.capture);
        buttonCapture.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {

                int currentPosition = myVideoView.getCurrentPosition(); //in millisecond
                Toast.makeText(Layout_Local_Video_player_with_capture.this,
                        "Current Position: " + currentPosition + " (ms)",
                        Toast.LENGTH_LONG).show();

                Bitmap bmFrame = mediaMetadataRetriever
                        .getFrameAtTime(currentPosition * 1000); //unit in microsecond

                if(bmFrame == null){
                    Toast.makeText(Layout_Local_Video_player_with_capture.this,
                            "bmFrame == null!",
                            Toast.LENGTH_LONG).show();
                }else{
                    AlertDialog.Builder myCaptureDialog =
                            new AlertDialog.Builder(Layout_Local_Video_player_with_capture.this);
                    ImageView capturedImageView = new ImageView(Layout_Local_Video_player_with_capture.this);
                    capturedImageView.setImageBitmap(bmFrame);
                    LayoutParams capturedImageViewLayoutParams =
                            new LayoutParams(LayoutParams.WRAP_CONTENT,
                                    LayoutParams.WRAP_CONTENT);
                    capturedImageView.setLayoutParams(capturedImageViewLayoutParams);

                    myCaptureDialog.setView(capturedImageView);
                    myCaptureDialog.show();
                }

            }});
    }

    MediaPlayer.OnCompletionListener myVideoViewCompletionListener =
            new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    Toast.makeText(Layout_Local_Video_player_with_capture.this, "End of Video",
                            Toast.LENGTH_LONG).show();
                }
            };

    MediaPlayer.OnPreparedListener MyVideoViewPreparedListener =
            new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {

                    long duration = myVideoView.getDuration(); //in millisecond
                    Toast.makeText(Layout_Local_Video_player_with_capture.this,
                            "Duration: " + duration + " (ms)",
                            Toast.LENGTH_LONG).show();

                }
            };

    MediaPlayer.OnErrorListener myVideoViewErrorListener =
            new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {

                    Toast.makeText(Layout_Local_Video_player_with_capture.this,
                            "Error!!!",
                            Toast.LENGTH_LONG).show();
                    return true;
                }
            };

}