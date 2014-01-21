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

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
final class ArtifactFactoryBuilder {

	private static final ArtifactFactoryBuilder INSTANCE = new ArtifactFactoryBuilder();

	private Map<Class, ArtifactFactory> factories = new HashMap<Class, ArtifactFactory>();

	private ArtifactFactoryBuilder() {
		useFactory(Station.class, Station.FACTORY);
		useFactory(Song.class, Song.FACTORY);
		useFactory(SongArt.class, SongArt.FACTORY);
		useFactory(Artist.class, Artist.FACTORY);
	}

	public static ArtifactFactoryBuilder getFactoryBuilder() {
		return INSTANCE;
	}

	public <T> void useFactory(Class<T> itemClass, ArtifactFactory<T> factory) {
		factories.put(itemClass, factory);
	}

	@SuppressWarnings("unchecked")
	public <T> ArtifactFactory<T> getFactory(Class<T> itemClass) {
		return factories.get(itemClass);
	}
}
