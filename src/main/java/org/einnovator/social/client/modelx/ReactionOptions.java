package org.einnovator.social.client.modelx;

import org.einnovator.social.client.model.Reaction;
import org.einnovator.util.model.ToStringCreator;
import org.einnovator.util.web.RequestOptions;

/**
 * Options to retrieve {@code Reaction}s.
 *
 * @see Reaction
 * @author support@einnovator.org
 */
public class ReactionOptions extends RequestOptions {

	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code ReactionOptions}.
	 *
	 */
	public ReactionOptions() {
	}

	/**
	 * Create instance of {@code ReactionOptions}.
	 *
	 * @param obj a prototype
	 */
	public ReactionOptions(Object obj) {
		super(obj);
	}
	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return super.toString(creator);
	}
}
