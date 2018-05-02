package app.android.com.helloandroid_lollipop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.android.com.helloandroid_lollipop.youtube.PlayerActivity;
import app.android.com.helloandroid_lollipop.youtube.VideoItem;
import app.android.com.helloandroid_lollipop.youtube.YoutubeConnector;

import com.squareup.picasso.Picasso;

public class Layout_Youtube_PlayeList extends AppCompatActivity {

    private EditText searchInput;
    private ListView videosFound;
    private Handler handler;

    private List<VideoItem> searchResults;

    private final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_youtube_playelist);

        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        searchInput = (EditText) findViewById(R.id.search_input);
        videosFound = (ListView) findViewById(R.id.videos_found);

        handler = new Handler();

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    Log.d(TAG,"IME_ACTION_DONE "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
                    Log.d(TAG,"v.getText().toString() "+v.getText().toString()+ new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

                    searchOnYoutube(v.getText().toString());
                    return false;
                }
                return true;
            }
        });

        addClickListener();
    }

    private void searchOnYoutube(final String keywords) {
        new Thread(){
            @Override
            public void run() {
                Log.d(TAG,"Run " + new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

                YoutubeConnector yc = new YoutubeConnector(Layout_Youtube_PlayeList.this);
                searchResults = yc.search(keywords);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound() {
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.sublayout_video_item, searchResults) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.d(TAG,"1 " + new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.sublayout_video_item, parent, false);
                }
                Log.d(TAG,"2 " + new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

                ImageView thumbnail = (ImageView) convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView) convertView.findViewById(R.id.video_title);
                TextView description = (TextView) convertView.findViewById(R.id.video_description);

                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getDescription());
                description.setText(searchResult.getDescription());
                Log.d(TAG,"2 test " +searchResult.getDescription() + new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());


                Log.d(TAG,"3 " + new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

                return convertView;
            }
        };
        videosFound.setAdapter(adapter);
    }

    private void addClickListener() {
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), PlayerActivity.class);
                intent.putExtra("VIDEO_ID", searchResults.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
