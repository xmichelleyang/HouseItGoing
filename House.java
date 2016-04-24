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


public class House extends Properties {
   String address;
   //Constructor
   House (String address, int total_bedrooms, int open_bedrooms, int bathrooms, float distance,
         float max_price, int sqft) {
            super(total_bedrooms, open_bedrooms, bathrooms, distance, max_price, sqft, null);
            this.address = address;
            this.type = "House";
   }
   public void save (House house)  throws IOException{

      FileWriter pw = new FileWriter("housing.csv", true); //true for append

      System.out.println(""); //new line
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
