package co.za.gmapssolutions.ekse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class Tab_2_Activity extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_tab_2, container, false);

    }
    @Override
    public  void  onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.list);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        StableArrayAdapter adapter = new StableArrayAdapter(getContext(),values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {

        Toast.makeText(getContext(),"Clicked : " + position,Toast.LENGTH_LONG).show();
    }
}