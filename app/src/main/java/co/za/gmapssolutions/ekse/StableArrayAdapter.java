package co.za.gmapssolutions.ekse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    private final Context context;
    private final String[] values;

    public StableArrayAdapter(Context context,String[] objects) {
        super(context, R.layout.line_adapter, objects);
        //for (int i = 0; i < objects.size(); ++i) {
          //  mIdMap.put(objects.get(i), i);
        //}
        this.context = context;
        this.values = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.line_adapter, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        firstLine.setText(values[position]);
        secondLine.setText(values[position]);
        //imageView.setImageResource(R.drawable.folder);
        return rowView;
    }
    //@Override
    //public long getItemId(int position) {
      //  String item = getItem(position);
       // return mIdMap.get(item);
    //}

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
