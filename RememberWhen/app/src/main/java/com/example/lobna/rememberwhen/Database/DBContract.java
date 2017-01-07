package com.example.lobna.rememberwhen.Database;

import android.provider.BaseColumns;

/**
 * Created by Lobna on 07-Jan-17.
 */

public class DBContract {
    public static final class MemoryTable implements BaseColumns{
        public static final String TABLE_NAME = "memories";
        public static final String _ID = "_ID";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";
    }
}
