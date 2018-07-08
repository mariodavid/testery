package de.diedavids.testery.core

import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.Scripting
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.testaction.Testaction
import de.diedavids.testery.entity.teststep.JsonTeststepResult
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.entity.teststep.TeststepResult
import de.diedavids.testery.entity.teststep.table.TableValueTeststepResult
import de.diedavids.testery.service.TeststepActionExecutor
import groovy.json.JsonOutput

class GroovyScriptTeststepActionExecutor implements TeststepActionExecutor {

    DataManager dataManager

    Scripting scripting

    ActionScript actionTestscript

    Teststep teststep

    Metadata metadata


    @Override
    void execute(Teststep teststep, TeststepResult teststepResult) {


        Closure createJsonResult = {Map params ->

            JsonTeststepResult jsonTeststepResult = (JsonTeststepResult) teststepResult
            jsonTeststepResult.summary = params.summary as String

            jsonTeststepResult.expectedValue = mapAsJson(params.expected as Map)
            jsonTeststepResult.actualValue = mapAsJson(params.actual as Map)

        }

        Closure createTableResult = {Map params ->

            TableValueTeststepResult tableValueTeststepResult = (TableValueTeststepResult) teststepResult
            tableValueTeststepResult.summary = params.summary as String

            tableValueTeststepResult.expectedTable = mapAsJson(params.expected as List)
            tableValueTeststepResult.actualTable = mapAsJson(params.actual as List)

        }

        scripting.evaluateGroovy(actionGroovyScript, new Binding(
                dataManager: dataManager,
                teststep: teststep,
                testaction: actionTestscript.action,
                result: teststepResult,
                jsonResult: createJsonResult,
                tableResult: createTableResult
        ))

    }

    protected String mapAsJson(Map data) {
        JsonOutput.prettyPrint(JsonOutput.toJson(data))
    }

    protected String mapAsJson(List data) {
        JsonOutput.prettyPrint(JsonOutput.toJson(data))
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
