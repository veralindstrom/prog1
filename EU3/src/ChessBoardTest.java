import java.util.Random;

public class ChessBoardTest {

	public static void main(String[] args) throws NotValidFieldException {
		// TODO Auto-generated method stub
		Chessboard chessboard = new Chessboard();
		Chessboard.Chesspiece[]  pieces = new Chessboard.Chesspiece[6];
		
		pieces[0] = chessboard.new Pawn ('w', 'P');
		pieces[1] = chessboard.new Rook ('b', 'R');
		pieces[2] = chessboard.new Knight ('w', 'N');
		pieces[3] = chessboard.new King ('b', 'K');
		pieces[4] = chessboard.new Queen ('w', 'Q');
		pieces[5] = chessboard.new Bishop ('w', 'B');
		
		
		
		System.out.println(chessboard);
		
		System.out.println("Pawn: wP"
				+ "\nRook: bR"
				+ "\nKnight: wN"
				+ "\nKing: bK"
				+ "\nQueen: wQ"
				+ "\nBishop: wB");
		
		Random r = new Random();
		char rowRandom = (char) (97 + r.nextInt(7)); //hittar random row mellan a-g
		byte columnRandom = (byte) (r.nextInt(7)); //hittar random kolumn 1-8
		
		long t = System.currentTimeMillis(); //current time i millisekunder lagras i t
		while (System.currentTimeMillis() - t < 1000); //väntar 5 sekunder
			
		for(int i = 0; i < pieces.length; i++) {
			
			//sätt pieces på random row-column och markera
			pieces[i].moveTo(rowRandom, columnRandom);
			pieces[i].markReachableFields(); //polymorphic -method-call
			
			//printa ut schackbrädan
			System.out.println(chessboard);
			
			//väntar 5 sekunder
			t = System.currentTimeMillis(); //current time i millisekunder lagras i t
			while (System.currentTimeMillis() - t < 1000); 
			
			//avmarkera och ta bort pieces
			pieces[i].unmarkReachableFields();
			pieces[i].moveOut();
		}
	}
}
