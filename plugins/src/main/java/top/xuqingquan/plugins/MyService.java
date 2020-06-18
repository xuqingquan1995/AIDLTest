package top.xuqingquan.plugins;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(getApplicationContext());
    }

    private static class MyBinder extends IPluginsAidlInterface.Stub {

        private Context context;

        public MyBinder(Context context) {
            this.context = context;
        }

        @Override
        public void startMainActivity() throws RemoteException {
            Intent intent = new Intent(context, MainActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
