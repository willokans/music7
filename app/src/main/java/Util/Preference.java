package Util;

import android.app.Activity;
import android.content.SharedPreferences;



/**
 * Created by willokans on 09/07/2017.
 */

public class Preference {

    /**
     * this class will allow user to change and search by city
     */

    SharedPreferences preferences;

    //create a constructor

    public Preference(Activity activity) {

        //instantiate our preference
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public void setCity(String city) {
        preferences.edit().putString("city", city).commit();


    }

    //if use has not chosen a city, return default
    public String getCity() {

        return preferences.getString("city", "dublin");
    }




}
