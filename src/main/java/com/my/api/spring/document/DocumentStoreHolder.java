package com.my.api.spring.document;

import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;

public class DocumentStoreHolder {

    private static class DocumentStoreContainer {
        public static final IDocumentStore store =
                new DocumentStore("http://host.docker.internal:8081", "HSL");

        static {
            store.initialize();
        }
    }

    public static IDocumentStore getStore() {
        return DocumentStoreContainer.store;
    }
}
