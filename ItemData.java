import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ItemData {
    final static String filePath = "D:/Training/Java/CaseStudy02/src/SnackItem.txt";
    private static Map<String, SnackItem> itemMap;

    static {
        try {
            itemMap = new HashMap<String, SnackItem>();
            BufferedReader br = null;
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");

                String status = parts[2];
                if (status.equals("A")) {
                    String name = parts[0];
                    String rate = parts[1];
                    String discountRate = parts[4];
                    String discountQty = parts[3];

                    SnackItem snack = new SnackItem(name, rate, status, discountRate, discountQty);
                    itemMap.put(name, snack);
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Map<String, SnackItem> getAllItems() {
        return itemMap;
    }

    public static SnackItem getItem(String itemName) {
        SnackItem tempSnackItem = itemMap.get(itemName);
        return tempSnackItem;
    }

    public static boolean isAvailable(String itemName) {
        boolean status = itemMap.containsKey(itemName);
        return status;
    }
}
