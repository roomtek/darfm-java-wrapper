/*
 * Copyright (c) 2014, Temitayo Odesanmi
 * All rights reserved.
 *
 * Redistribution and use of this software in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer in the documentation and/or other
 *   materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.odesanmi.darfm;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class DarCaller {

	private final String BASE = "http://api.dar.fm";
	private final String _SEARCHSTATION = "/darstations.php?",
			_TOPSONGS = "/topsongs.php?",
			_ONNOW = "/onnow_api.php?",
			_AUTOCOMPL = "/songartist.php?",
			_PLAYER = "/uberstationurlxml.php?", // "/player_api.php?"
			_RELATED = "/related_stations_api.php?",
			_ALBUMART = "/songart.php?";
	private final String UA = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
	private final String ENC = "UTF-8";

	// register as a partner on dar.fm to get a partner token
	private final String PARTNER_TOKEN = "XXXXXXXXXX";

	public DomElement relatedStationsByCallsign(String callsign) {
		try {
			String req = BASE + _RELATED + "callsign=" + encode(callsign);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement relatedStationsByartist(String artist) {
		try {
			String req = BASE + _RELATED + "songartist=" + encode(artist);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement stationsByZipCode(String zipcode) {
		try {
			String req = BASE + _SEARCHSTATION + "zipcode=" + encode(zipcode);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement stationsByCity(String city) {
		try {
			String req = BASE + _SEARCHSTATION + "city=" + encode(city);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement stationsByCityState(String city, String state) {
		try {
			String req = BASE + _SEARCHSTATION + "city=" + encode(city)
					+ "&state=" + encode(state);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement stationsByCityStateCountry(String city, String state,
			String country) {
		try {
			String req = BASE + _SEARCHSTATION + "city=" + encode(city)
					+ "&state=" + encode(state) + "&country" + encode(country);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement topSongs(String genre, int max) {
		try {
			String req = BASE + _TOPSONGS + "q=" + encode(genre)
					+ "&page_size=" + max;
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement querysongart(String query) {
		try {
			String req = BASE + _ALBUMART + "q=" + encode(query);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement onnow(String callsign) {
		try {
			String req = BASE + _ONNOW + "callsign=" + encode(callsign);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement autocomplete(String query) {
		return autocomplete(query, 10);
	}

	public DomElement autocomplete(String query, int max) {
		try {
			String req = BASE + _AUTOCOMPL + "q=*" + encode(query)
					+ "*&page_size=" + max;
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomElement getplayer(String callsign) {
		try {
			String req = BASE + _PLAYER + "callsign=" + encode(callsign);
			return callReturnDOM(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private DomElement callReturnDOM(String url) throws Exception {
		URL u = new URL(url + "&partner_token=" + PARTNER_TOKEN);
		HttpURLConnection urlcn;
		urlcn = (HttpURLConnection) u.openConnection();
		urlcn.setReadTimeout(0);
		urlcn.setRequestProperty("User-Agent", UA);

		InputStream is = urlcn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, ENC);

		Document doc = newDocumentBuilder().parse(new InputSource(isr));
		doc.normalize();
		Element root = doc.getDocumentElement();
		return new DomElement(root);
	}

	private DocumentBuilder newDocumentBuilder() throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		return builderFactory.newDocumentBuilder();

	}

	private String encode(String raw) throws Exception {
		return URLEncoder.encode(raw.trim(), ENC);
	}
}
