package org.einnovator.social.client.config;


import org.einnovator.social.client.SocialClient;
import org.einnovator.social.client.manager.ChannelManager;
import org.einnovator.social.client.manager.ChannelManagerImpl;
import org.einnovator.social.client.web.ChannelRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


@Configuration
@EnableConfigurationProperties(value=SocialClientConfiguration.class)
@Profile("!sso_exclude")
public class SocialClientConfig {


	@Autowired
	private SocialClientConfiguration config;
	
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private OAuth2ProtectedResourceDetails oauth2ResourceDetails;

	@Bean
	public OAuth2RestTemplate socialRestTemplate() {
		OAuth2RestTemplate template;
		template = new OAuth2RestTemplate(oauth2ResourceDetails, oauth2ClientContext);			
		template.setRequestFactory(config.getConnection().makeClientHttpRequestFactory());
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
	@ConditionalOnMissingBean(ChannelRestController.class)
	public ChannelRestController channelRestController() {
		return new ChannelRestController();
	}

}
