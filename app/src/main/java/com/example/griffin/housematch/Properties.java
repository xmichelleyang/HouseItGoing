package com.example.griffin.housematch;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Properties implements Serializable {

   float total_bedrooms;
   float open_bedrooms;
   float bathrooms;
   float distance;
   float max_price;
   int sqft;
   int[] priority = new int[6];
   String type;

   //Constructor
   Properties (float total_bedrooms, float open_bedrooms, float bathrooms,
         float distance, float max_price, int sqft, int[] priority) {
            this.total_bedrooms = total_bedrooms;
            this.open_bedrooms = open_bedrooms;
            this.bathrooms = bathrooms;
            this.distance = distance;
            this.max_price = max_price;
            this.sqft = sqft;

            if( priority != null ) {
               for (int i = 0; i < 6; i++){
                  this.priority[i] = priority[i];
               }
            }


   }

   void save (int slot) throws IOException {

      ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(
         new File(this.type + slot + ".sav")));
      writer.writeObject(this);
      writer.close();
   }

   public static Properties load (int index, String type) {
      Properties saveFile = null;
      try {
            ObjectInputStream reader = new ObjectInputStream(
               new FileInputStream(new File(type + Integer.toString(index) +
               ".sav")));

            saveFile = (Properties) reader.readObject();
            reader.close();
      } catch (IOException e) {
         System.err.println("Sorry, could not load file");
         System.exit(1);
      } catch (ClassNotFoundException e) {
         System.err.println("Sorry, corrupt or outdated save file");
         System.exit(1);
      }
      return saveFile;
   }

   public static void main(String[] args) throws IOException {
      /* int[] egers = {1,3,2,4,5,6};
      User user = new User (1,1,1,400, 400, 400, egers);
      House badhouse = new House(1,1,1, 300, 390, 401);
      House goodhouse = new House(1,1,1, 1, 300, 600);

      System.out.println(user.rank(badhouse));
      System.out.println(user.rank(goodhouse)); */
   }

}
