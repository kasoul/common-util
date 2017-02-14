package com.superh.hz.test.intellgames;

public class NineGridPalace {

	public static void main(String[] args) {
		String[][] nineGridPalace = new String[][]{
			new String[]{" "," "," "," "," "," "," "," "," "},
			new String[]{"6","2"," "," "," "," ","7"," "," "},
			new String[]{" "," ","8"," ","4","9"," "," "," "},
			new String[]{" "," ","4","5","8"," "," "," "," "},
			new String[]{"7"," "," "," "," "," ","6"," ","2"},
			new String[]{" "," "," "," ","9"," "," "," "," "},
			new String[]{" "," "," "," "," "," "," ","8"," "},
			new String[]{"1"," "," "," "," ","6"," "," "," "},
			new String[]{" "," "," "," "," "," "," ","5"," "},
			};
		
		int num = 1;
		while(true){
			String number = String.valueOf(num);
			convert2SparseMatrix(String.valueOf(number),nineGridPalace);
			boolean flag = setUnique(number,nineGridPalace);
			resetNineGridPalace(nineGridPalace);
		
			int blankCount = 0;
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					if(nineGridPalace[i][j].equals(" "))
						blankCount++;
				}
			}
			
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					System.out.print(nineGridPalace[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("-------------------------------------------------");
			
			if(blankCount == 0){
				break;
			}else if(flag){
				;
			}else if(num == 9){
				num = 1;
			}else{
				num++;
			}
		}

	}
	
	public static void convert2SparseMatrix(String number, String[][] nineGridPalace){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(nineGridPalace[i][j].equals(number)){
					for(int in=0; in<9; in++){
						if(nineGridPalace[in][j].equals(" "))
						nineGridPalace[in][j] = "N";
					}
					for(int jn=0; jn<9; jn++){
						if(nineGridPalace[i][jn].equals(" "))
						nineGridPalace[i][jn] = "N";
					}
					if(i==j){
						for(int index=0; index<9; index++){
							if(nineGridPalace[index][index].equals(" "))
							nineGridPalace[index][index] = "N";
						}
					}
					if(i==8-j){
						for(int index=0; index<9; index++){
							if(nineGridPalace[index][8-index].equals(" "))
							nineGridPalace[index][8-index] = "N";
						}
					}
				}
			}
		}
	}
	
	public static boolean setUnique(String number, String[][] nineGridPalace){
		int uniqueCount = 0;
		for(int childIndex=0; childIndex<9; childIndex++){
			int childGridPalaceRow = (childIndex / 3) * 3;
			int childGridPalaceColunm = (childIndex % 3) * 3;
			int blankCount = 0;
			int numberCount = 0;
			int uniqueRowIndex = -1;
			int uniqueColunmIndex = -1;
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(nineGridPalace[childGridPalaceRow + i][childGridPalaceColunm + j].equals(number)){
						numberCount++;
					}
					if(nineGridPalace[childGridPalaceRow + i][childGridPalaceColunm + j].equals(" ")){
						uniqueRowIndex = childGridPalaceRow + i;
						uniqueColunmIndex = childGridPalaceColunm + j;
						blankCount++;
					}
				}
			}
			
			if(numberCount >= 1){
				continue;
			}
			if(blankCount == 1){
				nineGridPalace[uniqueRowIndex][uniqueColunmIndex] = number;
				uniqueCount++;
			}
		}
		
		if(uniqueCount>0){
			return true;
		}else{
			return false;
		}
	}
	
	public static void resetNineGridPalace(String[][] nineGridPalace){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(nineGridPalace[i][j].equals("N")){
					nineGridPalace[i][j] = " ";
				}
			}
		}
	}

}
