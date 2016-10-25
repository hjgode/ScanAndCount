package hsm.demo.scanandcount;

import android.provider.BaseColumns;

/**
 * Created by hgode on 10/23/16.
 */
public final class DataReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DataReaderContract() {}

    /* Inner class that defines the table contents */
    public static class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "DATA_TABLE";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DATA = "daten";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_DATE = "date";
    }


}
