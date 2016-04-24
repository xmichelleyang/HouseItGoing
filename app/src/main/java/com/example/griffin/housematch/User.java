package com.example.griffin.housematch;

import java.io.Serializable;

public class User extends Properties implements Serializable {
    int[] primes = {2, 3, 5, 7, 11, 13};

    //Constructor
    User(float total_bedrooms, float open_bedrooms, float bathrooms, float distance,
         float max_price, int sqft, int[] priority) {
        super(total_bedrooms, open_bedrooms, bathrooms, distance, max_price,
                sqft, priority);
        this.type = "User";
    }

    public float rank(House house) {

        if (house.distance > this.distance || house.max_price > this.max_price || this.sqft > house.sqft) {
            return 0;
        }

        float sum = 0;
        sum += this.weight(this.priority[0]) * 1.0 / (1 + (Math.abs(house.total_bedrooms - this.total_bedrooms)));
        sum += this.weight(this.priority[1]) * 1.0 / (1 + (Math.abs(house.open_bedrooms - this.open_bedrooms)));
        sum += this.weight(this.priority[2]) * (house.bathrooms - this.bathrooms);
        sum += this.weight(this.priority[4]) * 1.0 / (1 + (house.distance));
        sum += this.weight(this.priority[3]) * 1.0 / (1 + ((house.max_price) / 1000));
        sum += this.weight(this.priority[5]) * ((float) (house.sqft - this.sqft) / 200);

        return sum;
    }

    public int weight(int priority) {
        return primes[6 - priority];
    }
}
