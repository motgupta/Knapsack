import java.util.ArrayList;
import java.util.List;

public class Knapsack {

	
	public static void main(String[] args) {
	
		int[] weight = {60, 30, 55, 30, 70, 48};
		int[] value = {60, 30, 55, 30, 70, 48};
		
		// in actual 2 dimensional knapsack problem there will be some value
		// associate with wieight/inputs like below
		// int[] value = { 1, 4, 5, 7, 5, 8 }
		
		int targetSum = 180;

		knapsack(weight, value, targetSum);

	}

	public static void knapsack(int[] weight, int[] value, int targetSum) {

		int[][] weightValMatrix = new int[weight.length + 1][targetSum + 1];

		for (int i = 0; i < weight.length; i++) {
			for (int k = 0; k < targetSum + 1; k++) {
				weightValMatrix[i][k] = 0;
			}

		}

		for (int i = 1; i < weight.length + 1; i++) {
			for (int k = 1; k < targetSum + 1; k++) {
				if (k < weight[i - 1]) {
					weightValMatrix[i][k] = weightValMatrix[i - 1][k];
				} else {
					int valueInclusiveCurrentWeight = value[i - 1];
					if ((k - weight[i - 1]) > 0) {
						valueInclusiveCurrentWeight = valueInclusiveCurrentWeight
								+ weightValMatrix[i - 1][k - weight[i - 1]];
					}

					int valueExcludingCurrentWeight = weightValMatrix[i - 1][k];
					weightValMatrix[i][k] = valueInclusiveCurrentWeight >= valueExcludingCurrentWeight ? valueInclusiveCurrentWeight
							: valueExcludingCurrentWeight;

				}

			}

			

		}
		
		
		
		for (int i = 1; i < weight.length + 1; i++) {
			
			for (int k = 1; k < targetSum + 1; k++) {
				
				System.out.print(weightValMatrix[i][k]);
				
				if(k == targetSum){
					System.out.println("");
				}
			}
		}
		
		
		//System.out.println("final value is " + weightValMatrix[weight.length][targetSum]);
		
		List<Integer> finallySelectedWeightIndex = new ArrayList<Integer>();
		
		findActualWeightIndex(weightValMatrix, weight.length, targetSum, finallySelectedWeightIndex, weight);
		
		for(int index:finallySelectedWeightIndex){
			System.out.println("weight is " + weight[index-1] + " value is "+ value[index-1]);
		}
		

	}
	
	
	public static void findActualWeightIndex(int[][] weightValMatrix, int row, int column, 
			List<Integer> finallySelectedWeightIndex, int[] weight) {
		
		if(row==0 || column==0){
			return;
		}
		
		if(weightValMatrix[row][column]==weightValMatrix[row-1][column]){
			findActualWeightIndex(weightValMatrix, row-1, column, finallySelectedWeightIndex, weight);
		}else{
			finallySelectedWeightIndex.add(row);
			findActualWeightIndex(weightValMatrix, row-1, column - weight[row-1] , finallySelectedWeightIndex, weight);
		}
	}

}
