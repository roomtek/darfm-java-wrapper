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
 * Bean that contains song information.<br/>
 * This class contains static methods that executes API methods relating to Song
 * information<br/>
 * <br/>
 * Method names are equivalent to the dar.fm API method names.
 * 
 * @see www.dar.fm
 * @author Temitayo Odesanmi
 */
public class Song {

	private String songartist;
	private String songtitle;

	public String getSongartist() {
		return songartist;
	}

	public void setSongartist(String songartist) {
		this.songartist = songartist;
	}

	public String getSongtitle() {
		return songtitle;
	}

	public void setSongtitle(String songtitle) {
		this.songtitle = songtitle;
	}

	static final ArtifactFactory<Song> FACTORY = new SongFactory();

	public static Collection<Song> getTopSongs(String genre) {
		return getTopSongs(genre, 10);
	}

	public static Collection<Song> getTopSongs(String genre, int max) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.topSongs(genre, max);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Song.class);
	}

	private static class SongFactory implements ArtifactFactory<Song> {
		@Override
		public Song parseElement(DomElement element) {
			Song song = new Song();

			DomElement songtitle = element.getChild("songtitle");
			if (songtitle != null) {
				song.songtitle = songtitle.getText();
			}

			DomElement songartist = element.getChild("songartist");
			if (songartist != null) {
				song.songartist = songartist.getText();
			}

			return song;
		}
	}

}
