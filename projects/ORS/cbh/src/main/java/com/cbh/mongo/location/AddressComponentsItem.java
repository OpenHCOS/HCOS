package com.cbh.mongo.location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressComponentsItem{

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("short_name")
	private String shortName;

	@JsonProperty("long_name")
	private String longName;

	public void setTypes(List<String> types){
		this.types = types;
	}

	public List<String> getTypes(){
		return types;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}

	public void setLongName(String longName){
		this.longName = longName;
	}

	public String getLongName(){
		return longName;
	}

	@Override
 	public String toString(){
		return 
			"AddressComponentsItem{" + 
			"types = '" + types + '\'' + 
			",short_name = '" + shortName + '\'' + 
			",long_name = '" + longName + '\'' + 
			"}";
		}
}