package deeplearning;

import java.util.Scanner;

// 강의실 냉방 중앙제어 시스템
public class Fuzzy_Theory {
	
	// 최소값을 구하는 함수
	static double minimum(double a, double b, double c) {
		double minimum = (a < b && a < c) ? a : (b < c) ? b : c;
		
		return minimum;
	}
	
	// 온도 최소값
	static double TEMP_MIN(double x ) {
		if(x < 20) return 1;
		else if(x >= 20 && x < 25) return (25 - x) / 5;
		else return 0;
	}
	
	// 온도 중간값
	static double TEMP_MID(double x) {
		if(x > 20 && x<25) return (x - 20) / 5;
		else if(x >= 25 && x <= 30) return (30 - x) / 5;
		else return 0;
	}
	
	// 온도 최대값
	static double TEMP_MAX(double x) {
		if (x > 25 && x < 30) return (x - 25) / 5;
		else if (x >= 30) return 1;
		else return 0;
	}

	// 습도 최소값
	static double HUMID_MIN(double x) {
		if (x <= 20) return 1;
		else if (x > 20 && x < 40) return (40 - x) / 20;
		else return 0;
	}
	
	// 습도 중간값
	static double HUMID_MID(double x) {
		if (x > 20 && x < 40) return (x - 20) / 20;
		else if (x >= 40 && x <= 60) return 1;
		else if (x > 60 && x < 80) return (80 - x) / 20;
		else return 0;
	}
	
	// 습도 최대값
	static double HUMID_MAX(double x) {
		if (x > 60 && x < 80) return (x - 60) / 20;
		else if (x >= 80) return 1;
		else return 0;
	}
	
	// 학생 수 최소값
	static double STD_MIN(double x ) {
		if(x < 10) return 1;
		else if(x >= 10 && x < 20) return (20 - x) / 10;
		else return 0;
	}
	
	// 학생 수 중간값
	static double STD_MID(double x) {
		if(x > 10 && x<20) return (x - 10) / 10;
		else if(x >= 20 && x <= 30) return (30 - x) / 10;
		else return 0;
	}
	
	// 학생 수 최대값
	static double STD_MAX(double x) {
		if (x > 20 && x < 30) return (x - 20) / 10;
		else if (x >= 30) return 1;
		else return 0;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("#########강의실 정보 입력#########");
		System.out.println("------------------------------");
		System.out.print("현재 온도 : ");
		double temp = sc.nextDouble(); // 현재 온도 입력
		System.out.print("현재 습도 : ");
		double humidity = sc.nextDouble(); // 현재 습도 입력
		System.out.print("강의실 학생 수 : ");
		double std = sc.nextDouble(); // 강의실 학생 수 입력
		System.out.println("------------------------------");
		
		double molecular; // 분자
		double denominator; // 분모
		
		double[] tempArr = { TEMP_MIN(temp),TEMP_MID(temp),TEMP_MAX(temp) }; // 온도 그래프
		double[] humidArr = { HUMID_MIN(humidity),HUMID_MID(humidity),HUMID_MAX(humidity) }; // 습도 그래프
		double[] stdArr = { STD_MIN(std),STD_MID(std),STD_MAX(std) }; // 강의실 학생 수 그래프
		
		molecular = // 냉방기 출력 0%
				    (minimum(tempArr[0],humidArr[0],stdArr[0]) + minimum(tempArr[0],humidArr[0],stdArr[1]) + 
				     minimum(tempArr[0],humidArr[0],stdArr[2]) + minimum(tempArr[1],humidArr[0],stdArr[0]) + 
				     minimum(tempArr[1],humidArr[0],stdArr[1])) * 0 +
				    // 냉방기 출력 20%
				    (minimum(tempArr[0],humidArr[1],stdArr[0]) + minimum(tempArr[0],humidArr[1],stdArr[1]) + 
				     minimum(tempArr[0],humidArr[1],stdArr[2]) + minimum(tempArr[1],humidArr[0],stdArr[2])) * 20 +
				    // 냉방기 출력 40%
				    (minimum(tempArr[0],humidArr[2],stdArr[0]) + minimum(tempArr[0],humidArr[2],stdArr[1]) + 
				     minimum(tempArr[1],humidArr[1],stdArr[0]) + minimum(tempArr[1],humidArr[1],stdArr[1]) + 
				     minimum(tempArr[1],humidArr[1],stdArr[2])) * 40 +
				    // 냉방기 출력 60%
				    (minimum(tempArr[0],humidArr[2],stdArr[2]) + minimum(tempArr[1],humidArr[2],stdArr[0]) + 
				     minimum(tempArr[1],humidArr[2],stdArr[1]) + minimum(tempArr[1],humidArr[2],stdArr[2])) * 60 +
				    // 냉방기 출력 80%
				    (minimum(tempArr[2],humidArr[0],stdArr[0]) + minimum(tempArr[2],humidArr[0],stdArr[1]) + 
				     minimum(tempArr[2],humidArr[0],stdArr[2]) + minimum(tempArr[2],humidArr[1],stdArr[0]) + 
				     minimum(tempArr[2],humidArr[1],stdArr[1])) * 80 +
				    // 냉방기 출력 100%
				    (minimum(tempArr[2],humidArr[1],stdArr[2]) + minimum(tempArr[2],humidArr[2],stdArr[0]) + 
				     minimum(tempArr[2],humidArr[2],stdArr[1]) + minimum(tempArr[2],humidArr[2],stdArr[2])) * 100;

		denominator = // 냉방기 출력 0%
					  minimum(tempArr[0],humidArr[0],stdArr[0]) + minimum(tempArr[0],humidArr[0],stdArr[1]) + 
					  minimum(tempArr[0],humidArr[0],stdArr[2]) + minimum(tempArr[1],humidArr[0],stdArr[0]) + 
					  minimum(tempArr[1],humidArr[0],stdArr[1]) +
					  // 냉방기 출력 20%
					  minimum(tempArr[0],humidArr[1],stdArr[0]) + minimum(tempArr[0],humidArr[1],stdArr[1]) + 
					  minimum(tempArr[0],humidArr[1],stdArr[2]) + minimum(tempArr[1],humidArr[0],stdArr[2]) +
					  // 냉방기 출력 40%
					  minimum(tempArr[0],humidArr[2],stdArr[0]) + minimum(tempArr[0],humidArr[2],stdArr[1]) + 
					  minimum(tempArr[1],humidArr[1],stdArr[0]) + minimum(tempArr[1],humidArr[1],stdArr[1]) + 
					  minimum(tempArr[1],humidArr[1],stdArr[2]) +
					  // 냉방기 출력 60%
					  minimum(tempArr[0],humidArr[2],stdArr[2]) + minimum(tempArr[1],humidArr[2],stdArr[0]) + 
					  minimum(tempArr[1],humidArr[2],stdArr[1]) + minimum(tempArr[1],humidArr[2],stdArr[2]) +
					  // 냉방기 출력 80%
					  minimum(tempArr[2],humidArr[0],stdArr[0]) + minimum(tempArr[2],humidArr[0],stdArr[1]) + 
					  minimum(tempArr[2],humidArr[0],stdArr[2]) + minimum(tempArr[2],humidArr[1],stdArr[0]) + 
					  minimum(tempArr[2],humidArr[1],stdArr[1]) +
					  // 냉방기 출력 100%
					  minimum(tempArr[2],humidArr[1],stdArr[2]) + minimum(tempArr[2],humidArr[2],stdArr[0]) + 
					  minimum(tempArr[2],humidArr[2],stdArr[1]) + minimum(tempArr[2],humidArr[2],stdArr[2]);
				
		System.out.println("#########강의실 냉방 정보#########");
		System.out.println("------------------------------");
		System.out.println("냉방기 출력 : " + Math.round(molecular / denominator) + "%");
		System.out.println("------------------------------");
	}
}
