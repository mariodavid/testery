package de.diedavids.testery.service;

import de.diedavids.testery.entity.teststep.Teststep;


public interface TeststepExecutionService {
    String NAME = "testery_TeststepExecutionService";

    void executeTeststep(Teststep teststep);

}