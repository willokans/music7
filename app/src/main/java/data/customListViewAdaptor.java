package data;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import model.Event;
import musicshow.willokans.com.music7.AppController;
import musicshow.willokans.com.music7.R;

/**
 * Created by willokans on 09/07/2017.
 * This will take our list_row.xml and create a custom list view
 */

public class customListViewAdaptor extends ArrayAdapter<Event> {

    //create a layout inflater
    private LayoutInflater inflater;
    private ArrayList<Event> data;
    private Activity mContext;
    private int layoutResourceID;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    //create a constructor
    public customListViewAdaptor(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<Event> objs) {
        super(context, resource, objs);

        data = objs;
        mContext = context;
        layoutResourceID = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Event getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Event item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View row = convertView;
        ViewHolder viewHolder = null;

        if (row == null) {
            inflater = LayoutInflater.from((mContext));
            row = inflater.inflate(layoutResourceID, parent, false);

            viewHolder = new ViewHolder();


            //Get reference to our views
            viewHolder.bandImgae = (NetworkImageView) row.findViewById(R.id.bandImage);
            viewHolder.headliner = (TextView) row.findViewById(R.id.headLinerText);
            viewHolder.venue = (TextView) row.findViewById(R.id.venueText);
            viewHolder.when = (TextView) row.findViewById(R.id.whenText);
            viewHolder.where = (TextView) row.findViewById(R.id.whereText);

            //pass items into view hold
            row.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) row.getTag();
        }

        //getting the current position of our data and setting it to the current event
        viewHolder.event = data.get(position);

        //Display the data
        viewHolder.headliner.setText("Headliner: " + viewHolder.event.getHeadLiner());
        viewHolder.venue.setText("Venue: " + viewHolder.event.getVenueName());
        viewHolder.when.setText("When: " + viewHolder.event.getStartData());
        viewHolder.where.setText("Where: " + viewHolder.event.getStreet() + ", "
        + viewHolder.event.getCity() + ", " + viewHolder.event.getCountry());
        viewHolder.bandImgae.setImageUrl(viewHolder.event.getUrl(), imageLoader);
        viewHolder.website = viewHolder.event.getWebsite();





        return row;
    }



    //create a view holder class
    public class ViewHolder {

        Event event;
        TextView headliner;
        TextView venue;
        TextView where;
        TextView when;
        NetworkImageView bandImgae;
        String website;

    }

}
