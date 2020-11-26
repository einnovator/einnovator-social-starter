package org.einnovator.social.client.model;

import java.util.List;
import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	private Map<ReactionType, Integer> userStats;

	private List<Reaction> userReactions;

	private Map<ReactionType, Integer> stats;

	private Map<ReactionType, List<Reaction>> reactions;
	
	private Integer total;
	
	/**
	 * Create instance of {@code Reactions}.
	 *
	 */
	public Reactions() {
	}

	/**
	 * Get the value of property {@code userStats}.
	 *
	 * @return the value of {@code userStats}
	 */
	public Map<ReactionType, Integer> getUserStats() {
		return userStats;
	}

	/**
	 * Set the value of property {@code userStats}.
	 *
	 * @param userStats the value of {@code userStats}
	 */
	public void setUserStats(Map<ReactionType, Integer> userStats) {
		this.userStats = userStats;
	}

	/**
	 * Get the value of property {@code userReactions}.
	 *
	 * @return the value of {@code userReactions}
	 */
	public List<Reaction> getUserReactions() {
		return userReactions;
	}

	/**
	 * Set the value of property {@code userReactions}.
	 *
	 * @param userReactions the value of {@code userReactions}
	 */
	public void setUserReactions(List<Reaction> userReactions) {
		this.userReactions = userReactions;
	}

	/**
	 * Get the value of property {@code stats}.
	 *
	 * @return the value of {@code stats}
	 */
	public Map<ReactionType, Integer> getStats() {
		return stats;
	}

	/**
	 * Set the value of property {@code stats}.
	 *
	 * @param stats the value of {@code stats}
	 */
	public void setStats(Map<ReactionType, Integer> stats) {
		this.stats = stats;
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

	public List<Reaction> findUserReactions(ReactionType type) {
		return Reaction.findOfType(userReactions, type);
	}

	@JsonIgnore
	public Reaction getUserReaction(ReactionType type) {
		List<Reaction> reactions = Reaction.findOfType(userReactions, type);
		return reactions!=null && reactions.size()>0 ? reactions.get(0) : null;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("total", total)
			.append("userStats", userStats)
			.append("stats", stats)
			.append("userReactions", userReactions)
			.append("reactions", reactions)
		;
	}

}
