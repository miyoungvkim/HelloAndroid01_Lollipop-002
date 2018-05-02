package app.android.com.helloandroid_lollipop;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Layout_Service_test extends AppCompatActivity implements View.OnClickListener {

    private Button btn_service_start, btn_service_bind, btn_service_unbind, btn_service_stop;
    private MyService mService;
    private boolean isBind;

    private static final String TAG = "TAG";

    ServiceConnection sconn = new ServiceConnection() {
        @Override //서비스가 실행될 때 호출
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            mService = myBinder.getService();

            isBind = true;

            Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
        }

        @Override //서비스가 종료될 때 호출
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isBind = false;
            Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_service_test);

        Log.d(TAG," "+new Throwable().getStackTrace()[0].getMethodName()+"()"+"#"+new Throwable().getStackTrace()[0].getLineNumber());

        //버튼에 대한 참조
        btn_service_start = (Button) findViewById(R.id.btn_service_start);
        btn_service_stop = (Button) findViewById(R.id.btn_service_stop);
        btn_service_bind = (Button) findViewById(R.id.btn_service_bind);
        btn_service_unbind = (Button) findViewById(R.id.btn_service_unbind);

        //각 버튼에 대한 리스너 연결 - OnClickListener를 확장했으므로 onClick 오버라이딩 후 this사용
        btn_service_start.setOnClickListener(this );
        btn_service_stop.setOnClickListener(this);
        btn_service_bind.setOnClickListener(this);
        btn_service_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_service_start :
                startService(new Intent(Layout_Service_test.this, MyService.class)); // 서비스 시작
                break;

            case R.id.btn_service_stop :
                stopService(new Intent(Layout_Service_test.this, MyService.class)); // 서비스 종료
                break;

            case R.id.btn_service_bind :
                if (!isBind) //해당 액티비이에서 바운딩 중이 아닐때만 호출 - 바운딩 시작
                    bindService(new Intent(Layout_Service_test.this, MyService.class), sconn, BIND_AUTO_CREATE);
                break;

            case R.id.btn_service_unbind :
                if (isBind) //해당 액티비티에서 바운딩중일때만 호출 - 바운딩 종료
                    unbindService(sconn);
                break;
            default :
                break;
        }
    }
}
