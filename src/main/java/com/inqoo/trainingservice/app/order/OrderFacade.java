package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.absence.Absence;
import com.inqoo.trainingservice.app.absence.AbsenceService;

public class OrderFacade {
    private AbsenceService absenceService;

    public Absence createAbsence(Absence absence) {
        return absenceService.saveNewAbsence(absence);
    }

}
