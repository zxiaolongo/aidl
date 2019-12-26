package cn.sinokj.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import aidl.IMyAidlService;
import cn.sinokj.aidl.basic_service.BasicService;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * startService
     * 清单文件注册
     * 服务基本使用
     */
    public void basicService() {
        /**
         * 开启服务
         * onCreate
         * onStartCommand
         * 多次开启（不执行onCrete,只执行onStartCommand）
         * onStartCommand
         * onStartCommand
         * onStartCommand
         */
        Intent startService = new Intent(MainActivity.this, BasicService.class);
        startService(startService);

        /**
         * 停止服务
         */
        Intent stopService = new Intent(MainActivity.this, BasicService.class);
        stopService(stopService);
    }


    /**
     * bindService
     */
    private BasicService.MyBinder mybind;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mybind = (BasicService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void bindBasicService() {
        /**
         *绑定服务
         */
        Intent bindService = new Intent(MainActivity.this, BasicService.class);
        bindService(bindService, serviceConnection, BIND_AUTO_CREATE);
        /**
         *解绑服务
         */
        unbindService(serviceConnection);
    }


    /**
     * aidl进程间通信
     */
    private IMyAidlService romoteBinder;
    private ServiceConnection remoteConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            romoteBinder = IMyAidlService.Stub.asInterface(service) ;
            try {
                Toast.makeText(MainActivity.this, romoteBinder.getString(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void unbind(View view) throws RemoteException {
        Toast.makeText(MainActivity.this, romoteBinder.getString(), Toast.LENGTH_SHORT).show();
        unbindService(remoteConnection);
    }

    public void bind(View view) {
        Intent intent = new Intent();
        intent.setAction("cn.sinokj.client.MyService");
        //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
        intent.setPackage("cn.sinokj.client");
        bindService(intent, remoteConnection, BIND_AUTO_CREATE);
    }
}
