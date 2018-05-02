package app.android.com.helloandroid_lollipop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import app.android.com.helloandroid_lollipop.tensorflow.CameraActivity;
import app.android.com.helloandroid_lollipop.tensorflow.LocalVideoPlayerActivity;
import app.android.com.helloandroid_lollipop.tensorflow.YoutubeVideoPlayerActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_go_to_servce_layout, btn_go_to_youtube_test_layout, btn_go_to_youtube_play_list, btn_go_to_local_videoplay_layout;
    private Button btn_go_to_tenserflow_layout;
    private Button btn_go_to_youtube_videoplay_with_tensorflow_layout, btn_go_to_videoplay_with_tensorflow_layout ;

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        //버튼에 대한 참조
        btn_go_to_servce_layout = (Button)findViewById(R.id.btn_go_to_servce_layout);
        btn_go_to_servce_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Layout_Service_test.class);
                startActivity(intent);
            }
        });

        btn_go_to_youtube_test_layout = (Button) findViewById(R.id.btn_go_to_youtube_test_layout);
        btn_go_to_youtube_test_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Layout_Youtube_Player.class);
                startActivity(intent);
            }
        });

        btn_go_to_local_videoplay_layout = (Button) findViewById(R.id.btn_go_to_local_videoplay_layout);
        btn_go_to_local_videoplay_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Layout_Local_Video_player_with_capture.class);
                startActivity(intent);
            }
        });

        // add for tenser flow layout
        btn_go_to_tenserflow_layout = (Button) findViewById(R.id.btn_go_to_tenserflow_layout);
        btn_go_to_tenserflow_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        //
        btn_go_to_youtube_videoplay_with_tensorflow_layout = (Button) findViewById(R.id.btn_go_to_youtube_videoplay_with_tensorflow_layout);
        btn_go_to_youtube_videoplay_with_tensorflow_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YoutubeVideoPlayerActivity.class);
                startActivity(intent);
            }
        });

        btn_go_to_videoplay_with_tensorflow_layout = (Button) findViewById(R.id.btn_go_to_videoplay_with_tensorflow_layout);
        btn_go_to_videoplay_with_tensorflow_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocalVideoPlayerActivity.class);
                startActivity(intent);
            }
        });

        btn_go_to_youtube_play_list = (Button) findViewById(R.id.btn_go_to_youtube_play_list);
        btn_go_to_youtube_play_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Layout_Youtube_PlayeList.class);
                startActivity(intent);
            }
        });

    }

}
