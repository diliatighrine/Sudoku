import java.util.ArrayList;

public abstract class Abeille{

	protected int posx;
	protected int posy;
	protected static ArrayList<Abeille> agents = new ArrayList<Abeille>(); 

	public Abeille(int posx , int posy){
		this.posx=posx;
		this.posy=posy;
		agents.add(this);
	}

	public double distance(double x , double y){

		double px = Math.pow( ( this.posx - x) , 2); 
		double py= Math.pow( (this.posy - y) , 2);
		return Math.sqrt(px+py) ;

	}



	public String toString(){
		return "Abeille  "+ "posx  =  " + posx + "  posy   " + posy ;
	}

	public int getPosx(){
		return posx;
	}

	public int getPosy(){
		return posy;
	}
	
	public static ArrayList<Abeille> getAgents(){
		return agents ;
	}
	

}






