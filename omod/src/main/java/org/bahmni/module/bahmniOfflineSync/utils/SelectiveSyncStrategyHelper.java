package org.bahmni.module.bahmniOfflineSync.utils;

import org.bahmni.module.bahmniOfflineSync.eventLog.EventLog;
import org.openmrs.Patient;

public class SelectiveSyncStrategyHelper {

    public EventLog setAddressHierarchy(Patient patient, EventLog eventLog)
    {
        if(patient.getPerson() != null && patient.getPerson().getPersonAddress() != null) {
            eventLog.setProvince(patient.getPerson().getPersonAddress().getStateProvince());
            eventLog.setDistrict(patient.getPerson().getPersonAddress().getAddress1());
            eventLog.setFacility(patient.getPerson().getPersonAddress().getAddress2());
        }
        return eventLog;
    }
}
