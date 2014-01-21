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
 * Bean that contains information for artist names returned from search result
 * of the autocomplete API Method names are similar to the dar.fm API method
 * names
 * 
 * @see www.dar.fm
 * 
 * @author Temitayo Odesanmi
 */
public class Artist {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	static final ArtifactFactory<Artist> FACTORY = new ArtistFactory();

	public static Collection<Artist> autocompleteArtist(String query) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.autocomplete(query);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Artist.class);

	}

	private static class ArtistFactory implements ArtifactFactory<Artist> {
		@Override
		public Artist parseElement(DomElement element) {
			Artist artist = new Artist();
			artist.name = element.getText();
			return artist;
		}
	}
}
