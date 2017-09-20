import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyly513 on 3/13/17.
 */
public class Tweet {
  private String twitId;
  private String username;
  private List<String> hashtags;
  private List<String> mentions;
  private List<String> content;

   public Tweet() {
      twitId=null;
      username=null;
      hashtags= new ArrayList<String>();
      mentions= new ArrayList<String>();
      content = new ArrayList<String>();
   }

   public String getTwitId() {
      return twitId;
   }

   public String getUsername() {
      return username;
   }

   public List<String> getHashtags() {
      return hashtags;
   }

   public List<String> getMentions() {
      return mentions;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setTwitId(String twitId) {
      this.twitId = twitId;
   }

   public List<String> getContent() {
      return content;
   }

}
