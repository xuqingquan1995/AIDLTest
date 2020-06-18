package top.xuqingquan.ipctest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import top.xuqingquan.plugins.IPluginsAidlInterface;

public class MainActivity2 extends AppCompatActivity {
    private IPluginsAidlInterface iPluginsAidlInterface;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPluginsAidlInterface = IPluginsAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String action = getIntent().getStringExtra("action");
        String pkgName = getIntent().getStringExtra("pkgName");
        Intent intent = new Intent(action);
        intent.setPackage(pkgName);
        bindService(intent, connection, BIND_AUTO_CREATE);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iPluginsAidlInterface.startMainActivity();
                    finish();
                } catch (RemoteException e) {
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