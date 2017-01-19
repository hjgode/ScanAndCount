package hsm.demo.scanandcount;

// Date formatting: SimpleDateFormat databaseDateTimeFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
// String currentDateandTime = databaseDateTimeFormate.format(new Date());     //2009-06-30 08:29:36
// see http://stackoverflow.com/questions/5369682/get-current-time-and-date-on-android
/**
 * Created by hgode on 10/22/16.
 */
public class item {
    long _id;
//    java.util.Date _date;
    String _data;
    long _quantity;
    String _lagerort;
    long _timestamp;

    public long getTimestamp() {
        return _timestamp;
    }
    public void setTimestamp(long lTimestamp){
        _timestamp=lTimestamp;
    }

    public String getData(){
        return _data;
    }
    public void setData(String s){_data=s;}

    public long getQuantity(){
        return _quantity;
    }
    public void setQuantity(int i){_quantity=i;}

    public String  getLagerort(){return _lagerort;}
    public  void set_lagerort(String s){_lagerort=s;};
/*
//    public item(String d, int q, java.util.Date dt){
    public item(String d, int q, long ts){
        _id=0;
        _data=d;
        _lagerort="-";
        _quantity=q;
//        _date=dt;
        _timestamp=ts;
    }
*/
    public item(String d, String l, long q, long ts){
        _id=0;
        _data=d;
        _lagerort=l;
        _quantity=q;
//        _date=dt;
        _timestamp=ts;
    }

    @Override
    public String toString(){
//        return _id + ", "+_data+", "+_quantity + ", " + DataReaderDBHelper.getDateString(_date);
        return _id + ", "+_data+", "+_lagerort+", "+_quantity + ", " + DataReaderDBHelper.getDateString(_timestamp);
    }

    public static String csvHeader(){
        return "Artikel; Menge; Lager; Datum";
    }
    public String csvLine(){
        return "\""+_data+"\""+"; "
                + ""   + _quantity + "; "
                + "\"" + _lagerort + "\"" +"; "
                + "\"" + DataReaderDBHelper.getDateString(_timestamp)+"\"";
    }
}
