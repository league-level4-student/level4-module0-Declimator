//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	int prevdirection = 4;
    	int direction = 4;
    	int[][] cdirection = new int[4][3];
        for(int i = 0; i < field.length; i++) {
        	for(int j = 0; j < field[i].length; j++) {
        		if(field[i][j] == 'c') {
        			if(i+1 < field.length && field[i+1][j] == 'o') {
        				cdirection[1][0]++;
        				cdirection[1][1] = i;
        				cdirection[1][2] = j;
        			} else if(i > 0 && field[i-1][j] == 'o') {
        				cdirection[3][0]++;
        				cdirection[3][1] = i;
        				cdirection[3][2] = j;
        			} else if(j > 0 && field[i][j-1] == 'o') {
        				cdirection[0][0]++;
        				cdirection[0][1] = i;
        				cdirection[0][2] = j;
        			} else if(j+1 < field[i].length && field[i][j+1] == 'o') {
        				cdirection[2][0]++;
        				cdirection[2][1] = i;
        				cdirection[2][2] = j;
        			}
        			
        		}
        	}
        }
        for(int i = 0; i < 4; i++) {
        	for(int j = 0; j < 3; j++) {
        		System.out.print(cdirection[i][j]);
        	}
        	System.out.println("");
        }
        for(int i = 0; i < 4; i++) {
        	if(cdirection[i][0] == 1) {
        		int[] r = {cdirection[i][2], cdirection[i][1]};
        		System.out.println(r[0] + " " + r[1]);
        		return r;
        	}
        }
        return null;
    }
}
