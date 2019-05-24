package leetcode.first50;

import java.util.ArrayList;
import java.util.List;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<Integer>();
        int i=0, j=0;
        while(true) {
            //System.out.println("i = " + i + ", j = " + j);
            if (i == nums1.length && j == nums2.length) {
                break;
            } else if (i == nums1.length) {
                list.add(nums2[j++]);
            } else if (j == nums2.length) {
                list.add(nums1[i++]);
            } else {
                if (nums1[i] < nums2[j]) {
                    list.add(nums1[i]);
                    i++;
                } else if (nums1[i] == nums2[j]) {
                    list.add(nums1[i]);
                    list.add(nums1[i]);
                    i++;
                    j++;
                } else {
                    list.add(nums2[j]);
                    j++;
                }
            }
        }
        //System.out.println(list);
        int size = list.size();
        if (size%2 == 0) {
            return (list.get(size/2-1) + list.get(size/2))/2.0;
        } else {
            return list.get(size/2);
        }
    }
}
