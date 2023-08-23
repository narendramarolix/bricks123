package com.leetCode;

import java.util.ArrayList;
import java.util.Iterator;

class Solution {
	public int[] twoSum(int[] nums, int target) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					a.add(i);
					a.add(j);
				}
			}
		}
		int[] a1 = new int[a.size()];
		int index = 0;
		for (Integer i : a) {
			a1[index] = i;
			index++;
		}
		return a1;
	}
}

public class TwoSumQ1 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] a = s.twoSum(new int[] { 1, 2, 3, 4, 5,6,7,8 }, 8);
		for (int i : a) {
			System.out.println(i);
		}
	}

}
