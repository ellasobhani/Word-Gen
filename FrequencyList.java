//I am the sole author of the work in this repository.

import structure5.*;
import java.util.Random;

/**
* A FrequencyList stores a set of characters each of which has
* an associated integer frequency
*/

public class FrequencyList {
  private Vector<Association<String, Integer>> freqList;

  /** Construct an empty FrequencyList */
  public FrequencyList() {
    freqList = new Vector<Association<String, Integer>>();
  }

  /** add(String ch)
  * If ch is in the FrequencyList, increment it's associated frequency
  * Otherwise add ch to FrequencyList with frequency 1
  * @param ch is the String to add to the FrequencyList
  */
  public void add(String ch) {
    boolean foundKey = false;
    //traverse through vector of associations freqList
    for (int i = 0; i < freqList.size(); i++) {
      //store key in var element
      Association<String, Integer> element = freqList.get(i);
      //if the key already exists in freqList add one to the value
      if (ch.equals(element.getKey())) {
        element.setValue(element.getValue() + 1);
        foundKey = true;
        //if the key doesn't exist in freqList add new association elem to freqList with value of 1
      }
    }
    if (!foundKey) {
      Association<String, Integer> elem = new Association<String, Integer>(ch, 1);
      freqList.add(elem);
    }
  }


  /** Selects a character from this FrequencyList
  * @return a character from the FrequencyList with probabality equal to its relative frequency
  */
  public String choose() {
    Random rand = new Random();
    Vector<String> charsList = new Vector<String>();
    for (int i = 0; i < freqList.size(); i++) {
      Association<String, Integer> ch = freqList.get(i);
      String character = ch.getKey();
      int number = ch.getValue();

      for (int j = 0; j < number; j++) {
        //append char to charsList number times
        charsList.add(character);
      }
    }

    int randIndex = rand.nextInt(charsList.size());
    return charsList.get(randIndex);

  }

  /** Produce a string representation of the FrequencyList
  * @return a String representing the FrequencyList
  */
  public String toString() {
    String  result = "";
    for (int g = 0; g < freqList.size(); g++) {
      Association<String, Integer> ch = freqList.get(g);
      String character = ch.getKey();
      int number = ch.getValue();


      result += "[ " + character + ": " + number + " ] ";
    }
    return result;
  }

  // Use main to test your FrequencyList class
  public static void main(String[] args) {
    FrequencyList test = new FrequencyList();
    test.add("r");
    test.add("r");
    test.add("j");
    test.add("c");
    test.add("r");
    test.add("r");
    String chosenChar = test.choose();
    String result = test.toString();
    System.out.println(result);

  }

}
