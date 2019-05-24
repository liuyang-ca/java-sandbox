package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
 * 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if(height.length <= 2) {
            return 0;
        }

        int low=0, tmp=0, result=0;
        int highest = 0;
        for(int i=0; i<height.length; i++) {
            if(height[i] >= height[low]) {
                if (tmp == 0) {
                    low = i;
                } else {
                    result = result + tmp;
                    tmp = 0;
                    low = i;
                }
            } else {
                tmp = tmp + height[low] - height[i];
            }

            if(highest < height[i]) {
                highest = height[i];
            }
        }

        MyLogger.LOGGER.debug("before {}", result);

        low = height.length-1;
        tmp = 0;
        for(int j=height.length-1; j>=0; j--) {
            if(height[j] >= height[low]) {
                if (tmp == 0) {
                    low = j;
                } else {
                    result = result + tmp;
                    tmp = 0;
                    low = j;
                }
            } else {
                tmp = tmp + height[low] - height[j];
            }

            if (height[j] == highest) {
                break;
            }
        }

        return result;
    }

    @Test
    public void Sandbox() {
        //System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{4, 2, 3}));
    }
}
