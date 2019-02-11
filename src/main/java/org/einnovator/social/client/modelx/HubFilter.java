package org.einnovator.social.client.modelx;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

public class HubFilter extends ObjectBase {

	private String q;
	
	private Boolean strict;

	private String owner;

	public HubFilter() {
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
		.append("owner", owner)
		.append("q", q)
		.append("strict",strict);
	}
}
