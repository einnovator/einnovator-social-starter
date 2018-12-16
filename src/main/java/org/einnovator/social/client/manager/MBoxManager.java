package org.einnovator.social.client.manager;


import java.net.URI;
import java.util.Map;

import org.einnovator.social.client.model.MBox;
import org.einnovator.social.client.modelx.ChatFilter;
import org.einnovator.social.client.modelx.ChatOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MBoxManager {
	
	
	MBox getChat(String id);
	
	MBox getChat(String id, ChatOptions options);

	Page<MBox> listChats(ChatFilter filter, Pageable pageable);

	URI createChat(MBox mbox);
	
	MBox updateChat(MBox mbox);
	
	boolean deleteChat(String id);
	
	void onChatUpdate(String id, Map<String, Object> details);

	void clearCache();
	
	Cache getChatCache();

}
