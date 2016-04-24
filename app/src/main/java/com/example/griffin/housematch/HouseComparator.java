package com.example.griffin.housematch;

import java.util.Comparator;

/**
 * Created by Alex on 4/24/16.
 */
public class HouseComparator implements Comparator<House> {
    public int compare(House house1, House house2) {
        if (house1.rank > house2.rank) {
            return 1;
        } else if (house1.rank == house2.rank) {
            return 0;
        } else {
            return -1;
        }
    }
}
