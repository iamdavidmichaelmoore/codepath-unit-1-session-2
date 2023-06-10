package com.dmclabs.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KthLargest {
    private static final Logger LOGGER = Logger.getLogger(KthLargest.class.getName());


    private List<Integer> list;
    private int k;
    
    public KthLargest(int k, List<Integer> numbers) {
        this.k = k;
        list = numbers;
    }
   

    public int add(int val) {
        list.add(val);
        return findK(list,k);
    }
    
    // QUICKSELECT *********************************************************************************
    //NOTE - findK(): Unnecessary use of a comparator.
    private int findK(List<Integer> arr, int kTH, Comparator<Integer> cmp) {
        int left = 0;
        int right = arr.size() - 1;
        Random r = new Random(0);
        int result = 0;
        while (left <= right) {
            int pivotIndex = r.nextInt(right - left + 1) + left;
            int newPivotIndex = partitionAroundPivot(left, right, pivotIndex, arr, cmp);

            if (newPivotIndex == arr.size() - kTH) {
                result = arr.get(newPivotIndex);
                LOGGER.log(Level.INFO, "{0}","case 1");
                break;
            } else if (newPivotIndex > arr.size() - kTH) {
                LOGGER.log(Level.INFO, "{0}", "case 2");
                right = newPivotIndex - 1;
            } else {
                LOGGER.log(Level.INFO, "{0}", "case 3");
                left = newPivotIndex + 1;
            }
        }
        return result;
    }

    //NOTE - findK() without comparator
    private int findK(List<Integer> arr, int kTH) {
        int left = 0;
        int right = arr.size() - 1;
        Random r = new Random(0);
        int result = 0;
        while (left <= right) {
            int pivotIndex = r.nextInt(right - left + 1) + left;
            int newPivotIndex = partitionAroundPivot(left, right, pivotIndex, arr);

            if (newPivotIndex == arr.size() - kTH) {
                result = arr.get(newPivotIndex);
                break;
            } else if (newPivotIndex > arr.size() - kTH) {
                right = newPivotIndex - 1;
            } else {
                left = newPivotIndex + 1;
            }
        }
        return result;
    }

    //NOTE - partitionAroundPivot() without comparator
    private int partitionAroundPivot(int left, int right, int pivotIndex, List<Integer> arr) {
        int pivotValue = arr.get(pivotIndex);
        int newPivotIndex = left;

        swap(arr, pivotIndex, right); // why is this here?
        for (int i = left; i < right; ++i) {
            if (arr.get(i) < pivotValue) {
                swap(arr, i, newPivotIndex++);
            }
        }
        swap(arr, right, newPivotIndex);
        return newPivotIndex;
    }

    //NOTE - partitionAroundPivot(): Unnecessary use of a comparator!
    private int partitionAroundPivot(int left, int right, int pivotIndex, List<Integer> arr, Comparator<Integer> cmp) {
        int pivotValue = arr.get(pivotIndex);
        int newPivotIndex = left;

        Collections.swap(arr, pivotIndex, right);
        for (int i = left; i < right; ++i) {
            if (cmp.compare(arr.get(i), pivotValue) < 0) {
                Collections.swap(arr, i, newPivotIndex++);
            }
        }
        Collections.swap(arr, right, newPivotIndex);
        return newPivotIndex;
    }

    // QUICKSELECT *********************************************************************************
    private int quickSelect(List<Integer> array, int kth)  {
       int left = 0;
       int right = array.size() - 1;
       while (left <= right) {
           int pivot = quickSelectPartition(array, left, right);

           if (pivot == kth - 1) {
            return array.get(pivot);
          } else if (pivot < kth - 1) {
            return quickSelect(array, pivot - 1);
          } else {
            return quickSelect(array, pivot + 1);
          }
       }
       return -1;
    }

    private int quickSelectPartition(List<Integer> array, int left, int right) {
        int pivotValue = array.get(left);
        int pivotIndex = left;
        
            swap(array, left, right);
            for (int i = left; i < right; ++i) {
                if (array.get(i) > pivotValue) {
                    swap(array, i, pivotIndex++);
                }
            }
        swap(array, right, pivotIndex);
        return pivotIndex;
    }

    private void swap(List<Integer> array, int leftIndex, int rightIndex) {
        int temp = array.get(leftIndex);
        array.set(leftIndex, array.get(rightIndex));
        array.set(rightIndex, temp);
    }
    // QUICKSORT ***********************************************************************************
    private void quickSort(List<Integer> array, int start, int end ) {
        if (start < end) {
            int middle = quickSortPartition(array, start, end);
            // Subarray A[p..q-1]
            quickSort(array,start,middle - 1);
            // Subarray A[p + 1..r]
            quickSort(array,middle + 1,end);
        }
    }

    private int quickSortPartition(List<Integer> array, int start, int end) {
        // array = A; start = p; end = r
        // Selects an element as a pivot around which to partition the subarray.
        int element = array.get(end - 1); // -> x -> pivot
        int rightPartition = start; // -> j
        int leftPartition = start - 1; // -> i
        
        while (rightPartition < end - 1) {
            // LOOP PROPERTIES
            // 1. if p <= k <= i -> A[k] <= x
            // 2. if i + 1 <= k <= j-1 -> A[k] > x
            // 3. if k = r -> a[k] = x
            if (array.get(rightPartition) <= element) {
                // increment the left partition (pointer)
                leftPartition++;
                // and swap the values; store lesser element in the lesser partition
                swap(array, leftPartition, rightPartition);
            }
            rightPartition++; // increment the loop
        }
        // swap pivot with left-most element greater than itself
        swap(array, leftPartition + 1,  end - 1);
        // return the new pivot
        return leftPartition + 1;
    }

    Comparator<Integer> comparator = new Comparator<Integer>() {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 < o2) {
            return -1;
        } else if (o1.equals(o2)) {
            return 0;
        } else {
            return 1;
        }
    }
};

}
