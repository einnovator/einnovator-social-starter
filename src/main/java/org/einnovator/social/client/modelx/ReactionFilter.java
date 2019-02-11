package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

public class ReactionFilter extends ObjectBase {

	private String type;

	private String username;

	private String group;

	public ReactionFilter() {
	}

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public String getTypes() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the type to set
	 */
	public void setTypes(String type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code username}.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the value of property {@code username}.
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
		.append("type", type)
		.append("username", username)
		.append("group", group);
	}
}
