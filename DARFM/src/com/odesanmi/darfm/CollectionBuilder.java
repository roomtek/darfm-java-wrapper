package com.odesanmi.darfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class CollectionBuilder {

	private CollectionBuilder() {
	}

	private static <T> ArtifactFactory<T> getFactory(Class<T> itemClass) {
		return ArtifactFactoryBuilder.getFactoryBuilder().getFactory(itemClass);
	}

	public static <T> Collection<T> buildCollection(DomElement element,
			Class<T> itemClass) {
		return buildCollection(element, getFactory(itemClass));
	}

	public static <T> Collection<T> buildCollection(DomElement element,
			ArtifactFactory<T> factory) {
		if (element == null)
			return Collections.emptyList();
		Collection<DomElement> children = element.getChildren();
		Collection<T> collection = new ArrayList<T>(children.size());
		for (DomElement child : children) {
			collection.add(factory.parseElement(child));
		}
		return collection;
	}

}
