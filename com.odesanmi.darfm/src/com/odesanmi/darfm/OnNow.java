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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Bean that contains On now information.<br/>
 * This class contains static methods that executes API methods relating to a
 * current show being aired by a radio station<br/>
 * <br/>
 * 
 * Method names are equivalent to the dar.fm API method names.
 * 
 * @see www.dar.fm
 * @author Temitayo Odesanmi
 */
public class OnNow {

	private String callsign;
	private Date timestamp;
	private String event_expires_seconds;
	private String show_expires_seconds;
	private Event event;
	private String showname;
	private String showhost;
	private String showgenre;
	private String genre;

	static final ArtifactFactory<OnNow> FACTORY = new OnNowFactory();

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getEvent_expires_seconds() {
		return event_expires_seconds;
	}

	public void setEvent_expires_seconds(String event_expires_seconds) {
		this.event_expires_seconds = event_expires_seconds;
	}

	public String getShow_expires_seconds() {
		return show_expires_seconds;
	}

	public void setShow_expires_seconds(String show_expires_seconds) {
		this.show_expires_seconds = show_expires_seconds;
	}

	public Event getEvent() {
		return event;
	}

	public void setevent(Event event) {
		this.event = event;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getShowhost() {
		return showhost;
	}

	public void setShowhost(String showhost) {
		this.showhost = showhost;
	}

	public String getShowgenre() {
		return showgenre;
	}

	public void setShowgenre(String showgenre) {
		this.showgenre = showgenre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	/***
	 * This command will return the currently playing song with artist or talk
	 * show for the radio station with matching callsign. <br/>
	 * <br/>
	 * */
	public static OnNow getOnNow(String callsign) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.onnow(callsign);
		if (element == null)
			return new OnNow();
		return FACTORY.parseElement(element);
	}

	// need to parse this structure 2014-01-20 19:37:12
	static final SimpleDateFormat dateformat = new SimpleDateFormat(
			"yyyy-mm-d HH:mm:ss");

	private static class OnNowFactory implements ArtifactFactory<OnNow> {
		@Override
		public OnNow parseElement(DomElement element) {
			OnNow onnow = new OnNow();

			DomElement callsign = element.getChild("callsign");
			if (callsign != null) {
				onnow.callsign = callsign.getText();
			}

			DomElement showname = element.getChild("showname");
			if (showname != null) {
				onnow.showname = showname.getText();
			}

			DomElement showhost = element.getChild("showhost");
			if (showhost != null) {
				onnow.showhost = showhost.getText();
			}

			DomElement genre = element.getChild("genre");
			if (genre != null) {
				onnow.genre = genre.getText();
			}

			DomElement show_expires_seconds = element
					.getChild("show_expires_seconds");
			if (show_expires_seconds != null) {
				onnow.show_expires_seconds = show_expires_seconds.getText();
			}

			DomElement timestamp = element.getChild("timestamp");
			if (timestamp != null) {
				try {
					Calendar cd = new GregorianCalendar();
					cd.setTime(dateformat.parse(timestamp.getText()));
					onnow.timestamp = cd.getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			DomElement event_expires_seconds = element
					.getChild("event_expires_seconds");
			if (event_expires_seconds != null) {
				onnow.event_expires_seconds = event_expires_seconds.getText();
			}

			// OnNow might have an Event

			if (element.hasChild("events")) {
				DomElement docevnt = element.getChild("events").getChild(
						"event");
				if (docevnt != null) {

					Event event = new Event();

					DomElement domtime = docevnt.getChild("time");
					if (domtime != null) {
						try {
							Calendar cd = new GregorianCalendar();
							//
							cd.setTime(dateformat.parse(domtime.getText()));
							event.setTime(cd.getTime());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					DomElement type = docevnt.getChild("type");
					if (type != null) {
						event.setType(type.getText());
					}

					DomElement artist = docevnt.getChild("artist");
					if (artist != null) {
						event.setArtist(artist.getText());
					}

					DomElement title = docevnt.getChild("title");
					if (title != null) {
						event.setTitle(title.getText());
					}

					DomElement album = docevnt.getChild("album");
					if (album != null) {
						event.setAlbum(album.getText());
					}

					DomElement ubergenre = docevnt.getChild("ubergenre");
					if (ubergenre != null) {
						event.setUbergenre(ubergenre.getText());
					}

					DomElement arturl = docevnt.getChild("arturl");
					if (arturl != null) {
						event.setArturl(arturl.getText());
					}

					DomElement infourl = docevnt.getChild("infourl");
					if (infourl != null) {
						event.setInfourl(infourl.getText());
					}

					onnow.event = event;
				}
			}

			return onnow;
		}
	}

}
