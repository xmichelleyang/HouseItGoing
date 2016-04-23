

public class House extends Properties {
   String address;

   //Constructor
   House (String address, int total_bedrooms, int open_bedrooms, int bathrooms, float distance,
         float max_price, int sqft) {
            super(total_bedrooms, open_bedrooms, bathrooms, distance, max_price, sqft, null);
            this.address = address;
            this.type = "House";
   }


   }
