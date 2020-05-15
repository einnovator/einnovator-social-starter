package org.einnovator.social.client.config;


import org.einnovator.util.config.ConnectionConfiguration;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


/**
 * EInnovator Social Service client configuration properties.
 * 
 * @author EInnovator {support@einnovator.org}
 */
@ConfigurationProperties("social")
public class SocialClientConfiguration extends ObjectBase {

	public static String DEFAULT_SERVER = "http://localhost:2030";

	private String server = DEFAULT_SERVER;
	
	@NestedConfigurationProperty
	private ConnectionConfiguration connection = new ConnectionConfiguration();

	@NestedConfigurationProperty
	private SocialRegistration registration;
	
	/**
	 * Create instance of {@code SocialClientConfiguration}.
	 *
	 */
	public SocialClientConfiguration() {
	}
	

	/**
	 * Get the value of property {@code server}.
	 *
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Set the value of property {@code server}.
	 *
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * Get the value of property {@code connection}.
	 *
	 * @return the connection
	 */
	public ConnectionConfiguration getConnection() {
		return connection;
	}

	/**
	 * Set the value of property {@code connection}.
	 *
	 * @param connection the connection to set
	 */
	public void setConnection(ConnectionConfiguration connection) {
		this.connection = connection;
	}

	/**
	 * Get the value of property {@code registration}.
	 *
	 * @return the registration
	 */
	public SocialRegistration getRegistration() {
		return registration;
	}

	/**
	 * Set the value of property {@code registration}.
	 *
	 * @param registration the registration to set
	 */
	public void setRegistration(SocialRegistration registration) {
		this.registration = registration;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("server", server)
			.append("connection", connection)
			.append("registration", registration)
			;
	}
	
}
