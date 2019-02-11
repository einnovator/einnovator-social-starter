package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

public class MessageOptions extends ObjectBase {

	private Boolean messages;

	public MessageOptions() {
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
