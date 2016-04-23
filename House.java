

public class House extends Properties {

   //Constructor
   House (int total_bedrooms, int open_bedrooms, int bathrooms, float distance,
         float max_price, int sqft) {
            super(total_bedrooms, open_bedrooms, bathrooms, distance, max_price, sqft, null);
            this.type = "House";
   }

}
