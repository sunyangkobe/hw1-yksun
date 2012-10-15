import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Internal Use for measuring the correctness
 * 
 * @author Yang Sun
 * 
 */
public class CalcAccuracy {

  public CalcAccuracy(String args[]) {
    File sample = new File(args[0]);
    File mine = new File(args[1]);

    Map<String, ArrayList<String>> sampleMap = addToMap(sample);
    Map<String, ArrayList<String>> mineMap = addToMap(mine);

    double correctness = calcRate(sampleMap, mineMap);
    System.out.println("The correctness rate is " + correctness);
  }

  private double calcRate(Map<String, ArrayList<String>> sampleMap,
          Map<String, ArrayList<String>> mineMap) {
    double correct = 0.0;
    double total = 0.0;

    for (String key : sampleMap.keySet()) {
      if (!mineMap.containsKey(key)) {
        total += sampleMap.get(key).size();
      } else {
        for (String val : sampleMap.get(key)) {
          if (mineMap.get(key).contains(val)) {
            correct++;
            total++;
          } else {
            total++;
          }
        }
      }
    }

    for (String key : mineMap.keySet()) {
      if (!sampleMap.containsKey(key)) {
        total += mineMap.get(key).size();
      } else {
        for (String val : mineMap.get(key)) {
          if (sampleMap.get(key).contains(val)) {
            correct++;
            total++;
          } else {
            total++;
          }
        }
      }
    }
    System.out.println("Correct Number: " + correct);
    System.out.println("Total Number: " + total);
    return correct / total;
  }

  private Map<String, ArrayList<String>> addToMap(File file) {
    Map<String, ArrayList<String>> m = new HashMap<String, ArrayList<String>>();
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNext()) {
        String[] valueSplitted = scanner.nextLine().split("\\|");
        if (!m.containsKey(valueSplitted[0])) {
          m.put(valueSplitted[0], new ArrayList<String>());
        }
        m.get(valueSplitted[0]).add(valueSplitted[1]);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return m;
  }

  public static void main(String[] args) throws Exception {
    new CalcAccuracy(args);
  }

}
