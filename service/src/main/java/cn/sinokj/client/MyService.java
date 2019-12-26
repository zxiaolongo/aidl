package cn.sinokj.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import aidl.IMyAidlService;

public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceBinder();
    }
    public class MyServiceBinder extends IMyAidlService.Stub{
        @Override
        public String getString() throws RemoteException {
            return "我是服务端str";
        }
    }
}
