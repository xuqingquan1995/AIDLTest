package top.xuqingquan.ipctest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "AIDL_TEST";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(getApplicationContext());
    }

    private static class MyBinder extends IHostAidlInterface.Stub {

        private Context context;

        public MyBinder(Context context) {
            this.context = context;
        }

        @Override
        public void startAdActivity(String pkgName, String action) throws RemoteException {
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("pkgName", pkgName);
            intent.putExtra("action", action);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.d(TAG, "startAdActivity: pkgName=" + pkgName + ",action=" + action);
        }
    }
}
