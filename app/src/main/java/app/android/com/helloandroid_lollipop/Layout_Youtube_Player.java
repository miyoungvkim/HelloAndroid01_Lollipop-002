package app.android.com.helloandroid_lollipop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static app.android.com.helloandroid_lollipop.youtube.config.YOUTUBE_API_KEY;

public class Layout_Youtube_Player extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    Button button;
    YouTubePlayer.OnInitializedListener listener;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_youtube_player_test);

        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        button = (Button) findViewById(R.id.youtubeButton);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        listener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
                // youTubePlayer.loadVideo("UPRuZcaZs9o"); // load 및 재생할 youtube id 입력
                youTubePlayer.loadVideo("DpvUZnNpm0I"); // load 및 재생할 youtube id 입력
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
            }
        };

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeView.initialize(YOUTUBE_API_KEY, listener);
            }
        } );
    }
}