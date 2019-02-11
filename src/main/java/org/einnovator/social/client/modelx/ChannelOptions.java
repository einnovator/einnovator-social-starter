package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

public class ChannelOptions extends ObjectBase {

	private Boolean messages;

	public ChannelOptions() {
	}

	public Boolean getNotes() {
		return messages;
	}

	public void setNotes(Boolean messages) {
		this.messages = messages;
	}


	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return super.toString(creator)
				.append("messages", messages);
	}
}
