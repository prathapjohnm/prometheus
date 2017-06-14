package com.taleo.knowledgerepo.crawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource(value = { "classpath:seedurls.properties" })
@Component
public class URLVisitInfo {

	private List<String> arURLToAvoid = new ArrayList<String>(15);

	@Autowired
	Environment env;

	private int MAX_DEPTH = 5;
	private static HashSet<String> links = new HashSet<String>();

	public URLVisitInfo() {
	}

	public void prepareURLsToAvoid() {
		if(!arURLToAvoid.isEmpty())
		{
			return;
		}
		String[] urlsToVisit = env.getRequiredProperty("toavoid").split(",");
		for (String urls : urlsToVisit) {
			arURLToAvoid.add(urls);
		}
		
		urlsToVisit = env.getRequiredProperty("toavoiddevforum").split(",");
		for (String urls : urlsToVisit) {
			arURLToAvoid.add(urls);
		}
		
		//set max depth
		MAX_DEPTH = Integer.parseInt(env.getRequiredProperty("maxdepth"));
	}

	public boolean isRequiredToVisit(String strURL, int depth) {
		if ((!links.contains(strURL) && (depth < MAX_DEPTH))) {

			links.add(strURL);
			if (!arURLToAvoid.contains(strURL)) {
				System.out.println("URL :" + strURL);
			}
			if ( (strURL.contains("oracle.com") || strURL.contains("confluence.oraclecorp.com")) && !arURLToAvoid.contains(strURL) && !strURL.endsWith("revisions")) {
				return true;
			}
		}
		return false;
	}

}
