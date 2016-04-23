public class House extends Properties {

   //Constructor
   House (int total_bedrooms, int open_bedrooms, int bathrooms, float distance,
         float max_price, int sqft, int[] priority) {
            super(total_bedrooms, open_bedrooms, distance, max_price, sqft, priority);
            this.type = "House";
   }

}
