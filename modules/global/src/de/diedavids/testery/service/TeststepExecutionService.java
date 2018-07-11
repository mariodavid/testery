package de.diedavids.testery.service;

import com.haulmont.cuba.core.global.validation.RequiredView;
import de.diedavids.testery.entity.teststep.Teststep;


public interface TeststepExecutionService {
    String NAME = "testery_TeststepExecutionService";

    void executeTeststep(@RequiredView("teststep-view") Teststep teststep);

}