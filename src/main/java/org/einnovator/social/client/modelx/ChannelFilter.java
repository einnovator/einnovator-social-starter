package org.einnovator.social.client.modelx;

import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.ChannelStatus;
import org.einnovator.social.client.model.ChannelType;
import org.einnovator.util.model.ToStringCreator;

/**
 * A filter for {@code Channel}s.
 *
 * @see Channel
 * @author support@einnovator.org
 *
 */
public class ChannelFilter extends ChannelOptions {
	
	private String q;
	
	private ChannelType type;
	
	private ChannelStatus status;

	private String owner;
	
	private String group;

	private Boolean subscribe;

	private String category;
	
	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code ChannelFilter}.
	 *
	 */
	public ChannelFilter() {
	}
	
	/**
	 * Create instance of {@code ChannelFilter}.
	 *
	 * @param obj a prototype
	 */
	public ChannelFilter(Object obj) {
		super(obj);
	}
	
	//
	// Getters/Setters
	//
	
	/**
	 * Get the value of property {@code q}.
	 *
	 * @return the q
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
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public ChannelType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(ChannelType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code status}.
	 *
	 * @return the status
	 */
	public ChannelStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 */
	public void setStatus(ChannelStatus status) {
		this.status = status;
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
	 * Get the value of property {@code group}.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Set the value of property {@code group}.
	 *
	 * @param group the value of property group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Get the value of property {@code subscribe}.
	 *
	 * @return the subscribe
	 */
	public Boolean getSubscribe() {
		return subscribe;
	}

	/**
	 * Set the value of property {@code subscribe}.
	 *
	 * @param subscribe the value of property subscribe
	 */
	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * Get the value of property {@code category}.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the value of property category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	//
	// With
	//
	
	/**
	 * Set the value of property {@code q}.
	 *
	 * @param q the value of property q
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withQ(String q) {
		this.q = q;
		return this;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withType(ChannelType type) {
		this.type = type;
		return this;
	}


	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withStatus(ChannelStatus status) {
		this.status = status;
		return this;
	}
	
	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the value of property owner
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withOwner(String owner) {
		this.owner = owner;
		return this;		
	}
	
	/**
	 * Set the value of property {@code group}.
	 *
	 * @param group the value of property group
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withGroup(String group) {
		this.group = group;
		return this;		

	}
	/**
	 * Set the value of property {@code subscribe}.
	 *
	 * @param subscribe the value of property subscribe
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
		return this;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the value of property category
	 * @return this {@code ChannelFilter}
	 */
	public ChannelFilter withCategory(String category) {
		this.category = category;
		return this;		
	}
	
	
	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("q", q)
			.append("type", type)
			.append("owner", owner)
			.append("group", group)
			.append("subscribe", subscribe)
			.append("category", category)
			;
	}
	
}
