package tp.Poo;

public class Location implements CalculerPrix {
	
public double calculerPrix(Wilaya w, Bien b) {
		
		double val = (w.getPrixM2());
		double finPrix=0;
		if((b.superficie)<60) {
			
			if(val<50000)
			{
				finPrix = (0.01*(b.prix)) + (b.prix);
			}
			else {
				finPrix = (0.015*(b.prix)) + (b.prix);
			}
		}
		if (((b.superficie)>60)&&((b.superficie)<150)) {
			
			if(val<50000)
			{
				finPrix = (0.02*(b.prix)) + (b.prix);
			}
			else {
				finPrix = (0.025*(b.prix)) + (b.prix);
			}
		}
		if ((b.superficie)>150) {
			
			if(val<50000)
			{
				finPrix = (0.03*(b.prix)) + (b.prix);
			}
			else {
				finPrix = (0.035*(b.prix)) + (b.prix);
			}
		}
		
		
		
		return (finPrix);
	}
}
