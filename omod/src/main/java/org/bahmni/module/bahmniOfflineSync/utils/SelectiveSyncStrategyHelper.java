package org.bahmni.module.bahmniOfflineSync.utils;

import org.bahmni.module.bahmniOfflineSync.eventLog.EventLog;
import org.openmrs.Patient;

public class SelectiveSyncStrategyHelper {

    public static EventLog setAddressHierarchy(Patient patient, EventLog eventLog)
    {
        if(patient.getPerson() != null && patient.getPerson().getPersonAddress() != null) {
            eventLog.setFilter1(patient.getPerson().getPersonAddress().getStateProvince());
            eventLog.setFilter2(patient.getPerson().getPersonAddress().getCityVillage());
            eventLog.setFilter3(patient.getPerson().getPersonAddress().getAddress2());
            eventLog.setFilter4(patient.getPerson().getPersonAddress().getAddress3());
            eventLog.setFilter5(patient.getPerson().getPersonAddress().getAddress4());
            eventLog.setFilter6(patient.getPerson().getPersonAddress().getAddress5());
            eventLog.setFilter7(patient.getPerson().getPersonAddress().getAddress6());
            eventLog.setFilter8(patient.getPerson().getPersonAddress().getAddress7());
            eventLog.setFilter9(patient.getPerson().getPersonAddress().getAddress8());
            eventLog.setFilter10(patient.getPerson().getPersonAddress().getAddress9());
        }
        return eventLog;
    }
}
