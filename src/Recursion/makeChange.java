package Recursion;

import java.util.ArrayList;

public class makeChange {

	//public static ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
	//public static ArrayList<Integer> set=new ArrayList();
	public static int makeChange(int n, int denom){
		int next_denom=0;
		switch(denom){
		case 25:
			next_denom=10;
			break;
		case 10:
			next_denom=5;
			break;
		case 5:
			next_denom=1;
			break;
		case 1:
			return 1;
		}
		
		int ways=0;
		for(int i=0;i*denom<=n;i++){
				ways =ways+makeChange(n-i*denom, next_denom);
		}
	//	results.add(set);
		return ways;
	}
	
	public static void main(String[] args) {
		//makeChange(5,25);
		System.out.println(makeChange(5,25));
		//System.out.println(results);
	}

}
