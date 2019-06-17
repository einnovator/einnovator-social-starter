/**
 * 
 */
package org.einnovator.social.client.model;


import org.einnovator.util.model.EntityBase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A {@code Reaction}.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reaction extends EntityBase {

	private String type;
	
	private int value;
	
	
	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code Reaction}.
	 *
	 */
	public Reaction() {
	}
	
	//
	// Getters and setters
	//
	

	public int getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
}
