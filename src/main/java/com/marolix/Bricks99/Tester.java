package com.marolix.Bricks99;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tester {

	public static void main(String[] args) {
		int sum = 10;
		List<Integer> elements = new ArrayList<Integer>();
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<List<Integer>> arrayList = new ArrayList<>();
		for (int i = 1; i < sum; i++) {
			for (int j = i + 1; j < sum; j++) {
				if (i + j == sum) {

					elements.add(i);
					elements.add(j);
//					System.out.println(elements);
					arrayList.add(elements);
					// elements.clear()
					elements = new ArrayList<Integer>();

				}

			}

		}
		
		System.out.println(arrayList);
//		Set<Map.Entry<Integer, Integer>> maps = map.entrySet();
//		System.out.println(maps);
//		for (Map.Entry<Integer, Integer> entry : maps) {
//			elements.add(entry.getKey());
//			elements.add(entry.getValue());
//
//		}
//		arrayList.add(elements);
//		System.out.println(arrayList);
	}

}
