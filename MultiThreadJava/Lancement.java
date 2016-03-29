import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lancement{
	// Fichier traité
	private File file = new File("Inserer le nom du fichier");
	private File file = new File("Inserer le nom du fichier");
	// Scanner parcourant le fichier
	private Scanner scanner;
    // Tableau contenant les notes
    private int[] notes;
    // Tableau contenant les id des utilisateurs et leur nb de notes
    private int[] userRate;
    // Tableau contenant les notes de popularite des films
    private int[] popMovie;
    // date de debut d'execution
    private long start;
    // temps d'execution
    private long tempsExec;
    // liste contenant les threads
    private ArrayList<Thread> list = new ArrayList<Thread>();
    // tableau stockant les notes (de la taille qu'on veut)
    private String[] arrayFile;
    // compteur de la taille du paquet de ligne envoye au thread
    private int k;

	// Consturctor
	public Lancement(){
        start = System.currentTimeMillis();

		// Initialisations
        notes = new int[6];
        for(int j = 0; j < 6 ; j++){
        	notes[j] = 0;
        }

        userRate = new int[100000];
        for(int j = 0; j < 100000; j++){
        	userRate[j]=0;
        }
        
        popMovie = new int[100000];
        for(int j = 0; j < 100000; j++){
        	userRate[j]=0;
        }
        
        arrayFile = new String[10000];
        
        try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        // Lecture du fichier
        while(scanner.hasNext()){
        	k=0;
        	
        	//Creation du paquet a envoyer au thread
        	while(k < arrayFile.length && scanner.hasNext()){
                arrayFile[k] = scanner.nextLine();
                k++;
        	}
        	
        	// On cree un thread et on lui passe un paquet de ligne du fichier
            try {
                ThreadNotes t = new ThreadNotes(arrayFile, this);
                Thread t1 = new Thread(t);
                this.list.add(t1);
                t1.start();
            } catch (Exception e){}
        }
        scanner.close();
        
        // Affichage des resultats
        printNotes();
        printUserNote(userNote());
        printPopMovie();
        System.out.println("Nombre de threads utilises : " + list.size());
        tempsExec = System.currentTimeMillis()-start;
        System.out.println("Temps d'execution : " + tempsExec + "ms");
	}
	
	// Remplissage du tableau de notes
    public synchronized void addNote(int note){
        notes[note]++;
    }

    // Remplissage de hashtable de notations d'utilisateurs
    public synchronized void addUserRate(int idUser){
        userRate[idUser-1]++;
    }

    // Calcule l'utilisateur qui a note le plus de films
    public int userNote() {
        int userMax = 0;
        int nbRate = 0;

        for(int i = 0; i < userRate.length; i++){
            if(nbRate < userRate[i]){
            	userMax = i;
            }
        }
        return(userMax);
    }
    
    // Affichage des notes
    public void printNotes(){
    	System.out.println("Nombre de films a 0 " + notes[0]);
        System.out.println("Nombre de films a 1 " + notes[1]);
        System.out.println("Nombre de films a 2 " + notes[2]);
        System.out.println("Nombre de films a 3 " + notes[3]);
        System.out.println("Nombre de films a 4 " + notes[4]);
        System.out.println("Nombre de films a 5 " + notes[5]);
    }

    // Affichage de l'utilisateur ayant note le plus de films
    public void printUserNote(int idUserMax){
        System.out.println("L'utilisateur ayant note le plus de film est le n " + (this.userRate[idUserMax] + 1));
    }
    
    // Remplit le tableau de films populaires
    public synchronized void popMovie(int idFilm, int noteFilm){
    	popMovie[idFilm] += noteFilm;
    }
    
    // Affiche le film le plus populaire
    public void printPopMovie(){
    	int movieRate = 0;
    	int movie =0;
    	
    	for (int i = 0; i < 100000; i++){
    		if(movieRate < this.popMovie[i]){
    			movieRate = this.popMovie[i];
    			movie = i;
    		}
    	}	
    	System.out.println("Le film le plus populaire est le numero " + movie);
    }
}