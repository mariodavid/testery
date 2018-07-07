package de.diedavids.testery.service;

import de.diedavids.testery.entity.testaction.Testaction;
import de.diedavids.testery.entity.teststep.Teststep;
import de.diedavids.testery.entity.teststep.TeststepResult;

public interface TeststepActionExecutor {

    void execute(Teststep teststep, TeststepResult teststepResult);

    boolean supports(Testaction testaction);

}
