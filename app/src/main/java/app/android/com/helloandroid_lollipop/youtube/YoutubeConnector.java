package app.android.com.helloandroid_lollipop.youtube;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.android.com.helloandroid_lollipop.R;

import static app.android.com.helloandroid_lollipop.youtube.config.YOUTUBE_API_KEY;

public class YoutubeConnector {
    private YouTube youtube;
    private YouTube.Search.List query;


    private final String TAG = "TAG";
    public YoutubeConnector(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
                Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

            }
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try {
            Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

            query = youtube.search().list("id,snippet");
            query.setKey(YOUTUBE_API_KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        } catch (IOException e) {
            Log.d("YC", "Could not initialize: " + e.getMessage());
            Log.d(TAG,"Could not initialize: " + e.getMessage()+" "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        }
    }

    public List<VideoItem> search(String keywords) {
        query.setQ(keywords);
        try {
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();
            List<VideoItem> items = new ArrayList<VideoItem>();

            for (SearchResult result : results) {
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }

            return items;
        } catch (IOException e) {
            Log.d("YC", "Could not search: " + e);
            Log.d(TAG, "Could not search: " + e+ " "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

            return null;
        }
    }
}
