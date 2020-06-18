package top.xuqingquan.plugins;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import top.xuqingquan.ipctest.IHostAidlInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AIDL_TEST";

    private IHostAidlInterface iHostAidlInterface;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: 11iHostAidlInterface==>" + (iHostAidlInterface == null));
            iHostAidlInterface = IHostAidlInterface.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected: 22iHostAidlInterface==>" + (iHostAidlInterface == null));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("top.xuqingquan.host");
        intent.setPackage("top.xuqingquan.ipctest");
        bindService(intent, connection, BIND_AUTO_CREATE);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d(TAG, "onClick: iHostAidlInterface==>" + (iHostAidlInterface == null));
                    iHostAidlInterface.startAdActivity(getPackageName(), "top.xuqingquan.plugins");
                    Log.d(TAG, "onClick: success");
                    finish();
                } catch (RemoteException e) {
                    Log.e(TAG, "onClick: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
