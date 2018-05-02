package app.android.com.helloandroid_lollipop;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "TAG";
    private IBinder mIBinder = new MyBinder();

    public int var = 777; //서비스바인딩의 예시로 출력할 값

    private Ringtone ringtone;

    class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
        return mIBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flgs, int STARTid) {
        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        Uri ringtone_uri = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_RINGTONE );
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtone_uri);
        ringtone.play();

        Toast.makeText(getApplicationContext(),"Service Start",Toast.LENGTH_LONG).show();

        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
        if(ringtone.isPlaying()) {
            Toast.makeText(getApplicationContext(),"Service Stop",Toast.LENGTH_LONG).show();
            ringtone.stop();
        }
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        return super.onUnbind(intent);
    }

}
