import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyly513 on 3/15/17.
 */
public class User {
  private String username;
  private String name;
  private List<Tweet> tweetList;
  public double score;

  public User(){
    username=null;
    name=null;
    tweetList= new ArrayList<Tweet>();
    score=0;
  }

  public String getUsername() {
    return username;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;

  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Tweet> getTweetList() {
    return tweetList;
  }


}
