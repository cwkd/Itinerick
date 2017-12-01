package com.example.owner.itinerick3;

import android.provider.BaseColumns;

/**
 * Created by Owner on 12/1/2017.
 */

public class LocationContract {
    public static final class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "LocationRecord";
        public static final String COL_KEYWORDS = "Location";
        public static final String COL_LOCATION = "Keywords";
    }
}
