package org.packt.dissect.mvc.editor;

import java.beans.PropertyEditorSupport;

public class AgeEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		try{
			int age = Integer.parseInt(text);
			setValue(age);
		}catch(NumberFormatException e){
		    setValue(0);	
		}
	}
	
	@Override
	public String getAsText() {
	
		return  "0";
	}
}
