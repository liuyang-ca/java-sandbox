package leetcode.first50;

import org.junit.Test;

import java.util.*;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int area = 0;
        int iMax = height.length-1;
        int jMax = height.length;
        for(int i=0; i<iMax; i++) {
            for(int j=1; j<jMax; j++) {
                area = Math.max(area, (j-i)*Math.min(height[i], height[j]));
            }
        }
        return area;
    }

    public int maxArea2(int[] height) {
        int i=0, j=height.length-1;
        int area = 0;
        while(i != j) {
            area = Math.max(area, (j-i)*Math.min(height[i], height[j]));
            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return area;
    }


    @Test
    public void Sandbox() {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
