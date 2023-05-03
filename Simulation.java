public class Simulation {

	public final Terrain t = new Terrain();
    public final Abeille[] tabAgents = new Abeille[Terrain.NBLIGNESMAX * Terrain.NBCOLONNESMAX]  ;
	public final Ressource[] tabRessources = new Ressource[Terrain.NBLIGNESMAX * Terrain.NBCOLONNESMAX] ; 
	public static final int MAXIT = 1;
	public int nbAbeille;
	public int nbRess;
	
	

	public Simulation(int m , int n){


		//placer les m ressources aleatoirement sur le terrain 
	
		nbRess = m;
		nbAbeille = n;
		int lig;
		int col;

		for(int i=0;i < m ; i++){
			//generer la ligne et la colonne aleatoirement 
			//
			boolean caseFound = false ;
		
			while( ! caseFound){
				
				lig =(int) (Math.random() * Terrain.NBLIGNESMAX) ;	
		    	col = (int) (Math.random() * Terrain.NBCOLONNESMAX);
				
				if (t.sontValides(lig,col) && t.caseEstVide(lig,col)){
				
			//histoire d'avoir un equilibre entre les deux ressources miel et pollen j'utilise le modulo pour avoir un de chaque une fois sur deux 
				Ressource ressource ;
				if(i % 2 == 0){

	
			  		ressource = new Ressource ("pollen",i);
				}else{
					ressource = new Ressource("miel",i);
				}
			

			//associer une source a une case 
				ressource.setPosition(lig,col);
				tabRessources[i] = ressource ; //ajout de la ressource au tableau 
				
				//
				
				Abeille abeille1 = new AbeilleRecolte(lig,col);
			    tabAgents[j] = abeille1; //ajout de l'abeille au tableau des agents
			    j++;

			 //posx =(int) (Math.random() * Terrain.NBLIGNESMAX) ;
			 //posy = (int) (Math.random() * Terrain.NBCOLONNESMAX);

			   Abeille abeille2 = new AbeilleTransforme(lig,col);
		       tabAgents[j] = abeille2;
			   j++;
				
				caseFound = true;
				}

		}


		//generer n agents 

		/*int j = 0;
		
		while(j < n){
		
			boolean caseAgent = false ;
			int posx=-1;
			int posy=-1;
			 while (! caseAgent){
			 
			 	 posx =(int) (Math.random() * Terrain.NBLIGNESMAX) ;
			 	 posy = (int) (Math.random() * Terrain.NBCOLONNESMAX);
			 	
			 	if (t.sontValides(posx,posy)){
			 		caseAgent = true ;
			 	}
			 }
			 	

			 Abeille abeille1 = new AbeilleRecolte(posx,posy);
			 tabAgents[j] = abeille1; //ajout de l'abeille au tableau des agents
			 j++;

			 //posx =(int) (Math.random() * Terrain.NBLIGNESMAX) ;
			 //posy = (int) (Math.random() * Terrain.NBCOLONNESMAX);

			 Abeille abeille2 = new AbeilleTransforme(posx,posy);
		         tabAgents[j] = abeille2;
			 j++;


		}*/
	}
}
	





	public void simile(){
		int i = MAXIT ; 

		while(i >= 0){

			for(Abeille a : Abeille.getAgents()){

      				

					//verifier s'il y a une ressource sur la meme position que l'abeille et ainsi que la nature de la ressource (pollen ou miel )
					for(int j=0;j < nbRess;j++){

						Ressource r = tabRessources[j];

						if( (r.getX() == a.getPosx()) && (r.getY() == a.getPosy() )) {
							int quantite = r.getQuantite();
							
							//si la ressource s'agit du pollen on verifie le type de labeille qui est sur la meme case 
							
							if(r.type == "pollen"){


								//verifier le type de l'abeille :recolte
								if(a instanceof AbeilleRecolte){

									 //je vide la case car je transporte le pollen et pour cela je dois trouver une autre case valide pour y deposer la ressource 
									
									
									int lig =(int) (Math.random() * Terrain.NBLIGNESMAX) ;
								    int col = (int) (Math.random() * Terrain.NBCOLONNESMAX);
								    
									if(t.sontValides(lig,col)){
		
										a.seDeplacer(lig,col); //deplacer son abeille

										Ressource rnv = t.getCase(lig , col);
										
										// deux cas de figure : soit la nouvelle case est occupée dans ce cas la je verifie le type de la ressource sil sagit du pollen jaditionne les deux quantites , si la case est vide je pzux directement y deposer la ressource transportée 

										if (rnv != null ){  //s'il y a une ressource sur la nouvelle case 
											//si la case a du miel on fait rien sinon on rajoute la quantite du pollen a la nouvelle case 
											if(rnv.type == "pollen"){
												rnv.setQuantite(rnv.getQuantite() + quantite);
												t.videCase(a.getPosx(),a.getPosy());
												
											 
											}
										}else{//la case est vide donc on y place directement la ressource 
											t.setCase(lig,col,r); 
											t.videCase(a.getPosx(),a.getPosy());
										}

									
									}
								
									//le else pour le deuxieme type d'abeille 
								}else
									 if (a instanceof AbeilleTransforme){


											Ressource retire =t.getCase(a.getPosx(),a.getPosy());
											//s'il y a du pollen dans la case 
											if(retire != null) {
											
												t.setCase(a.getPosx(),a.getPosy(),new Ressource("miel",retire.getQuantite())); //transformation du pollen en miel par l'agent abeilletransforme 
											}
											
									}	


								}else 
								
								if(r.type == "miel") {
								
									if(a instanceof AbeilleRecolte){
									
								
										int lig =-1;
								    	int col = -1;
									
										boolean cool  =false ;
										while(! cool){
										
											if(t.sontValides(lig,col)){
											
												if((t.caseEstVide(lig,col)) || ((t.getCase(lig,col)).type == "miel")){
												cool = true;
											}
										}else{
										
										
											lig =(int) (Math.random() * Terrain.NBLIGNESMAX) ;
								    		col = (int) (Math.random() * Terrain.NBCOLONNESMAX);
								    	
								 		
								    	}	
								    }
								    
								    
								    Ressource miel = t.getCase(lig,col);
								    if(miel != null){
								    	miel.setQuantite(miel.getQuantite() + quantite);
								    }
								    
								    else{
								    	t.setCase(lig,col,new Ressource("miel",quantite));
								    }
									
							}	
						}
					}
			}
		
		}

			t.affiche(2); 
			i++;
		}
	}
}

   



