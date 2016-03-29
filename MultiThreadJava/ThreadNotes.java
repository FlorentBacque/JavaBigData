
public class ThreadNotes implements Runnable {
	
	private String[] lines;
	// Nom du Lancement appelant
	private Lancement lc;
    // compteur de position sur la ligne
    private int j;
	
	// Constructor
	public ThreadNotes (String[] lines, Lancement lc){
		this.lines = new String[lines.length];
		this.lines = lines;
		this.lc = lc;
		this.j = 0;
	}

	@Override
	public void run() {
		// compteur du nombre de ':'
		int countColon = 0;
		int ctInf = 0;
		int ctSup = 0;
		boolean flagInf = true;
		boolean flagSup = true;
		
		
		// Nombre de notes
		for(int i = 0; i < lines.length; i++){
			j = 0;
			countColon =0;
			while(j < lines[i].length() && countColon < 4){
				if(lines[i].charAt(j) == ':'){
    				countColon++;
    			}
				j++;
			}
			lc.addNote(((int)lines[i].charAt(j))-48);
		}
		
		// Utilisateur ayant le plus note
		for(int i = 0; i < lines.length; i++){
			countColon = 0;
			j = 0;
			while(j < lines[i].length() && countColon < 1){
				if(lines[i].charAt(j) == ':'){
    				countColon++;
    			}
				j++;
			}
			lc.addUserRate(Integer.parseInt(lines[i].substring(0, (j-2))));
		}
		
		// Film le plus populaire
		for(int i = 0; i < lines.length; i++){
			countColon = 0;
			j = 0;
			ctInf = 0;
			ctSup = 0;
			flagInf = true;
			flagSup = true;
			
			while(j < lines[i].length() && countColon < 4){
				if(lines[i].charAt(j) == ':'){
					countColon++;
    			}
				if(countColon == 2 && flagInf){
					ctInf = j+1;
					flagInf = false;
				}
				if(countColon == 3 && flagSup){
					ctSup = j;
					flagSup = false;
				}
				j++;
			}
			lc.popMovie(Integer.parseInt(lines[i].substring(ctInf, ctSup)), (((int)lines[i].charAt(j))-48));
		}
	}
}