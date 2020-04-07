package co.za.gmapssolutions.ekse;

import android.content.*;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class Tab_1_Activity extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private MediaPlayer mediaPlayer;
    String[] values;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_tab_1, container, false);
    }
    @Override
    public  void  onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        listView = getView().findViewById(R.id.list);
        values = new String[] { "01. Emotion.mp3", "02. Good Bad.mp3", "03. Dangerous.mp3",
                "04. Sauce.mp3", "05. Whatchamacallit feat. Chris Brown CDQ.mp3", "06. Cheap Shot.mp3",
                "08. Boo'd Up.mp3", "09. Everything (feat. John Legend).mp3",
                "10. Own It.mp3", "11. Run My Mouth.mp3", "12. Gut Feeling (feat. H.E.R.).mp3",
                "13. Trip.m4a", "14. Close.mp3", "15. Easy.mp3",
                "16. Naked (Bonus Track).mp3"};

        StableArrayAdapter adapter = new StableArrayAdapter(getContext(),values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        mediaPlayer = new MediaPlayer();

        try {
            if(Build.VERSION.SDK_INT >= 21) {
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
            }else{
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    togglePlayPause();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        try{
            mediaPlayer.setDataSource(getContext(), Uri.parse(ConfigAPI.API_URL
                    + values[position]));
            mediaPlayer.prepareAsync();

        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        //togglePlayPause();

        //Toast.makeText(getContext(),"Clicked : " + position,Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   final int position, long id) {

        Toast.makeText(getContext(),"Long click : " + position,Toast.LENGTH_LONG).show();

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Options");

        builder.setItems(R.array.array_options,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item

                //Intent intent = new Intent(getContext(),DownloadSong.class);
                //intent.putExtra(DownloadSong.SONG_NAME,values[position]);

                //intent.putExtra(DownloadSong.MESSENGER,messenger);
                //getActivity().startService(intent);

                Toast.makeText(getContext(),"Saving song: " + position,Toast.LENGTH_LONG).show();

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                   //

                Toast.makeText(getContext(),"Clicked : Cancel" + id,Toast.LENGTH_LONG).show();
            }
        });

       //builder.setView(R.layout.dialog);

        AlertDialog dialog = builder.create();

        dialog.show();

        return  true;
    }
       private void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            //mPlayerControl.setImageResource(R.drawable.ic_play);
        } else {
            mediaPlayer.start();
            //mPlayerControl.setImageResource(R.drawable.ic_pause);
        }
    }

}
