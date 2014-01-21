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

/**
 * Bean that contains station player information.<br/>
 * This class contains static methods that executes API methods relating to
 * Station URLs API <br/>
 * <br/>
 * Method names are equivalent to the dar.fm API method names.
 * 
 * @see www.dar.fm
 * @author Temitayo Odesanmi
 */
public class StationPlayer {
	private String url;
	private String encoding;
	private String callsign;
	private String websiteurl;

	static final ArtifactFactory<StationPlayer> FACTORY = new StationPlayerFactory();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getWebsiteurl() {
		return websiteurl;
	}

	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}

	public static StationPlayer getStationPlayer(String callsign) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.getplayer(callsign);
		if (element == null)
			return new StationPlayer();
		return FACTORY.parseElement(element);
	}

	private static class StationPlayerFactory implements
			ArtifactFactory<StationPlayer> {
		@Override
		public StationPlayer parseElement(DomElement element) {
			StationPlayer player = new StationPlayer();

			DomElement url = element.getChild("url");
			if (url != null) {
				player.url = url.getText();
			}

			DomElement encoding = element.getChild("encoding");
			if (encoding != null) {
				player.encoding = encoding.getText();
			}

			DomElement callsign = element.getChild("callsign");
			if (callsign != null) {
				player.callsign = callsign.getText();
			}

			DomElement websiteurl = element.getChild("websiteurl");
			if (websiteurl != null) {
				player.websiteurl = websiteurl.getText();
			}

			return player;
		}
	}
}
