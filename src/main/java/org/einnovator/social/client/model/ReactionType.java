package org.einnovator.social.client.model;


/**
 * An Enum for a {@code Reaction} type.
 *
 * @see Reaction
 * @author support@einnovator.org
 */
public enum ReactionType {
	VOTEUP("Vote Up"),
	VOTEDOWN("Vote Down"),
	LIKE("Like"),
	LOVE("Love")
	;
	
	private final String displayValue;

	ReactionType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public String getDisplayName() {
		return displayValue;
	}

	public static ReactionType parse(String s) {
		for (ReactionType e: ReactionType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static ReactionType parse(String s, ReactionType defaultValue) {
		ReactionType value = parse(s);
		return value!=null ? value: defaultValue;
	}

}
