package de.diedavids.testery.core.binding

import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.entity.teststep.input.TeststepInput
import de.diedavids.testery.entity.teststep.result.TeststepResult

interface GroovyScriptTeststepBindingSupplier {

    Map<String, Object> getBinding(Teststep teststep, TeststepInput teststepInput, TeststepResult teststepResult, ActionScript actionTestscript)
}