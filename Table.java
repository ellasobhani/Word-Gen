//I am the sole author of the work in this repository.
import structure5.*;

/**
* A Table holds a collection of strings, each of which has an
* associated FrequencyList
*/
public class Table {

  private Vector<Association<String, FrequencyList>> table;

  /** Construct an empty table */
  public Table() {
    table = new Vector<Association<String, FrequencyList>>();
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyList
  * by adding value to it
  * Otherwise, create a FrequencyList for key and add value to it
  * @param key is the desired k-letter sequence
  * @param value is the character to add to the FrequencyList of key
  */
  public void add(String key, String value) {
    boolean foundKey = false;
    //traverse through vector of associations table
    for (int i = 0; i < table.size(); i++) {
      //store key in var element
      Association<String, FrequencyList> element = table.get(i);
      //if the key already exists in table add value to existing frequencylist
      if (key.equals(element.getKey())) {
        element.getValue().add(value);
        foundKey = true;
        //if the key doesn't exist in table add new association temp to table
      }
    }
    if (!foundKey) {
      FrequencyList freqList = new FrequencyList();
      freqList.add(value);
      Association<String, FrequencyList> temp = new Association<String, FrequencyList>(key, freqList);
      table.add(temp);
    }
  }


  /**
  * If key is in the table, return one of the characters from
  * its FrequencyList with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  * @param key is the k-letter sequence whose frequencies we want to use
  * @return a character selected from the corresponding FrequencyList
  */
  public String choose(String key) {
    boolean foundKey = false;
    FrequencyList freqList;
    //traverse through vector of associations table
    for (int i = 0; i < table.size(); i++) {
      //store association in var row
      Association<String, FrequencyList> row = table.get(i);
      //if the key already exists in freqList add one to the value
      if (key.equals(row.getKey())) {
        freqList = row.getValue();
        String chosenChar = freqList.choose();
        foundKey = true;
        return chosenChar;
      }
    }
    return "Key not in table.";
  }


  /** Produce a string representation of the Table
  * @return a String representing this Table
  */
  public String toString() {
    String  result = "";
    for (int g = 0; g < table.size(); g++) {
      Association<String, FrequencyList> element = table.get(g);
      String key = element.getKey();
      FrequencyList freqList = element.getValue();
      String printedFreqList = freqList.toString();


      result += "[ " + key + ": " + printedFreqList + " ] ";
    }
    return result;
  }

  //methods used for WordGen, ignore
  public static int size() {
    return table.size();
  }

  public static Association<String, FrequencyList> get(int i) {
    return table.get(i);
  }

  // Use main to test your Table class
  public static void main(String[] args) {
    Table test = new Table();
    test.add("je", "s");
    test.add("je", "s");
    test.add("st", "e");
    test.add("lo", "v");
    test.add("je", "e");
    String chosenKey = test.choose("je");
    System.out.println(chosenKey);
    String table = test.toString();
    System.out.println(table);

  }

}
