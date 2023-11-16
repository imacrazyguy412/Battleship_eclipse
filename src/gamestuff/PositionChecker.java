package gamestuff;

public class PositionChecker {
	
	public static Position checkPosition(String inputPos) {
		int col = 0;
		char row = 'A';
		if(inputPos.length() == 0) {
			System.out.println("Blank line");
			return new Position(0, 0);
		}  else if(inputPos.length() >4) {
			System.out.println("Wrong format, must have at most 4 characters");
			return new Position(0, 0);
		} else {
			try {
				col = Integer.parseInt(inputPos.substring(2));
			} catch(Exception e) {
				System.out.println("Column that is not a number.");
				return new Position(0, 0);
			}
			row = inputPos.charAt(0);
			
			if(inputPos.substring(1,2).equals("-")) {
				
			} else {
				System.out.println("No hyphen");
				return new Position(0, 0);
			}
			if(Character.isDigit(row)) {
				System.out.println("Row that is not a letter.");
				return new Position(Character.getNumericValue(row), col);
				//return new Position(0, 0);
			}
		}
		//range checker
		boolean inRange = true;
			if(Character.compare(row, 'a')>9) {
				System.out.println("row out of range");
				inRange = false;
				}
			if(col > 10 || col <= 0 && col != -1) {
				System.out.println("column out of range");
				inRange = false;
			}
			if(inRange) {	
			return new Position(row, col);
			} else {
				return new Position(0, 0);
			}
	}
}