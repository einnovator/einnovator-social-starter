package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.springframework.core.style.ToStringCreator;

public class ChatOptions extends ObjectBase {

	private Boolean notes;

	public ChatOptions() {
	}

	public Boolean getNotes() {
		return notes;
	}

	public void setNotes(Boolean notes) {
		this.notes = notes;
	}


	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return super.toString(creator)
				.append("notes", notes);
	}
}
