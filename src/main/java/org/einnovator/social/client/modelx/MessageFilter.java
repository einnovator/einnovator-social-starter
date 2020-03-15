package org.einnovator.social.client.modelx;

import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.model.MessageType;
import org.einnovator.util.model.ToStringCreator;

/**
 * A filter for {@code Message}s.
 *
 * @see Message
 * @author support@einnovator.org
 *
 */
public class MessageFilter extends MessageOptions {
	
	
	private String q;
	
	private String owner;

	private MessageType type;

	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code MessageFilter}.
	 *
	 */
	public MessageFilter() {
	}
	
	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code q}.
	 *
	 * @return the value of q
	 */
	public String getQ() {
		return q;
	}

	/**
	 * Set the value of property {@code q}.
	 *
	 * @param q the value of property q
	 */
	public void setQ(String q) {
		this.q = q;
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
	 * @param owner the value of property owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

	
	//
	// With
	//

	/**
	 * Set the value of property {@code q}.
	 *
	 * @param q the value of property q
	 * @return this {@code MessageFilter}
	 */
	public MessageFilter withQ(String q) {
		this.q = q;
		return this;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 * @return this {@code MessageFilter}
	 */
	public MessageFilter withType(MessageType type) {
		this.type = type;
		return this;
	}
	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the value of property owner
	 * @return this {@code MessageFilter}
	 */
	public MessageFilter withOwner(String owner) {
		this.owner = owner;
		return this;		
	}
	
	
	
	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
				.append("q", q)
				.append("type", type)
				.append("owner", owner)
				;
	}
	
}
