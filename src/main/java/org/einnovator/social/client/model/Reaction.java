/**
 * 
 */
package org.einnovator.social.client.model;


import java.util.ArrayList;
import java.util.List;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A {@code Reaction} to a {@code Message}, such as as {@code LIKE} or {@code VOTEUP}.
 *
 * @see ReactionType
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reaction extends EntityBase {

	private ReactionType type;
	
	private Integer value;
	
	
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
	// Getters/Setters
	//
	
	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public ReactionType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(ReactionType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code value}.
	 *
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Set the value of property {@code value}.
	 *
	 * @param value the value of property value
	 */
	public void setValue(Integer value) {
		this.value = value;
	}


	//
	// With
	//
	
	
	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 * @return this {@code Reaction}
	 */
	public Reaction withType(ReactionType type) {
		this.type = type;
		return this;
	}

	
	/**
	 * Set the value of property {@code value}.
	 *
	 * @param value the value of property value
	 * @return this {@code Reaction}
	 */
	public Reaction withValue(Integer value) {
		this.value = value;
		return this;
	}
	
	
	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("type", type)
				.append("value", value)
				;
	}

	 public static List<Reaction> findOfType(List<Reaction> reactions, ReactionType type) {
		 if (reactions==null) {
			 return null;
		 }
		 List<Reaction> reactions2 = new ArrayList<>();
		 for (Reaction reaction: reactions) {
			 if (reaction.getType()==type) {
				 reactions2.add(reaction);
			 }
		 }
		 return reactions2;
	}

}
