package com.example.griffin.housematch;

import android.content.Context;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.File;
import java.io.FileOutputStream;

/**
 * A House object signifying the properties of a house to be listed.
 * Can also be appended onto a CSV file
 **/
public class House extends Properties implements Serializable {
    String address;
    String email;
    //Constructor
    House (String address, float total_bedrooms, float open_bedrooms, float bathrooms,
         float distance, float max_price, int sqft, String email) {
            super(total_bedrooms, open_bedrooms, bathrooms, distance,
                  max_price, sqft, null);
            this.address = address;
            this.email = email;
            this.type = "House";
    }

    /**
    * Saves the house listing onto a CSV File
    */
    public String save () {

        return address + "," + total_bedrooms + "," + open_bedrooms + ","
                + bathrooms + "," + distance + "," + max_price + ","
                + sqft + "," + email + "\n";
//          FileWriter pw;
//          try {
//              pw = new FileWriter("../../../../../res/housingTEST.csv", true);
//
//              pw.append(this.address);
//              pw.append(",");
//              pw.append(Float.toString(total_bedrooms));
//              pw.append(",");
//              pw.append(Float.toString(open_bedrooms));
//              pw.append(",");
//              pw.append(Float.toString(bathrooms));
//              pw.append(",");
//              pw.append(Float.toString(distance));
//              pw.append(",");
//              pw.append(Float.toString(max_price));
//              pw.append(",");
//              pw.append(Integer.toString(sqft));
//              pw.append(",");
//              pw.append(this.email);
//              pw.append('\n');
//
//              System.out.println("added house");
//
//              pw.flush();
//              pw.close();
//          } catch(IOException e) {
//              System.out.println(System.getProperty("user.dir"));
//          }

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
