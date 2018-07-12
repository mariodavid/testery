package de.diedavids.testery.core

import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.Scripting
import de.diedavids.testery.core.binding.GroovyScriptTeststepBindingSupplier
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.testaction.Testaction
import de.diedavids.testery.entity.teststep.input.JsonTeststepInput
import de.diedavids.testery.entity.teststep.input.TeststepInput
import de.diedavids.testery.entity.teststep.result.JsonTeststepResult
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.entity.teststep.result.TeststepResult
import de.diedavids.testery.entity.teststep.result.TableValueTeststepResult
import de.diedavids.testery.service.TeststepActionExecutor
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class GroovyScriptTeststepActionExecutor implements TeststepActionExecutor {

    DataManager dataManager

    Scripting scripting

    ActionScript actionTestscript

    Teststep teststep

    Metadata metadata

    List<GroovyScriptTeststepBindingSupplier> groovyScriptTeststepBindingSuppliers


    @Override
    void execute(Teststep teststep, TeststepInput teststepInput, TeststepResult teststepResult) {


        scripting.evaluateGroovy(actionGroovyScript, getScriptBinding(teststep, teststepInput, teststepResult))

    }

    protected Binding getScriptBinding(Teststep teststep, TeststepInput teststepInput, TeststepResult teststepResult) {


        Map<String, Object> bindingValues = [:]
        groovyScriptTeststepBindingSuppliers.each {
            bindingValues += it.getBinding(teststep, teststepInput, teststepResult, actionTestscript)
        }

        new Binding(bindingValues)
    }


    protected String getActionGroovyScript() {
        actionTestscript.script.script
    }


    @Override
    boolean supports(Testaction testaction) {
        return true;
    }

    @Override
    String getResultType() {
        return null
    }
}
