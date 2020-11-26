package org.einnovator.social.client.model;

import java.util.List;
import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A {@code Reactions} statistics descriptor.
 *
 * @author support@einnovator.org
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reactions extends ObjectBase {
	
	private Map<ReactionType, Integer> owner;

	private Map<ReactionType, Integer> all;

	private Map<ReactionType, List<Reaction>> reactions;
	
	private Integer total;
	
	/**
	 * Create instance of {@code Reactions}.
	 *
	 */
	public Reactions() {
	}

	/**
	 * Get the value of property {@code owner}.
	 *
	 * @return the value of {@code owner}
	 */
	public Map<ReactionType, Integer> getOwner() {
		return owner;
	}

	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the value of {@code owner}
	 */
	public void setOwner(Map<ReactionType, Integer> owner) {
		this.owner = owner;
	}

	/**
	 * Get the value of property {@code all}.
	 *
	 * @return the value of {@code all}
	 */
	public Map<ReactionType, Integer> getAll() {
		return all;
	}

	/**
	 * Set the value of property {@code all}.
	 *
	 * @param all the value of {@code all}
	 */
	public void setAll(Map<ReactionType, Integer> all) {
		this.all = all;
	}

	/**
	 * Get the value of property {@code reactions}.
	 *
	 * @return the value of {@code reactions}
	 */
	public Map<ReactionType, List<Reaction>> getReactions() {
		return reactions;
	}

	/**
	 * Set the value of property {@code reactions}.
	 *
	 * @param reactions the value of {@code reactions}
	 */
	public void setReactions(Map<ReactionType, List<Reaction>> reactions) {
		this.reactions = reactions;
	}
	
	
	/**
	 * Get the value of property {@code total}.
	 *
	 * @return the value of {@code total}
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * Set the value of property {@code total}.
	 *
	 * @param total the value of {@code total}
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("total", total)
			.append("owner", owner)
			.append("all", all)
			.append("reactions", reactions)
		;
	}

}
