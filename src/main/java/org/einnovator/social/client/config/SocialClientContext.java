/**
 * 
 */
package org.einnovator.social.client.config;

import org.einnovator.util.web.ClientContext;

/**
 *
 */
public class SocialClientContext extends ClientContext {
	
	private SocialClientConfiguration config;
	
	/**
	 * Create instance of {@code SocialClientContext}.
	 *
	 */
	public SocialClientContext() {
	}

	/**
	 * Get the value of property {@code config}.
	 *
	 * @return the config
	 */
	public SocialClientConfiguration getConfig() {
		return config;
	}

	/**
	 * Set the value of property {@code config}.
	 *
	 * @param config the value of property config
	 */
	public void setConfig(SocialClientConfiguration config) {
		this.config = config;
	}
	
}
