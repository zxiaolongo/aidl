package cn.sinokj.aidl.basic_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BasicService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ServiceTest","  ----->  onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServiceTest","  ----->  onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ServiceTest","  ----->  onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder{
        public void getString(){
            Log.d("ServiceTest","  ----->  getString");
        }
    }
}
