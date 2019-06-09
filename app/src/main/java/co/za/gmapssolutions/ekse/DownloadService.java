package co.za.gmapssolutions.ekse;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.util.concurrent.Executor;

public abstract class DownloadService extends Service {

    public class Access extends Binder {
        public  DownloadService getServie(){
            return DownloadService.this;
        }
    }
    private final Access binder = new Access();

    private final  Executor executor;
    private  int counter;
    private CompletionHandler completionHandler = new CompletionHandler();
    public DownloadService(Executor executor){
        this.executor = executor;
    }

    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }

    protected abstract void onHandleIntent(Intent intent);

    @Override
    public  int onStartCommand( final Intent intent,int flags,int startId){
        counter++;
     executor.execute(new Runnable() {
         @Override
         public void run() {
            try {
                onHandleIntent(intent);
            }finally {
                completionHandler.sendMessage(Message.obtain(completionHandler));
            }
         }
     });
     return START_REDELIVER_INTENT;
    }

    private class CompletionHandler extends Handler{
        @Override
        public void handleMessage(Message message){
            if(counter-- == 0){
                //Log.i(TAG,"0 tasks,stopping");
                stopSelf();
            }else{
                //Log.i(TAG, counter + " active tasks");
            }
        }
    }
}
