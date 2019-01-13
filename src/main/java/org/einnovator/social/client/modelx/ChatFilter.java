package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ToStringCreator;

public class ChatFilter extends ChatOptions {
	
	private String q;
	
	private Boolean strict;

	public ChatFilter() {
	}
	
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Boolean getStrict() {
		return strict;
	}

	public void setStrict(Boolean strict) {
		this.strict = strict;
	}
	
	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
				.append("q", q)
				.append("strict",strict);
	}
	
}
