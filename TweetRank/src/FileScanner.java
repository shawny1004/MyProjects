import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class controls the file scan process.
 */
public class FileScanner {

  public ArrayList<User> getUserList() {
    return userList;
  }

  public ArrayList<Tweet> getTweetList() {
    return tweetList;
  }

  private ArrayList<User> userList;
  private ArrayList<Tweet> tweetList;

  public FileScanner() {
    userList = new ArrayList<User>();
    tweetList = new ArrayList<Tweet>();
  }


  public void scanTheFile() throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream("array.txt"));
    ArrayList<String> fileList = new ArrayList<String>();
    while (sc.hasNext()) {
      fileList.add(sc.next());
    }
    for (int i = 0; i < fileList.size(); ) {
      String nextWord = fileList.get(i);
      String name = "";
      if (nextWord.equals("{uName:")) {
        //every time the cursor encounters uName means a new line has started.
        //there fore create a new tweet and user object.
        Tweet tweetToBeAdded = new Tweet();
        User userToBeAdded = new User();
        //get the next string, which indicates the username starting with " and end with ",
        tweetToBeAdded
            .setUsername(fileList.get(i + 1).substring(1, fileList.get(i + 1).length() - 2));
        userToBeAdded
            .setUsername(fileList.get(i + 1).substring(1, fileList.get(i + 1).length() - 2));
        i += 2;
        while (!fileList.get(i + 1).equals("Hashtags:")) {
          name += (fileList.get(i + 1).replaceAll("[^a-zA-Z0-9\\s]", "") + " ");
          i++;
        }
        userToBeAdded.setName(name);
        i++;
        while (!fileList.get(i + 1).equals("Mentions:")) {
          tweetToBeAdded.getHashtags().add(fileList.get(i + 1).replaceAll("[^a-zA-Z0-9\\s]", ""));
          i++;
        }
        i++;
        while (!fileList.get(i + 1).equals("TwitID:")) {
          tweetToBeAdded.getMentions().add(fileList.get(i + 1).replaceAll("[^a-zA-Z0-9\\s]", ""));
          i++;
        }
        i++;
        tweetToBeAdded
            .setTwitId(fileList.get(i + 1).substring(0, fileList.get(i + 1).length() - 1));
        i += 2;
        while (i < fileList.size() - 1 && (!fileList.get(i + 1).equals("{uName:"))) {
          tweetToBeAdded.getContent().add(fileList.get(i + 1));
          i++;
        }
        tweetToBeAdded.getContent().set(0, tweetToBeAdded.getContent().get(0).substring(1));
        tweetToBeAdded.getContent().set(tweetToBeAdded.getContent().size() - 1,
            tweetToBeAdded.getContent().get(tweetToBeAdded.getContent().size() - 1)
                .substring(0,
                    tweetToBeAdded.getContent().get(tweetToBeAdded.getContent().size() - 1).length()
                        - 3));
        tweetList.add(tweetToBeAdded);
        if (!this.containsUser(name)) {
          userList.add(userToBeAdded);
        }
      } else {
        i++;
      }
    }
  }


  private boolean containsUser(String name) {
    if (userList.size() == 0) {
      return false;
    }
    for (int i = 0; i < userList.size(); i++) {
      if (this.userList.get(i).getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public void twitterDistribute() {
    for (int i = 0; i < this.getTweetList().size(); i++) {
      for (int j = 0; j < this.getUserList().size(); j++) {
        if (this.getUserList().get(j).getUsername()
            .equals(this.getTweetList().get(i).getUsername())) {
          this.getUserList().get(j).getTweetList().add(this.getTweetList().get(i));
        }
      }
    }
  }


}
