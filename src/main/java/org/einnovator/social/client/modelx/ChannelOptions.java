package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

/**
 * Options to retrieve {@code Channel}s.
 *
 * @see Channel
 * @author support@einnovator.org
 */
public class ChannelOptions extends ObjectBase {

	private Boolean messages;

	/**
	 * Create instance of {@code ChannelOptions}.
	 *
	 */
	public ChannelOptions() {
	}

	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code messages}.
	 *
	 * @return the messages
	 */
	public Boolean getMessages() {
		return messages;
	}

	/**
	 * Set the value of property {@code messages}.
	 *
	 * @param messages the value of property messages
	 */
	public void setMessages(Boolean messages) {
		this.messages = messages;
	}
	
	
	//
	// With
	//

	/**
	 * Set the value of property {@code messages}.
	 *
	 * @param messages the value of property messages
	 * @return this {@code ChannelOptions}
	 */	
	public ChannelOptions withMessages(Boolean messages) {
		this.messages = messages;
		return this;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return super.toString(creator)
				.append("messages", messages);
	}

}
