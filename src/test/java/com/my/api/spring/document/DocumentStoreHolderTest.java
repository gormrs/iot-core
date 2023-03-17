package com.my.api.spring.document;

import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DocumentStoreHolderTest {

    @Test
    public void testGetStore() {
        IDocumentStore store = DocumentStoreHolder.getStore();
        Assertions.assertNotNull(store, "Store is null");
        Assertions.assertNotNull(((DocumentStore) store).getConventions(), "Store is not initialized");
    }

}
