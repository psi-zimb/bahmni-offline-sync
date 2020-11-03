package org.bahmni.module.bahmniOfflineSync.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bahmni.module.bahmniOfflineSync.eventLog.RowTransformer;
import org.openmrs.api.context.Context;
import org.openmrs.module.addresshierarchy.AddressHierarchyEntry;
import org.openmrs.module.addresshierarchy.AddressHierarchyLevel;
import org.openmrs.module.addresshierarchy.service.AddressHierarchyService;
import org.openmrs.module.emrapi.concept.EmrConceptService;
import org.openmrs.module.webservices.rest.SimpleObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressHierarchyTransformer implements RowTransformer {

    protected Log log = LogFactory.getLog(getClass());

    @Override
    public SimpleObject transform(String url) {
        AddressHierarchyService addressHierarchyService = Context.getService(AddressHierarchyService.class);
        EmrConceptService conceptService = Context.getService(EmrConceptService.class);
        log.error("url is "+ url);
        String uuid = getUuidFromUrl(url);
        if (uuid == null) {
            return null;
        }
        try {
            AddressHierarchyEntry addressHierarchyEntry = addressHierarchyService.getAddressHierarchyEntryByUuid(uuid);
            if(addressHierarchyEntry != null) {
                StringBuilder sbr = new StringBuilder();
                sbr.append("{");
                sbr.append("\"addressHierarchyEntryId\":" + addressHierarchyEntry.getAddressHierarchyEntryId() + ",");
                sbr.append("\"name\":\"" + addressHierarchyEntry.getName() + "\",");
                sbr.append(" \"levelId\":" + addressHierarchyEntry.getLevel().getId() + ",");
                sbr.append("\"addressHierarchyLevel\": {");
                sbr.append("\"levelId\":" + addressHierarchyEntry.getLevel().getId() + ",");
                sbr.append("\"name\":\"" + addressHierarchyEntry.getLevel().getName() + "\",");
                sbr.append("\"parentLevelId\":" + getParentLevelID(addressHierarchyEntry.getLevel().getParent()) + ",");
                sbr.append("\"addressField\":\"" + addressHierarchyEntry.getLevel().getAddressField().getName() + "\",");
                sbr.append("\"required\":" + addressHierarchyEntry.getLevel().getRequired() + ",");
                sbr.append("\"uuid\":\"" + addressHierarchyEntry.getLevel().getUuid() + "\",");
                sbr.append("\"id\":" + addressHierarchyEntry.getLevel().getId() + "},");
                sbr.append(" \"parentId\":" + getParentID(addressHierarchyEntry.getParent()) + ",");
                sbr.append(" \"userGeneratedId\":" + addressHierarchyEntry.getUserGeneratedId() + ",");
                sbr.append(" \"uuid\":\"" + addressHierarchyEntry.getUuid()+"\"");
                sbr.append("}");
                SimpleObject simpleObject = new SimpleObject();
                simpleObject.add("address",  SimpleObject.parseJson(sbr.toString()));
                log.error("converted response ->" + simpleObject.get("address"));
                return simpleObject.get("address");
            }
            else
                return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getUuidFromUrl(String url) {
        Pattern uuidPattern = Pattern.compile("([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})");
        Matcher matcher = uuidPattern.matcher(url);
        return matcher.find() ? matcher.group(0) : null;
    }

    private Integer getParentLevelID(AddressHierarchyLevel parent)
    {
        return parent != null ? parent.getLevelId() : null ;
    }

    private Integer getParentID(AddressHierarchyEntry parent)
    {
        return parent != null ? parent.getId() : null ;
    }
}
