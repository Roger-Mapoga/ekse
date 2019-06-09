package co.za.gmapssolutions.ekse;

import android.content.Intent;
import android.os.Binder;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DownloadSong extends DownloadService{

    public static final int MAX_CONCURRENCY = 5;
    public DownloadSong(){
        super(Executors.newFixedThreadPool(MAX_CONCURRENCY,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setPriority(Thread.MIN_PRIORITY);
                        t.setName("song-download-thread");
                        return t;
                    }
                }));
    }
    protected void onHandleIntent(Intent intent){
        //intent.setData

        DownloadProcess("16. Naked (Bonus Track).mp3");

    }
    public  void DownloadProcess(String songName){
        int count;

        try {
            //open database
            DataBaseAdapter DBAdapter = new DataBaseAdapter(this);
            DBAdapter.open();

            URL url = new URL(ConfigAPI.API_URL+ songName);
            URLConnection session = url.openConnection();
            session.connect();

            InputStream song = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream bas = new ByteArrayOutputStream();

            byte data[] = new byte[1024];

            //long total = 0;

            while ((count = song.read(data)) != -1){
                //total += count;
                bas.write(data,0,count);
            }

            DBAdapter.insertSong("Ella Main","Naked",bas.toByteArray(),"mp3");

            song.close();

        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
    }

}
