package org.einnovator.social.client;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


/**
 * EInnovator Meta Service client configuration properties.
 * 
 * @author Jorge Simao {jorge.simao@einnovator.org}
 */
@ConfigurationProperties("social")
public class SocialClientConfiguration {

	public static String DEFAULT_SERVER = "http://localhost:2030";

	private String server = DEFAULT_SERVER;
	
	@NestedConfigurationProperty
	private ConnectionConfiguration connection = new ConnectionConfiguration();

	public SocialClientConfiguration() {
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public ConnectionConfiguration getConnection() {
		return connection;
	}

	public void setConnection(ConnectionConfiguration connection) {
		this.connection = connection;
	}

		
}
