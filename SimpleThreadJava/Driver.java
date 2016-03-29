import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
	double start = System.currentTimeMillis();
	
	//Nombre de votants et de film inclus dans le fichier + le separateur utilisé
	 int /*nbVotants = 6040,*/ /*nbFilms = 3952*/ nbFilms = 100000, nbVotants = 71567;
	 String separateur = "::";
	
	 //Variables pour comptabiliser le nombre de vote par étoile/demi étoile
	 float zero =0, de = 0, une = 0, deux = 0, trois = 0, quatre =0, cinq =0;
	 float unede = 0, deuxde = 0, troisde = 0, quatrede = 0, total = 0; 
	 
	 //Tableaux servant à calculer le film le plus populaire et le plus gros voteur, chaque case du tableau correspond à un id | exemple nbVoteUti[1] = votant avec l'id 1
	  int nbVoteUti[] = new int[nbVotants+1];
	  int i = 0, idMax = 0, nbRatingsMax = 0;
	  int nbVoteFilm[] = new int[nbFilms+1];
	  int idPop = 0, nbVotePop = 0;
	  
	  //Lecture des fichiers de votes et liste des films
	   String sCurrentLine = null;
	   String sCurrentLine2 = null;
	   
	  BufferedReader br2 = new BufferedReader(new FileReader("Insérer le nom du fichier"));
	  BufferedReader br = new BufferedReader(new FileReader("Insérer le nom du fichier"));

	   br.readLine();
	   br2.readLine();
	  
		while ((sCurrentLine = br.readLine()) != null) {
			
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==0)
				zero++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==0.5)
				de++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==1)
				une++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==1.5)
				unede++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==2)
				deux++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==2.5)
				deuxde++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==3)
				trois++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==3.5)
				troisde++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==4)
				quatre++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==4.5)
				quatrede++;
			if(Float.valueOf(sCurrentLine.split(separateur)[2])==5)
				cinq++;
				
			//Pour chaque ligne (vote) on incrémente la case correspondant à l'id du votant pour savoir son nombre de votes au total, même chose pour le film
			nbVoteUti[Integer.parseInt(sCurrentLine.split(separateur)[0])]++;
			nbVoteFilm[Integer.parseInt(sCurrentLine.split(separateur)[1])]++;
			total++;
		}
		
		//Utilisateur ayant le plus voté
		for (i = 0; i < nbVotants; i++) {
			if (nbVoteUti[i] > nbRatingsMax) {
				nbRatingsMax = nbVoteUti[i];
				idMax = i;
			}
		}
		
		//Film le plus populaire
		for (i = 0; i < nbFilms; i++) {
			if (nbVoteFilm[i] > nbVotePop) {
				nbVotePop = nbVoteFilm[i];
				idPop = i;
			}
		}

		System.out.println("Notations  :");
		System.out.println("0* : "+zero+" / "+(zero/total)*100+"%");
		System.out.println("0.5* : "+de+" / "+(de/total)*100+"%");
		System.out.println("1* : "+une+" / "+(une/total)*100+"%");
		System.out.println("1.5* : "+unede+" / "+(unede/total)*100+"%");
		System.out.println("2* : "+deux+" / "+(deux/total)*100+"%");
		System.out.println("2.5* : "+deuxde+" / "+(deuxde/total)*100+"%");
		System.out.println("3* : "+trois+" / "+(trois/total)*100+"%");
		System.out.println("3.5* : "+troisde+" / "+(troisde/total)*100+"%");
		System.out.println("4* : "+quatre+" / "+(quatre/total)*100+"%");
		System.out.println("4.5* : "+quatrede+" / "+(quatrede/total)*100+"%");
		System.out.println("5* : "+cinq+" / "+(cinq/total)*100+"%");
		System.out.println("Total des votes : "+total);
		
		System.out.println("Plus gros voteur : " + idMax);
		
		//Recuperation du nom du film le plus populaire
		for(i=0; i<idPop; i++)
			sCurrentLine2=br2.readLine();
		
		System.out.println("Film le plus populaire : " +sCurrentLine2.split(separateur)[1]);
		br.close();
		br2.close();
		
		System.out.println("Temps d'exec : " + (System.currentTimeMillis()-start));
	}
}
