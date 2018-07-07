package de.diedavids.testery.core

import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.Scripting
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.testaction.Testaction
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.entity.teststep.TeststepResult
import de.diedavids.testery.service.TeststepActionExecutor

class GroovyScriptTeststepActionExecutor implements TeststepActionExecutor {

    DataManager dataManager

    Scripting scripting

    ActionScript actionTestscript

    Teststep teststep

    Metadata metadata


    @Override
    void execute(Teststep teststep, TeststepResult teststepResult) {

        scripting.evaluateGroovy(actionGroovyScript, new Binding(
                dataManager: dataManager,
                teststep: teststep,
                testaction: actionTestscript.action,
                result: teststepResult
        ))

    }

    protected String getActionGroovyScript() {
        """
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata

${actionTestscript.script.script}
"""


    }

    @Override
    boolean supports(Testaction testaction) {
        return true;
    }
}
