//I am the sole author of the work in this repository.
import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class WordGen {
  //instance variables
  private String resultString;

  public WordGen() {
    resultString = "";
  }

  public static String storeText(Scanner sc) {
    //read and store text from text file inputted by user in the command line
    StringBuffer textBuffer = new StringBuffer();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      textBuffer.append(line);
      textBuffer.append("\n");
    }

    String text = textBuffer.toString();
    return text;
  }

  //method to create the table and fill it with all appropriate values
  public static Table createTable(String text, int k) {
    //construct empty table
    Table table = new Table();
    table.add(text.substring(0, k), text.substring(k, k + 1));
    //traverse through text k elements at a time
    for (int i = 0; i < 500; i++) {
      String nextChar = text.substring(i, k + i + 1);
      //add the k first k elements of the substring to the table as the key and the character that comes next as the value)
      table.add(nextChar.substring(0, k), nextChar.substring(k));
    }
    return table;
  }

  public static String fillString(Table table, int k, String text) {
    //Add initial k characters to resultString ("seed")
    resultString = text.substring(0, k);
    //continue adding to string until string length is 500
    while (resultString.length() < 500) {
      //traverse through table pulling out each association, its key, and choosing a char to append onto resultString
      for (int i = 0; i < table.size(); i++) {
        Association<String, FrequencyList> element = table.get(i);
        String key = element.getKey();
        String nextChar = table.choose(key);
        resultString += nextChar;
      }
    }
    return resultString;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    WordGen wg = new WordGen();

    if (args.length == 0) {
      // no args, so print usage line and do nothing else
      System.out.println("Usage: java WordGen ");
    } else {
      // convert first argument to k
      int k = Integer.parseInt(args[0]);
      // second argument stored in String file
      String text = wg.storeText(sc);
      Table table = wg.createTable(text, k);
      String result = wg.fillString(table, k, text);
      System.out.println(result);



    }
  }
}
