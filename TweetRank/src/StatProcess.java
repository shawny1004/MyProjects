import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Shuo Yang on 3/16/2017.
 */
public class StatProcess implements Comparator<Tweet> {
//This object is designed to perform statistical calculations.
public void groupTwitterByTime(){


  }

public void sort(ArrayList<Tweet> a){
  ArrayList<Tweet> b = new ArrayList<Tweet>();
  Comparator<Tweet> cp= new StatProcess();
   a.sort(cp);
}
  @Override
  public int compare(Tweet o1, Tweet o2) {
  int date1=Integer.parseInt(o1.getTwitId().substring(0,5));
  int date2=Integer.parseInt(o2.getTwitId().substring(0,5));
    if(date1<date2){
      return -1;
    }
    if(date1==date2) {
      return 0;
    }else {
      return 1;
    }
  }
}
