package Insegnamenti;

import myExceptions.DataFormatException;

public class Check {
	
	public static boolean orario(String orario) { // controlla che la stringa sia un orario
		
		if(orario.length() <= 5) {
			byte i = 0;
			while (i < orario.length() && orario.charAt(i) != ':')
				i++;
				
			if(orario.length() == 4 && i == 1 &&  // formato h:mm
			   Character.isDigit(orario.charAt(0)) && Character.isDigit(orario.charAt(2)) && Character.isDigit(orario.charAt(3)))
			{
				if(Integer.parseInt(orario.substring(0, 1)) >= 0 && 
				   Integer.parseInt(orario.substring(2, 4)) >= 0 && Integer.parseInt(orario.substring(2, 4)) <= 60)
					return true;
			}
			if(orario.length() == 5 && i == 2 &&  // formato hh:mm
			   Character.isDigit(orario.charAt(0)) && Character.isDigit(orario.charAt(1)) &&
			   Character.isDigit(orario.charAt(3)) && Character.isDigit(orario.charAt(4)))
			{
				
				if(Integer.parseInt(orario.substring(0, 2)) >= 0 && Integer.parseInt(orario.substring(0, 2)) <= 23 &&
				   Integer.parseInt(orario.substring(3, 5)) >= 0 && Integer.parseInt(orario.substring(3, 5)) <= 60)
					return true;
			}
		}
		return false;
	}
	// gg/mm/aa
	public static void data(String data) throws DataFormatException {
		boolean isOk = false;
		if (data.length() == 8)
			if(data.charAt(2) == '/' && data.charAt(5) == '/')
				try {
					if(Integer.parseInt(data.substring(6, 8)) == 21 || Integer.parseInt(data.substring(6, 8)) == 22) { // controlla l'anno
						byte m = Byte.parseByte(data.substring(3, 5));
						byte g = Byte.parseByte(data.substring(0, 2));
						if(g > 0) {
							switch (m) { // controllo il mese con i case dello switch e i giorni all'interno dei case
								case 4, 6, 9, 11:  if (g <= 30) isOk = true;
								case 2: if(g <= 28) isOk = true;
								case 1, 3, 5, 7 ,8, 10, 12: if(g <= 31) isOk = true;
							}
						}
					}
				} catch (NumberFormatException e) {

				}
		if(!isOk)
			throw new DataFormatException("Non e' una data esistente o selezionabile");
	}
	
	public static boolean isEmpty(Object[] obj) {
		
		boolean empty = true;
		for(Object o : obj)
			if (o != null) {
				empty = false;
				break;
			}
		return empty;
	}

}
