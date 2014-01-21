package com.odesanmi.darfm;

interface ArtifactFactory<T> {

	public T parseElement(DomElement element);

}
