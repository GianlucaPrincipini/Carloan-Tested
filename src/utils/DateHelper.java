package utils;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class DateHelper {
	   /**
	    * Fuso orario di riferimento settato in Europa Centrale (Central European Time)
	    */
	   private static final DateTimeZone jodaTzCET = DateTimeZone.forID("CET");
	   
	    /**
	     * Converte una java.sql.Date in una LocalDate
	     * @param d data da convertire
	     * @return data convertita
	     */
	    public static LocalDate dateToLocalDate(java.sql.Date d) {
	        if(d==null) return null;
	        return new LocalDate(d.getTime(), jodaTzCET);
	    }

	    /**
	     * Converte una LocalDate in java.sql.Date
	     * @param ld data da convertire
	     * @return data convertita
	     */
	    public static java.sql.Date localdateToDate(LocalDate ld) {
	        if(ld==null) return null;
	        return new java.sql.Date(
	           ld.toDateTimeAtStartOfDay(jodaTzCET).getMillis());
	    }
	    
	    /**
	     * Converte una stringa del tipo "gg/mm/aaaa" in una LocalDate
	     * @param date
	     * @return
	     */
	    public static LocalDate dateParse(String date) {
	    	String[] dateString = date.split("/", 3);
	    	int year, month, day;
	    	day = Integer.parseInt(dateString[0]);
	    	month = Integer.parseInt(dateString[1]);
	    	year = Integer.parseInt(dateString[2]);
	    	return new LocalDate(year, month, day);
	    }
	    
	    public static String dateAsString(LocalDate date){
	    	return  date.getDayOfMonth() + "/" + date.getMonthOfYear() + "/" + date.getYear() ;
	    }

	    
	    public static void main(String[] args) {
	    	
	    }

		public static LocalDate dateParse(java.time.LocalDate value) {
			try {
				return new LocalDate(value.getYear(), value.getMonthValue(), value.getDayOfMonth());
			} catch (Exception e) {
				return null;
			}
		}
	    
}
