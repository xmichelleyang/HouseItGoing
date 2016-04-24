import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;

/**
 * A House object signifying the properties of a house to be listed.
 * Can also be appended onto a CSV file
 **/
public class House extends Properties {
   String address;
   //Constructor
   House (String address, int total_bedrooms, int open_bedrooms, int bathrooms,
         float distance, float max_price, int sqft) {
            super(total_bedrooms, open_bedrooms, bathrooms, distance,
                  max_price, sqft, null);
            this.address = address;
            this.type = "House";
   }

   /**
    * Saves the house listing onto a CSV File
    * @param House - house to be saved
    */
   public void save ()  throws IOException{

      FileWriter pw = new FileWriter("housingTEST.csv", true); //true for append

      pw.append(this.address);
      pw.append(",");
      pw.append(Integer.toString(total_bedrooms));
      pw.append(",");
      pw.append(Integer.toString(open_bedrooms));
      pw.append(",");
      pw.append(Integer.toString(bathrooms));
      pw.append(",");
      pw.append(Float.toString(distance));
      pw.append(",");
      pw.append(Float.toString(max_price));
      pw.append(",");
      pw.append(Integer.toString(sqft));
      pw.append('\n');


      pw.flush();
      pw.close();


      /*
      CSVWriter writer = new CSVWriter(new Writer(), ',');
      List <String> newhouseprop = new ArrayList<String>();

      newhouseprop.add(address);
      String prop = Integer.toString(total_bedrooms);
      newhouseprop.add(prop);
      prop = Integer.toString(open_bedrooms);
      newhouseprop.add(prop);
      prop = Integer.toString(bathrooms);
      newhouseprop.add(prop);
      prop = Float.toString(distance);
      newhouseprop.add(prop);
      prop = Float.toString(max_price);
      newhouseprop.add(prop);
      prop = Integer.toString(sqft);
      newhouseprop.add(prop);

      writer.writeAll(newhouseprop); */
   }
}
