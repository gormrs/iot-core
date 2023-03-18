package com.my.api.spring.document;

import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;

public class DocumentStoreHolder {

    private static class DocumentStoreContainer {
        public static final String SERVER_URL = "http://localhost:8081";
        public static final String DATABASE_NAME = "HSL";

        public static final IDocumentStore store =
                new DocumentStore(SERVER_URL, DATABASE_NAME);

        static {
            store.initialize();
        }
    }

    public static IDocumentStore getStore() {
        return DocumentStoreContainer.store;
    }
}
