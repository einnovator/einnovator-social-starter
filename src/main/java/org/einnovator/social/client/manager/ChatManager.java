package org.einnovator.social.client.manager;


import java.net.URI;
import java.util.Map;

import org.einnovator.social.client.model.Chat;
import org.einnovator.social.client.modelx.ChatFilter;
import org.einnovator.social.client.modelx.ChatOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatManager {
	
	
	Chat getChat(String id);
	
	Chat getChat(String id, ChatOptions options);

	Page<Chat> listChats(ChatFilter filter, Pageable pageable);

	URI createChat(Chat chat);
	
	Chat updateChat(Chat chat);
	
	boolean deleteChat(String id);
	
	void onChatUpdate(String id, Map<String, Object> details);

	void clearCache();
	
	Cache getChatCache();

}
