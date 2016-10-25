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
    int _quantity;
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
    public int getQuantity(){
        return _quantity;
    }
    public void setQuantity(int i){_quantity=i;}

//    public item(String d, int q, java.util.Date dt){
    public item(String d, int q, long ts){
        _id=0;
        _data=d;
        _quantity=q;
//        _date=dt;
        _timestamp=ts;
    }
    @Override
    public String toString(){
//        return _id + ", "+_data+", "+_quantity + ", " + DataReaderDBHelper.getDateString(_date);
        return _id + ", "+_data+", "+_quantity + ", " + DataReaderDBHelper.getDateString(_timestamp);
    }

    public String csvLine(){
        return "\""+_data+"\"; "+_quantity + "; \"" + DataReaderDBHelper.getDateString(_timestamp)+"\"";
    }
}
