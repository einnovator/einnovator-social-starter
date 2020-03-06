package org.einnovator.social.client.modelx;

import org.einnovator.social.client.model.ReactionType;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

/**
 * A filter for {@code Reaction}s.
 *
 * @see Reaction
 * @author support@einnovator.org
 *
 */
public class ReactionFilter extends ObjectBase {

	private ReactionType type;

	private String owner;

	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code ReactionFilter}.
	 *
	 */
	public ReactionFilter() {
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
	 * @param type the type to set
	 */
	public void setType(ReactionType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code owner}.
	 *
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	//
	// With
	//

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the type to set
	 * @return this {@code ReactionFilter}
	 */
	public ReactionFilter withType(ReactionType type) {
		this.type = type;
		return this;
	}

	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the owner to set
	 * @return this {@code ReactionFilter}
	 */
	public ReactionFilter withOwner(String owner) {
		this.owner = owner;
		return this;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
		.append("type", type)
		.append("owner", owner);
	}
}
