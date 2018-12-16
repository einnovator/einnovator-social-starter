package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.springframework.core.style.ToStringCreator;

public class ChatOptions extends ObjectBase {

	private Boolean messages;

	public ChatOptions() {
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
