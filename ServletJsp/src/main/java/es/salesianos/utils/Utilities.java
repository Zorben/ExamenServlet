package es.salesianos.utils;

import java.util.List;

import es.salesianos.model.Country;

public class Utilities {
	
		/* 
		 *  PRE: 'original' es un String cualquiera
		 *  POST: devuelve original sin espacios, con la primera letra en mayusculas y el resto en minusculas
		 */
			
			public String formatString(String original){
				
				if (original != null && !original.isEmpty()){
					return original.trim().substring(0,1).toUpperCase() + original.substring(1).toLowerCase();
				}
				else return original;
			}
			
			
		/* 
		 *  PRE: 'list' es una lista de objetos de la clase Pais
		 *  POST: devuelve un string concatenando el nombre de los paises separandolos por una coma.
		 */
			
			public static String listCountriesToString(List<Country> list){
				String cadena="";
				if (list != null || !list.isEmpty()){
					for (Country country : list) {
						cadena += country.getcountryName()+", ";
					}
					/* quita la coma y espacio final */
					cadena += ".";
					cadena = cadena.replace(", .", ""); 
				}
				return cadena;
				
			}
}