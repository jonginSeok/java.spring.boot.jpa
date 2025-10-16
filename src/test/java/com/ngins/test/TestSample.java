/**
 * 
 */
package com.ngins.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 
 */
public class TestSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// stream 의 사용예 
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Integer sum = numbers.stream()
		    .filter(x -> x % 2 == 0)
		    .map(x -> x * x)
		    .filter(x -> x > 10)
		    .reduce(0, Integer::sum)
		    ;
		
		System.out.println(sum); // 출력: 216
		/**
		   ✅ 단계별 결과
			1. filter(x -> x % 2 == 0) → [2, 4, 6, 8, 10] 
			2. map(x -> x * x) → [4, 16, 36, 64, 100]
			3. filter(x -> x > 10) → [16, 36, 64, 100]
			4. reduce(0, Integer::sum) → 216
		 */

		// Collection 의 사용예
		Collection<String> fruits = new ArrayList<>();
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Orange");

		System.out.println(fruits.contains("Banana")); // true
		System.out.println(fruits.size());             // 3

		for (String fruit : fruits) {
		    System.out.println(fruit);
		}
		
		List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) list.add(i);

        int max = Collections.max(list); // 최대값
        System.out.println("MAX : " + max);
        
        int min = Collections.min(list); // 최소값
        System.out.println("MIN : " + min);
        
		/**
		   ✅ 요소 추가 및 제거
		  	boolean add(E e);           // 요소 추가
			boolean remove(Object o);   // 요소 제거
			void clear();               // 모든 요소 제거
			
		   🔍 조회 및 검사
			boolean contains(Object o);    // 특정 요소 포함 여부
			boolean isEmpty();             // 비어 있는지 확인
			int size();                    // 요소 개수
			
		   🔁 반복 및 변환
			Iterator<E> iterator();        // 반복자 반환
			Object[] toArray();            // 배열로 변환
			<E> T[] toArray(T[] a);        // 타입 지정 배열로 변환
			
		   🔄 집합 연산
			boolean containsAll(Collection<?> c); // 모든 요소 포함 여부
			boolean addAll(Collection<? extends E> c); // 여러 요소 추가
			boolean removeAll(Collection<?> c); // 여러 요소 제거
			boolean retainAll(Collection<?> c); // 교집합 유지
		 
		   🧠 특징 요약
			• 	인터페이스이므로 직접 인스턴스화 불가 → 구현 클래스 사용 필요
			• 	제네릭 지원 → 타입 안정성 확보
			• 	다형성 활용 가능 → 다양한 구현체를 하나의 타입으로 처리
			• 	컬렉션 간 연산 가능 → AddAll, retainAll, removeAll 등
		 */

	}

}
