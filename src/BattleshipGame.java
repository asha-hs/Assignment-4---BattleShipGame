import java.util.Scanner;

public class BattleshipGame {



    public static void main(String[] args) {
	Ocean ocean = new Ocean();
	String play = "yes";
	Scanner sc = new Scanner(System.in);
	ocean.placeAllShipsRandomly();
	ocean.print();
	while(play.equals("yes")) {
	    System.out.println("Enter shots by mentioning row and column numbers");
	    System.out.println("The input format should look like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17");
	    
	    String[] str = sc.nextLine().split(";");

	    for(int i = 0; i < str.length; i++) {
		String[] shot = str[i].split(",");
		int row = Integer.parseInt(shot[0]);
		int col = Integer.parseInt(shot[1]);
		ocean.shootAt(row , col); 
		Ship s = ocean.getShips()[row][col];
		if(!s.getShipType().equals("") && s.getIsFired()) {
		    System.out.println("hit");
		}
		else {
		    System.out.println("miss");
		}
		if(s.isSunk()) {
		    System.out.println("You just sank a "+ s.getShipType());
		}
	    }
	    ocean.print();
	    System.out.println("Number of hits : "+ocean.getHitCount()+" Score : "+ocean.getShipsSunk());
	    if(ocean.getShipsSunk() == 13) {
		System.out.println("you shot all the ships!! Game over!!!!");
	    }
	    else {
		System.out.println("Want to play again, type yes / no");
		play = sc.next();
	    }
	    
	}
	sc.close();
    }

}
