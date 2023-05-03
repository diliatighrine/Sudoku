public class AbeilleRecolte extends Agents {

	private String fonction ;

	
	public AbeilleRecolte(int posx,int posy){
		super(posx,posy);
		this.fonction = "je recolte";
	}

	
		 
	public void seDeplacer(int xnew , int ynew){
	
	for(int i = 0; i < Abeille.agents.size() ; i++){
			Abeille a = Abeille.agents.get(i);
			if((a.posx == xnew) && (a.posy == ynew)){
				System.out.println("Case occupée , deplacement de l'abeille impossible ");
				return ;
			}
		}

		this.posx = xnew;
		this.posy = ynew;
	}
	
	

	public String toString(){
		return super.toString() + "fonction = " + this.fonction ;
	}
	
	
	public AbeilleRecolte clone(){
		return new AbeilleRecolte(this.posx,this.posy);
	}
	
}







