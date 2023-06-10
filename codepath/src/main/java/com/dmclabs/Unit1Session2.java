package com.dmclabs;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dmclabs.utils.KthLargest;

/**
 * Hello world!
 *
 */
public class Unit1Session2 
{
    private static final Logger LOGGER = Logger.getLogger(Unit1Session2.class.getName());
    public static void main( String[] args)
    {

        KthLargest largest = new KthLargest(3, new ArrayList<>(Arrays.asList(4,5,8,2)));
        int[] numbers = {3,5,10,9,4};
        for (int number : numbers) {
            LOGGER.log(Level.INFO, "Output: {0}", largest.add(number));
        }
        
    }


}
