
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Created by Shuo Yang on 3/16/2017.
 */
public class Main {
public static void main(String[] args) throws FileNotFoundException {
  FileScanner fileScanner = new FileScanner();
  fileScanner.scanTheFile();
  fileScanner.twitterDistribute();
  StatProcess sp= new StatProcess();
  sp.sort(fileScanner.getTweetList());
  for(int i =0;i<fileScanner.getTweetList().size();i++){
    if(!fileScanner.getTweetList().get(i).getUsername().equals("DrAhmedAHamed")) {
      System.out.println(fileScanner.getTweetList().get(i).getTwitId().substring(0,5) + "");
      printTwitter(fileScanner.getTweetList().get(i));
      System.out.println();
    }
  }
  /*for(int i =0;i<fileScanner.getTweetList().size();i++ ){
    System.out.println("This is the #"+(i+1)+" Twitter");
    printTwitter(fileScanner.getTweetList().get(i));
  }
  for(int i=0;i<fileScanner.getUserList().size();i++){
    System.out.println("This is the #"+ i+" User");
    printUser(fileScanner.getUserList().get(i));
  }*/
 /* for(int i=0;i<fileScanner.getUserList().size();i++){
    System.out.println("UserName ="+ fileScanner.getUserList().get(i).getUsername());
    printTwitterByUser(fileScanner.getUserList().get(i));
  }*/
}
  public static void printTwitter(Tweet t){
    System.out.println("username = "+ t.getUsername());
   // System.out.println("TwitterID = "+ t.getTwitId());
   /* System.out.print("Mentions =");
    if(t.getMentions().size()!=0){
    for(int i=0;i<t.getMentions().size();i++){
      System.out.print(t.getMentions().get(i)+" ");
    }
    System.out.println();}
    System.out.print("HashTags =");
    if(t.getHashtags().size()!=0){
    for(int i=0;i<t.getHashtags().size();i++){
      System.out.print(t.getHashtags().get(i)+" ");
    }
    System.out.println();}*/
    System.out.print("Contents =");
    for(int i=0;i<t.getContent().size();i++){
      System.out.print(t.getContent().get(i)+" ");

    }
    System.out.println();
  }
  public static void printUser(User u){
    System.out.println("Username = "+u.getUsername());
    System.out.println("Name = "+u.getName());
  }
  public static void printTwitterByUser(User u){
    for(int i=0;i<u.getTweetList().size();i++){
      System.out.print("Twitter #"+i);
      for(int j=0;j<u.getTweetList().get(i).getContent().size();j++) {
        System.out.print(u.getTweetList().get(i).getContent().get(j) + " ");
      }
      System.out.println();
    }
  }
}
