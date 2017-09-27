package org.packt.functional.codes.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;


@Service("genericRef")
public class GenericReferences {
	
	public int convertInt(String strVal){
		
		Function<String,Integer> convertToInt =  Integer::parseInt;
		return convertToInt.apply(strVal);
	}
	
	public Date convertBday(String bdayStr){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Function<String,Date> birthday = t -> {
			Date bdate = null;
			try {
				bdate = sdf.parse(t);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				bdate = null;
			}
			return bdate;
		};
		
		return birthday.apply(bdayStr);
	}
	
		
	
	@SuppressWarnings("deprecation")
	public boolean midYearStarted(Date started){
		Date midYear = new Date("117,5,30");
		Predicate<Date> midDayCheck = midYear::before;
		return midDayCheck.test(started);
	}

}
