package org.bahmni.module.bahmniOfflineSync.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bahmni.module.bahmniOfflineSync.eventLog.RowTransformer;
import org.openmrs.api.context.Context;
import org.openmrs.module.addresshierarchy.AddressHierarchyEntry;
import org.openmrs.module.addresshierarchy.service.AddressHierarchyService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.ConversionUtil;
import org.openmrs.module.webservices.rest.web.representation.Representation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressHierarchyTransformer implements RowTransformer {

    protected Log log = LogFactory.getLog(getClass());

    @Override
    public SimpleObject transform(String url) {
        AddressHierarchyService addressHierarchyService = Context.getService(AddressHierarchyService.class);
        String uuid = getUuidFromUrl(url);
        if (uuid == null) {
            return null;
        }
        try {
            AddressHierarchyEntry addressHierarchyEntry = addressHierarchyService.getAddressHierarchyEntryByUuid(uuid);
            log.error("Address Hierarchy Entity -> "+ addressHierarchyEntry.getUuid());
            SimpleObject simpleObject = new SimpleObject();
            simpleObject.add("", ConversionUtil.convertToRepresentation(addressHierarchyEntry, Representation.FULL));
            return simpleObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUuidFromUrl(String url) {
        Pattern uuidPattern = Pattern.compile("([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})");
        Matcher matcher = uuidPattern.matcher(url);
        return matcher.find() ? matcher.group(0) : null;
    }
}
