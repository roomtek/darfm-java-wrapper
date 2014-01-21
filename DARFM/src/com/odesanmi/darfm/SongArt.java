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

import java.util.Collection;
import java.util.Collections;

/**
 * Bean that contains album art information.<br/>
 * This class contains static methods that executes API methods relating to Song
 * Art / Album Images.<br/>
 * <br/>
 * Method names are equivalent to the dar.fm API method names.
 * 
 * @see www.dar.fm
 * @author Temitayo Odesanmi
 */
public class SongArt {

	private String arturl;
	private String artist;
	private String title;
	private String album;
	private int size;
	static final ArtifactFactory<SongArt> FACTORY = new SongArtFactory();

	public String getArturl() {
		return arturl;
	}

	public void setArturl(String arturl) {
		this.arturl = arturl;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	// Basic recommendation engine using an artist
	// Example:http://dar.fm/songart.php?q=U2
	public static Collection<SongArt> querySongArt(String artist) {
		return querysongart(null, null, artist, 0);
	}

	// Basic recommendation engine using an artist, song
	// Example:http://dar.fm/songart.php?q=U2+Mysterious+Ways
	public static Collection<SongArt> querySongArt(String title, String artist) {
		return querysongart(title, null, artist, 0);
	}

	// Basic recommendation engine using an artist, album
	// Example:http://dar.fm/songart.php?q=U2+Achtung+Baby
	//
	// Basic recommendation engine using an artist, album, size
	// Example:http://dar.fm/songart.php?q=U2+Achtung+Baby+1080
	public static Collection<SongArt> querySongArt(String album, String artist,
			int size) {
		return querysongart(null, album, artist, size);
	}

	private static Collection<SongArt> querysongart(String title, String album,
			String artist, int size) {
		DarCaller dar = new DarCaller();

		StringBuilder sb = new StringBuilder();

		sb.append(artist);

		if (title != null) {
			// artist, song
			sb.append(" " + title);
			// the spaces " " are left, so that the UTF-8 encoder can properly
			// encode them as "+" which is required for the method call
		} else {
			// artist, album
			if (album != null)
				sb.append(" " + album);
		}

		if (size != 0) {
			sb.append(" " + size);
		}

		DomElement element = dar.querysongart(sb.toString());
		if (element == null)
			return Collections.emptyList();

		return CollectionBuilder.buildCollection(element, SongArt.class);

	}

	private static class SongArtFactory implements ArtifactFactory<SongArt> {
		@Override
		public SongArt parseElement(DomElement element) {
			SongArt songart = new SongArt();

			songart.size = Integer.valueOf(element.getChildText("size"));

			DomElement arturl = element.getChild("arturl");
			if (arturl != null) {
				songart.arturl = arturl.getText();
			}

			DomElement artist = element.getChild("artist");
			if (artist != null) {
				songart.artist = artist.getText();
			}

			DomElement title = element.getChild("title");
			if (title != null) {
				songart.title = title.getText();
			}

			DomElement album = element.getChild("album");
			if (album != null) {
				songart.album = album.getText();
			}

			return songart;
		}
	}

}
