package com.taleo.knowledgerepo.util;

import org.jsoup.nodes.Document;

public class DocumentTransformationUtil {

	public static String getDescription(Document doc) {
		String desc = getKeywords(doc);

		if (desc.length() > 500) {
			desc = desc.substring(0, 500);
		}

		return desc;
	}

	public static String getKeywords(Document doc) {
		String keyWords = doc.select("body").text();
		if(keyWords.indexOf("guests online.") != -1)
		{
			keyWords = keyWords.substring(keyWords.indexOf("guests online."));
		}
		keyWords = keyWords.replaceAll("guests online.", "").replaceAll("Home » Wiki", "");

		return keyWords;
	}

	public static String getKeywordsForIndexer(Document doc) {
		return doc.select("body").text();
	}
}
