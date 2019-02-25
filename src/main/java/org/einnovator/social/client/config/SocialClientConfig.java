package org.einnovator.social.client.config;


import org.einnovator.social.client.SocialClient;
import org.einnovator.social.client.manager.ChannelManager;
import org.einnovator.social.client.manager.ChannelManagerImpl;
import org.einnovator.social.client.web.ChannelRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


@Configuration
@EnableConfigurationProperties(value=SocialClientConfiguration.class)
public class SocialClientConfig {


	@Autowired
	private SocialClientConfiguration config;
	
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private OAuth2ProtectedResourceDetails oauth2ResourceDetails;

	public ClientHttpRequestFactory clientHttpRequestFactory() {
		ConnectionConfiguration connConfig = config.getConnection();
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		if	(connConfig.getTimeout() != null) {
			clientHttpRequestFactory.setConnectTimeout(connConfig.getTimeout());
		}
		if	(connConfig.getRequestTimeout() != null) {
			clientHttpRequestFactory.setConnectionRequestTimeout(connConfig.getRequestTimeout());
		}
		if	(connConfig.getReadTimeout() != null) {
			clientHttpRequestFactory.setReadTimeout(connConfig.getReadTimeout());
		}
		return clientHttpRequestFactory;
	}
	
	@Bean
	public OAuth2RestTemplate metaRestTemplate() {
		OAuth2RestTemplate template;
		template = new OAuth2RestTemplate(oauth2ResourceDetails, oauth2ClientContext);			
		template.setRequestFactory(clientHttpRequestFactory());
		return template;
	}
	
	@Bean
	public SocialClient channelClient() {
		return new SocialClient(config);
	}

	@Bean
	public ChannelManager channelManager(CacheManager cacheManager) {
		return new ChannelManagerImpl(cacheManager);
	}
	
	@Bean
	public ChannelRestController channelRestController() {
		return new ChannelRestController();
	}

}
