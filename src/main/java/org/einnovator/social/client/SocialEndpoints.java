package org.einnovator.social.client;

public class SocialEndpoints {
	

	public static String mbox(String id, SocialClientConfiguration config) {
		return mboxs(config) + "/" + id;
	}

	public static String mboxs(SocialClientConfiguration config) {
		return config.getServer() + "/api/mbox/";
	}



}
