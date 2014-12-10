package lc.addressbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lc.addressbook.Models.Result;
import lc.addressbook.R;

/**
 * Created by LC on 14-12-09.
 */
public class ListAdapter  extends ArrayAdapter<Result> {

    private Context mcontext;
    private List<Result> results;
    private int mResource;

    public ListAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        mcontext = context;
        results = objects;
        mResource = resource;
    }



    private static class ViewHolder {
        ImageView picture;
        TextView name;
        TextView phone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder; // to reference the child views for later actions

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.row, null);

            // cache view fields into the holder
            holder = new ViewHolder();
            holder.picture = (ImageView) view.findViewById(R.id.iv_picture);
            holder.name = (TextView) view.findViewById(R.id.txt_name);
            holder.phone = (TextView) view.findViewById(R.id.txt_number);

            // associate the holder with the view for later lookup
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        // object item based on the position
        Result result = results.get(position);

        // assign values if the object is not null
        if(result != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            Picasso.with(getContext()).load(result.getUser().getPicture().getThumbnail()).into(holder.picture);
            holder.name.setText(result.getUser().getName().getFirst() + " " + result.getUser().getName().getLast());
            holder.phone.setText(result.getUser().getPhone());
        }

        return view;
    }
}
