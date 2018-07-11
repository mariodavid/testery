package de.diedavids.testery.service;

import de.diedavids.testery.entity.testaction.Testaction;
import de.diedavids.testery.entity.teststep.Teststep;
import de.diedavids.testery.entity.teststep.input.TeststepInput;
import de.diedavids.testery.entity.teststep.result.TeststepResult;

public interface TeststepActionExecutor {

    void execute(Teststep teststep, TeststepInput teststepInput, TeststepResult teststepResult);

    boolean supports(Testaction testaction);

    String getResultType();

}
