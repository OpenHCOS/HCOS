package com.cbh.mongo.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Viewport{

	@JsonProperty("southwest")
	private Southwest southwest;

	@JsonProperty("northeast")
	private Northeast northeast;

	public void setSouthwest(Southwest southwest){
		this.southwest = southwest;
	}

	public Southwest getSouthwest(){
		return southwest;
	}

	public void setNortheast(Northeast northeast){
		this.northeast = northeast;
	}

	public Northeast getNortheast(){
		return northeast;
	}

	@Override
 	public String toString(){
		return 
			"Viewport{" + 
			"southwest = '" + southwest + '\'' + 
			",northeast = '" + northeast + '\'' + 
			"}";
		}
}