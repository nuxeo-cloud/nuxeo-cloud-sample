package org.nuxeo.cloud.sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.event.EventService;
import org.nuxeo.ecm.core.event.impl.EventListenerDescriptor;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy({"org.nuxeo.cloud.sample.nuxeo-cloud-sample-core"})
public class TestUpdateDescriptionListener {

    protected final List<String> events = Arrays.asList("documentCreated", "documentModified");

    @Inject
    protected EventService s;

    @Inject
    CoreSession session;

    @Test
    public void listenerRegistration() {
        EventListenerDescriptor listener = s.getEventListener("updatedescriptionlistener");
        assertNotNull(listener);
        assertTrue(events.stream().allMatch(listener::acceptEvent));
    }


    @Test
    public void description_is_updated() {
        DocumentModel doc = session.createDocumentModel("/","Test","Note");
        doc.setPropertyValue("dc:title", "My doc");
        doc = session.createDocument(doc);

        doc = session.getDocument(new PathRef("/Test"));
        assertEquals("Nuxeo Cloud document", doc.getPropertyValue("dc:description"));

    }
}
