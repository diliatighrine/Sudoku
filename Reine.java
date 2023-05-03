public class Reine extends Abeille {
	
	private static final Reine INSTANCE = new Reine(0,0);
	
	private Reine(int x , int y){
		super(x,y);
	}
	
	public static Reine getInstance(){
		
		return INSTANCE ;
	}
	
	
	public String toString(){
		return "je suis la QUEEN je n'ai pas besoin de me déplacer ";
	}
	
}

