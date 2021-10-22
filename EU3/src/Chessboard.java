public class Chessboard {
	
	public static class Field {   
		private char    row;
		private byte    column;
		private Chesspiece    piece = null;
		private boolean    marked = false;
		
		public Field (char row, byte column) {
			this.row = row;
			this.column = column;
		}
		
		public void put (Chesspiece piece) {
			this.piece = piece;
		}
		
		public Chesspiece take () {
			this.piece = null;
            return null;
		}
		
		public void mark () {
			this.marked = true;
		}
		
		public void unmark ()  {
			this.marked = false;
		}
		
		public String toString () {
			String    s = (marked) ? "xx" : "--"; //if (marked) s == "xx", annars "--"
			return (piece == null) ? s : piece.toString (); //if (piece == null) return s, annars piece.toString()
		}
	}
	
	public static final int    NUMBER_OF_ROWS = 8;
	public static final int    NUMBER_OF_COLUMNS = 8;
	
	public static final int    FIRST_ROW = 'a';
	public static final int    FIRST_COLUMN = 1;
	
	private Field[][]    fields;
	
	public Chessboard () {
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		char    row = 0;
		byte    column = 0;
		
		for (int r = 0; r < NUMBER_OF_ROWS; r++) {
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
			
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				fields[r][c] = new Field (row, column);
				column++;
			}	
		}
	}
	
	//presenterar schack-brädan tom
	public String toString () {
		
		StringBuilder field = new StringBuilder();
		
		field.append("    1  2  3  4  5  6  7  8\n");
		
		for(int r = 0; r < 8; r++) {
			field.append((char) (r + FIRST_ROW) + " ");
			
			
			for(int c= 0; c <8; c++) {
				field.append(" " + fields [r][c]);
				if(c==7)
					field.append("\n");
			}
		}
		
		return field.toString();
	}
	
	//checkar om fält är möjligt
	public boolean isValidField (char row, byte column) {
		boolean isValid = false;
		
		if (row < 105 && row > 96 && column < 9 && column >= 0) //om row är a-h
															//och om column är 1-8
				isValid = true; //är field valid
		
		return isValid;
	}
	
	public abstract class Chesspiece {
		private char    color;
		// w -white, b -black
		private char    name;
		// K -King, Q -Queen, R -Rook, B -Bishop, N -Knight,
		// P –Pawn
		
		protected char    row = 0;
		protected byte    column = -1;
		
		protected Chesspiece (char color, char name) {
			this.color = color;
			this.name = name;
			isOnBoard();
		}
		
		public String toString () {
			return "" + color + name;
		}
		
		public boolean isOnBoard () {
			return Chessboard.this.isValidField (row, column);
		}
		
		public void moveTo (char row, byte column) 
				throws NotValidFieldException {
			if (!Chessboard.this.isValidField (row, column))
				throw new NotValidFieldException ("bad field: " + row + column );
			
			this.row = row;
			this.column = column;
			
			int    r = row -FIRST_ROW;
			int    c = column -FIRST_COLUMN;
			Chessboard.this.fields[r][c].put (this);
		}
		
		public void moveOut () {
			int    r = this.row -FIRST_ROW;
			int    c = this.column -FIRST_COLUMN;
			
			Chessboard.this.fields[r][c].take();
		}
		
		public abstract void markReachableFields ();
		
		public abstract void unmarkReachableFields ();
	}
	
	public class Pawn extends Chesspiece {
		public Pawn (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			byte    col = (byte) (column + 1); //förlyttas per column
			if (Chessboard.this.isValidField (row, col)) { //om reachble är inom schackbrädan
				int    r = row - FIRST_ROW;
				int    c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
		}

		public void unmarkReachableFields () { 
			byte    col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col)) {
				int    r = row - FIRST_ROW;
				int    c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark (); //avmarkera platsen ^
			}
		}
	}

	public class Rook extends Chesspiece {
		public Rook (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			//markera hela columnen genom att gå igenom alla rader
			for(int c = 0; c <= ('h' - FIRST_ROW); c++) {
				if (Chessboard.this.isValidField (row, (byte) c)) { //om reachble finns på brädan
					Chessboard.this.fields[row - FIRST_ROW][(byte) c].mark (); //markera den
				}
			}
			
			//markera hela raden genom att gå igenom alla columner
			for(int r = 0; r <= (8 - FIRST_COLUMN); r++) {
				if (Chessboard.this.isValidField ((char) (FIRST_ROW + r), (byte) column)) {//om reachble finns på brädan
					Chessboard.this.fields[r][column - FIRST_COLUMN].mark (); //markera den
				}
			}
		}
		
		//avmarkerar skiten^
		public void unmarkReachableFields () { 
			for(int c = 0; c <= ('h' - FIRST_ROW); c++) {
				if (Chessboard.this.isValidField (row, (byte) c)) {//om reachble finns på brädan
					Chessboard.this.fields[row - FIRST_ROW][(byte) c].unmark (); //avmarkera den
				}
			}
			
			for(int r = 0; r <= (8 - FIRST_COLUMN); r++) {
				if (Chessboard.this.isValidField ((char) (FIRST_ROW + r), (byte) column)) {//om reachble finns på brädan
					Chessboard.this.fields[r][column - FIRST_COLUMN].unmark (); //avmarkera den
				}
			}
		}
	}
	
	public class Knight extends Chesspiece {
		public Knight (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			//INSTEAD: make array for posistions arr[col][row]:
			byte col1 = (byte) (column + 2);
			char row1 = (char) (row + 1);
			
			//INSTEAD: make loop through with while isValidField
			if (Chessboard.this.isValidField (row1, col1)) {
				int    r = row1 -FIRST_ROW;
				int    c = col1 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			byte col2 = (byte) (column + 2);
			char row2 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row2, col2)) {
				int    r = row2 -FIRST_ROW;
				int    c = col2 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			byte col3 = (byte) (column - 2);
			char row3 = (char) (row + 1);
			
			if (Chessboard.this.isValidField (row3, col3)) {
				int    r = row3 -FIRST_ROW;
				int    c = col3 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			byte col4 = (byte) (column - 2);
			char row4 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row4, col4)) {
				int    r = row4 -FIRST_ROW;
				int    c = col4 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			byte col5 = (byte) (column + 1);
			char row5 = (char) (row + 2);
			
			if (Chessboard.this.isValidField (row5, col5)) {
				int    r = row5 -FIRST_ROW;
				int    c = col5 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			byte col6 = (byte) (column + 1);
			char row6 = (char) (row - 2);
			
			if (Chessboard.this.isValidField (row6, col6)) {
				int    r = row6 -FIRST_ROW;
				int    c = col6 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			byte col7 = (byte) (column - 1);
			char row7 = (char) (row + 2);
			
			if (Chessboard.this.isValidField (row7, col7)) {
				int    r = row7 -FIRST_ROW;
				int    c = col7 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			byte col8 = (byte) (column - 1);
			char row8 = (char) (row - 2);
			
			if (Chessboard.this.isValidField (row8, col8)) {
				int    r = row8 -FIRST_ROW;
				int    c = col8 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
				
			
			
		}

		public void unmarkReachableFields () { 
			byte col1 = (byte) (column + 2);
			char row1 = (char) (row + 1);
			
			if (Chessboard.this.isValidField (row1, col1)) {
				int    r = row1 -FIRST_ROW;
				int    c = col1 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			byte col2 = (byte) (column + 2);
			char row2 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row2, col2)) {
				int    r = row2 -FIRST_ROW;
				int    c = col2 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			byte col3 = (byte) (column - 2);
			char row3 = (char) (row + 1);
			
			if (Chessboard.this.isValidField (row3, col3)) {
				int    r = row3 -FIRST_ROW;
				int    c = col3 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			byte col4 = (byte) (column - 2);
			char row4 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row4, col4)) {
				int    r = row4 -FIRST_ROW;
				int    c = col4 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			byte col5 = (byte) (column + 1);
			char row5 = (char) (row + 2);
			
			if (Chessboard.this.isValidField (row5, col5)) {
				int    r = row5 -FIRST_ROW;
				int    c = col5 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			byte col6 = (byte) (column + 1);
			char row6 = (char) (row - 2);
			
			if (Chessboard.this.isValidField (row6, col6)) {
				int    r = row6 -FIRST_ROW;
				int    c = col6 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			byte col7 = (byte) (column - 1);
			char row7 = (char) (row + 2);
			
			if (Chessboard.this.isValidField (row7, col7)) {
				int    r = row7 -FIRST_ROW;
				int    c = col7 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			byte col8 = (byte) (column - 1);
			char row8 = (char) (row - 2);
			
			if (Chessboard.this.isValidField (row8, col8)) {
				int    r = row8 -FIRST_ROW;
				int    c = col8 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
	}
	
	public class Bishop extends Chesspiece {
		public Bishop (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			
			int i = 1;
            while(Chessboard.this.isValidField((char) (row + i),(byte) (column + i)))
            {
            		int    r = (char) (row + i);
            		int    c = (byte) (column + i);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].mark ();
                    i++;
            }
            
            int j = 1;
			
            while(Chessboard.this.isValidField((char) (row - j),(byte) (column + j)))
            {
            		int    r = (char) (row - j) ;
            		int    c = (byte) (column + j);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].mark ();
                    j++;
            }
            
            int k = 1;
			
            while(Chessboard.this.isValidField((char) (row + k),(byte) (column - k)))
            {
            	int    r = (char) (row + k) ;
        		int    c = (byte) (column - k);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].mark ();
                    k++;
            }
            
            int l = 1;
			
            while(Chessboard.this.isValidField((char) (row - l),(byte) (column - l)))
            {
            	int    r = (char) (row - l) ;
        		int    c = (byte) (column - l);
            		Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark ();
                    l++;
            }
           
		}
		public void unmarkReachableFields () { 
				
				int i = 1;
	            while(Chessboard.this.isValidField((char) (row + i),(byte) (column + i)))
	            {
	            		int    r = (char) (row + i);
	            		int    c = (byte) (column + i);
	            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].unmark ();
	                    i++;
	            }
	            
	            int j = 1;
				
	            while(Chessboard.this.isValidField((char) (row - j),(byte) (column + j)))
	            {
	            		int    r = (char) (row - j) ;
	            		int    c = (byte) (column + j);
	            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].unmark ();
	                    j++;
	            }
	            
	            int k = 1;
				
	            while(Chessboard.this.isValidField((char) (row + k),(byte) (column - k)))
	            {
	            	int    r = (char) (row + k) ;
	        		int    c = (byte) (column - k);
	            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].unmark ();
	                    k++;
	            }
	            
	            int l = 1;
				
	            while(Chessboard.this.isValidField((char) (row - l),(byte) (column - l)))
	            {
	            	int    r = (char) (row - l) ;
	        		int    c = (byte) (column - l);
	            		Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark ();
	                    l++;
	            }
	           
		}
	}
	
	public class Queen extends Chesspiece {
		public Queen (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			
		//ROOK	
			//markera hela columnen genom att gå igenom alla rader
			for(int c = 0; c <= ('h' - FIRST_ROW); c++) {
				if (Chessboard.this.isValidField (row, (byte) c)) { //om reachble finns på brädan
					Chessboard.this.fields[row - FIRST_ROW][(byte) c].mark (); //markera den
				}
			}
			
			//markera hela raden genom att gå igenom alla columner
			for(int r = 0; r <= (8 - FIRST_COLUMN); r++) {
				if (Chessboard.this.isValidField ((char) (FIRST_ROW + r), (byte) column)) {//om reachble finns på brädan
					Chessboard.this.fields[r][column - FIRST_COLUMN].mark (); //markera den
				}
			}
			
		//BISHOP
			int i = 1;
            while(Chessboard.this.isValidField((char) (row + i),(byte) (column + i)))
            {
            		int    r = (char) (row + i);
            		int    c = (byte) (column + i);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].mark ();
                    i++;
            }
            
            int j = 1;
			
            while(Chessboard.this.isValidField((char) (row - j),(byte) (column + j)))
            {
            		int    r = (char) (row - j) ;
            		int    c = (byte) (column + j);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].mark ();
                    j++;
            }
            
            int k = 1;
			
            while(Chessboard.this.isValidField((char) (row + k),(byte) (column - k)))
            {
            	int    r = (char) (row + k) ;
        		int    c = (byte) (column - k);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].mark ();
                    k++;
            }
            
            int l = 1;
			
            while(Chessboard.this.isValidField((char) (row - l),(byte) (column - l)))
            {
            	int    r = (char) (row - l) ;
        		int    c = (byte) (column - l);
            		Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark ();
                    l++;
            }
           
		}

		public void unmarkReachableFields () { 
			//ROOK	
			//markera hela columnen genom att gå igenom alla rader
			for(int c = 0; c <= ('h' - FIRST_ROW); c++) {
				if (Chessboard.this.isValidField (row, (byte) c)) { //om reachble finns på brädan
					Chessboard.this.fields[row - FIRST_ROW][(byte) c].unmark (); //markera den
				}
			}
			
			//markera hela raden genom att gå igenom alla columner
			for(int r = 0; r <= (8 - FIRST_COLUMN); r++) {
				if (Chessboard.this.isValidField ((char) (FIRST_ROW + r), (byte) column)) {//om reachble finns på brädan
					Chessboard.this.fields[r][column - FIRST_COLUMN].unmark (); //markera den
				}
			}
			
		//BISHOP
			int i = 1;
            while(Chessboard.this.isValidField((char) (row + i),(byte) (column + i)))
            {
            		int    r = (char) (row + i);
            		int    c = (byte) (column + i);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].unmark ();
                    i++;
            }
            
            int j = 1;
			
            while(Chessboard.this.isValidField((char) (row - j),(byte) (column + j)))
            {
            		int    r = (char) (row - j) ;
            		int    c = (byte) (column + j);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].unmark ();
                    j++;
            }
            
            int k = 1;
			
            while(Chessboard.this.isValidField((char) (row + k),(byte) (column - k)))
            {
            	int    r = (char) (row + k) ;
        		int    c = (byte) (column - k);
            		Chessboard.this.fields[r - FIRST_ROW][c-FIRST_COLUMN].unmark ();
                    k++;
            }
            
            int l = 1;
			
            while(Chessboard.this.isValidField((char) (row - l),(byte) (column - l)))
            {
            	int    r = (char) (row - l) ;
        		int    c = (byte) (column - l);
            		Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark ();
                    l++;
            }
           
		}
	}
	
	public class King extends Chesspiece {
		public King (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			
			//VERTIKALT
			byte    col1 = (byte) (column + 1); //förlyttas per column
			if (Chessboard.this.isValidField (row, col1)) { //om reachble är inom schackbrädan
				int    r = row - FIRST_ROW;
				int    c = col1 - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			byte    col2 = (byte) (column - 1); //förlyttas per column
			if (Chessboard.this.isValidField (row, col2)) { //om reachble är inom schackbrädan
				int    r = row - FIRST_ROW;
				int    c = col2 - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			//HORISONTELLT
			char    row1 = (char) (row + 1); //förlyttas per column
			if (Chessboard.this.isValidField (row1, column)) { //om reachble är inom schackbrädan
				int    r = row1 - FIRST_ROW;
				int    c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			char    row2 = (char) (row - 1); //förlyttas per column
			if (Chessboard.this.isValidField (row2, column)) { //om reachble är inom schackbrädan
				int    r = row2 - FIRST_ROW;
				int    c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			//HÖRNBITAR
			byte    col3 = (byte) (column + 1);
			char 	row3 = (char) (row + 1);
			
			if (Chessboard.this.isValidField (row3, col3)) {
				int    r = row3 -FIRST_ROW;
				int    c = col3 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			byte    col4 = (byte) (column + 1);
			char 	row4 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row4, col4)) {
				int    r = row4 -FIRST_ROW;
				int    c = col4 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			byte    col5 = (byte) (column - 1);
			char 	row5 = (char) (row + 1);
			
			
			if (Chessboard.this.isValidField (row5, col5)) {
				int    r = row5 -FIRST_ROW;
				int    c = col5 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			byte    col6 = (byte) (column - 1);
			char 	row6 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row6, col6)) {
				int    r = row6 -FIRST_ROW;
				int    c = col6 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
		}

		public void unmarkReachableFields () { 
			byte    col1 = (byte) (column + 1); //förlyttas per column
			if (Chessboard.this.isValidField (row, col1)) { //om reachble är inom schackbrädan
				int    r = row - FIRST_ROW;
				int    c = col1 - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			char    row1 = (char) (row + 1); //förlyttas per column
			if (Chessboard.this.isValidField (row1, column)) { //om reachble är inom schackbrädan
				int    r = row1 - FIRST_ROW;
				int    c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			byte    col2 = (byte) (column - 1); //förlyttas per column
			if (Chessboard.this.isValidField (row, col2)) { //om reachble är inom schackbrädan
				int    r = row - FIRST_ROW;
				int    c = col2 - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			char    row2 = (char) (row - 1); //förlyttas per column
			if (Chessboard.this.isValidField (row2, column)) { //om reachble är inom schackbrädan
				int    r = row2 - FIRST_ROW;
				int    c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark (); //förflyttas ett steg (column) inom sin rad åt höger
			}
			
			byte    col3 = (byte) (column + 1);
			char 	row3 = (char) (row + 1);
			
			
			if (Chessboard.this.isValidField (row3, col3)) {
				int    r = row3 -FIRST_ROW;
				int    c = col3 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			byte    col4 = (byte) (column + 1);
			char 	row4 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row4, col4)) {
				int    r = row4 -FIRST_ROW;
				int    c = col4 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			byte    col5 = (byte) (column + 1);
			char 	row5 = (char) (row - 1);
			
			
			if (Chessboard.this.isValidField (row5, col5)) {
				int    r = row5 -FIRST_ROW;
				int    c = col5 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			byte    col6 = (byte) (column - 1);
			char 	row6 = (char) (row - 1);
			
			if (Chessboard.this.isValidField (row6, col6)) {
				int    r = row6 -FIRST_ROW;
				int    c = col6 -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
	}
}