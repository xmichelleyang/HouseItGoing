public class User extends Properties {
   int[] primes = {2, 3, 5, 7, 11, 13};
   //Constructor
   User (int total_bedrooms, int open_bedrooms, int bathrooms, float distance,
         float max_price, int sqft, int[] priority) {
            super(total_bedrooms, open_bedrooms, bathrooms, distance, max_price,
            sqft, priority);
            this.type = "User";
   }

   public int rank (House house) {
      int sum = 0;
      sum += this.weight(this.priority[0]) * 1/ (1 + (Math.abs(house.total_bedrooms - this.total_bedrooms)));
      sum += this.weight(this.priority[1]) * (Math.abs(house.open_bedrooms - this.open_bedrooms));
      sum += this.weight(this.priority[2]) * (Math.abs(house.bathrooms - this.bathrooms));
      sum += this.weight(this.priority[4]) * (Math.abs(house.distance - this.distance));
      sum += this.weight(this.priority[3]) * (Math.abs(house.max_price - this.max_price));
      sum += this.weight(this.priority[5]) * (Math.abs(house.sqft - this.sqft));
      //max is not 0 but if we do1/ans then it's flipped
   }

   public int weight (int priority) {
      return primes[6 - priority];
   }
}
