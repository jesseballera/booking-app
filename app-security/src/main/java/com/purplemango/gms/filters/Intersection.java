package com.purplemango.gms.filters;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

//    Get the intersection of 3 sorted arrays.
//
//    For example, given
//    arr1 = [1, 2, 3, 4, 5, 6], arr2 = [2, 4, 6, 8, 10], arr3 = [1, 4, 6, 7, 10], return [4, 6] since 4 and 6 show up in all 3 arrays.
//
//            Please try to use the most efficient way to do it and not libraries but only indices.
//
//            intersections(int[] arr1, int[] arr2, int[] arr3) {
//    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {2, 4, 6, 8, 10};
        int[] arr3 = {1, 4, 6, 7, 10};

        List<Integer> common = intersections(arr1, arr2, arr3);

        for (Integer num : common) {
            System.out.print(num + " ");
        }
    }

    public static List<Integer> intersections(int[] arr1, int[] arr2, int[] arr3) {

        int i = 0;
        int j = 0;
        int k = 0;

        List<Integer> result = new ArrayList<>();

        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {

                result.add(arr1[i]);
                i++;
                j++;
                k++;
                // skip duplicates elements in arr1[]
                while (i < arr1.length && arr1[i] == arr1[i-1]) {
                    i++;
                }

                // skip duplicates elements in arr2[]
                while (j < arr2.length && arr2[j] == arr2[j-1]) {
                    j++;
                }

                // skip duplicates elements in arr3[]
                while (k < arr3.length && arr3[k] == arr3[k-1]) {
                    k++;
                }
                // if arr1[i] < arr2[j], then element cannot be common
            } else if (arr1[i] < arr2[j]) {
                i++;
                // if arr2[j] < arr3[k], then element cannot be common
            } else if (arr2[j] < arr3[k]) {
                j++;
                // if arr3[k] is smallest, then kth element be common
            } else {
                k++;
            }
        }
            return result;
    }
}
