package org.einnovator.social.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.UUID;

import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.ChannelBuilder;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.model.MessageBuilder;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.sso.client.support.SsoTestHelper;
import org.einnovator.util.UriUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "social.server=http://localhost:2050", "sso.server=http://localhost:2001/auth",
		"spring.cache.ehcache.config:ehcache-social-starter.xml" })
public class SocialClientTests extends SsoTestHelper {

	public static final String TEST_USER = "jsimao71@gmail.com";
	public static final String TEST_USER2 = "tdd@einnovator.org";
	public static final String TEST_USER3 = "info@einnovator.org";
	public static final String TEST_PASSWORD = "Einnovator123!!";
	
	
	private static final String CLIENT_ID = "app";
	private static final String CLIENT_SECRET = "app123!";

	private static final String TEST_CHANNEL = "TDD";
	
	@Autowired
	private SocialClientConfiguration config;

	@Autowired
	private SocialClient client;

	@Configuration
	static class TestConfig extends SsoTestHelper.TestConfig {

		public TestConfig(ApplicationContext context) {
			super(TEST_USER, TEST_PASSWORD, CLIENT_ID, CLIENT_SECRET, context);
		}
		
	}
	
	@Test
	public void listChannelsTest() {
		Page<Channel> channels = client.listChannels(null, null);
		assertNotNull(channels);
		assertNotNull(channels.getContent());
		assertFalse(channels.getNumberOfElements()==0);
		assertFalse(channels.getContent().isEmpty());
		for (Channel channel: channels) {
			System.out.println(channel);			
		}
	}

	@Test
	@Ignore
	public void listChannelsWithFilterTest() {
		String q = "t";
		ChannelFilter filter = new ChannelFilter();
		filter.setQ(q);
		Page<Channel> channels = client.listChannels(filter, null);
		assertNotNull(channels);
		assertNotNull(channels);
		assertNotNull(channels.getContent());
		assertFalse(channels.getNumberOfElements()==0);
		assertFalse(channels.getContent().isEmpty());
		for (Channel channel : channels) {
			assertTrue(channel.getName().contains(q));
		}
	}

	@Test
	public void getExistingChannelTest() {
		String name = TEST_CHANNEL;
		Channel channel = client.getChannel(name);
		assertNotNull(channel);
		assertEquals(name, channel.getName());
	}

	@Test
	public void createChannelAndDeleteTest() {
		String name = "tdd-" + UUID.randomUUID().toString();
		Channel channel = new ChannelBuilder().name(name).build();
		URI uri = client.createChannel(channel);
		assertNotNull(uri);
		String id = UriUtils.extractId(uri);
		Channel channel2 = client.getChannel(id);
		assertNotNull(channel2);
		System.out.println(channel2);
		client.deleteChannel(id);
		try {
			client.getChannel(id);			
			fail();
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	public Channel getOrCreateChannel(String name) {
		try {
			Channel channel = client.getChannel(name);		
			return channel;
		} catch (RuntimeException e) {
		}
		Channel channel = new ChannelBuilder().name(name).build();
		URI uri = client.createChannel(channel);
		assertNotNull(uri);
		String id = UriUtils.extractId(uri);
		Channel channel2 = client.getChannel(id);
		return channel2;
	}

	@Test
	@Ignore
	public void updateExistingChannelTest() {
		String name = TEST_CHANNEL;
		Channel channel = client.getChannel(name);
		assertNotNull(channel);
		assertEquals(name, channel.getName());
		channel.setPurpose("Purpose-" + UUID.randomUUID());
		client.updateChannel(channel);
		Channel channel2 = client.getChannel(name);
		assertNotNull(channel2);
		assertEquals(channel.getPurpose(), channel2.getPurpose());
	}

	@Test
	public Channel postMessageTest(String name) {
		Channel channel = getOrCreateChannel(name);
		Message msg = new MessageBuilder().content("test-" + UUID.randomUUID()).build();
		URI uri = client.postMessage(channel.getUuid(), msg);
		String id = UriUtils.extractId(uri);
		Message msg2 = client.getMessage(channel.getUuid(), id, null);
		assertNotNull(msg2);
		assertEquals(msg.getContent(), msg2.getContent());
		return channel;
	}


}