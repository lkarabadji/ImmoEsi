package tp.Poo;

public class Vente implements CalculerPrix {   
	
	public double calculerPrix(Wilaya w, Bien b) {
		
		double val = (w.getPrixM2());
		double finPrix=0;
		if (b.prix<5000000)
		{
			if(val<50000)
			{
				finPrix = (0.03*(b.prix)) + (b.prix);
			}
			else {
				finPrix = (0.035*(b.prix)) + (b.prix);
			}
		}
		if ((b.prix<15000000)&&(b.prix>5000000))
		{
			if(val<50000)
			{
				finPrix = (0.02*(b.prix)) + (b.prix);
			}
			else 
			{
				finPrix = (0.025*(b.prix)) + (b.prix);
			}
		}
		if ( b.prix>15000000 ) {
				
				if(val<70000)
				{
					finPrix = (0.01*(b.prix)) + (b.prix);
				}
				else 
				{
					finPrix = (0.02*(b.prix)) + (b.prix);
				}
				
			}
		return (finPrix);
		
	}

}
