package de.diedavids.testery.service

import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.global.*
import com.haulmont.cuba.core.global.validation.RequiredView
import de.diedavids.testery.core.GroovyScriptTeststepActionExecutor
import de.diedavids.testery.core.binding.GroovyScriptTeststepBindingSupplier
import de.diedavids.testery.core.binding.JsonTeststepInputParseException
import de.diedavids.testery.data.SimpleDataLoader
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.entity.teststep.input.JsonTeststepInput
import de.diedavids.testery.entity.teststep.input.TeststepInput
import de.diedavids.testery.entity.teststep.result.TeststepResult
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.stereotype.Service

import javax.inject.Inject

@Slf4j
@Service(TeststepExecutionService.NAME)
class TeststepExecutionServiceBean implements TeststepExecutionService {


    @Inject
    SimpleDataLoader simpleDataLoader

    @Inject
    Persistence persistence

    @Inject
    Scripting scripting

    @Inject
    DataManager dataManager

    @Inject
    TimeSource timeSource

    @Inject
    Metadata metadata

    @Inject
    List<GroovyScriptTeststepBindingSupplier> groovyScriptTeststepBindingSuppliers

    @Override
    void executeTeststep(@RequiredView("teststep-view") Teststep teststep) {

        ActionScript actionTestscript = findActionScriptForTeststep(teststep)
        TeststepActionExecutor teststepActionExecutor = findTeststepActionExecutor(actionTestscript, teststep)
        TeststepResult teststepResult = createTeststepResultInstance(actionTestscript, teststepActionExecutor)
        TeststepInput teststepInput = loadTeststepInput(teststep)

        doExecute(teststepActionExecutor, teststep, teststepInput, teststepResult)

    }

    protected ActionScript findActionScriptForTeststep(Teststep teststep) {
        simpleDataLoader.loadByReference(ActionScript, "action", teststep.action, "actionScript-view")
    }

    protected TeststepInput loadTeststepInput(Teststep teststep) {
        dataManager.reload(teststep.input, "input-view")
    }

    protected TeststepResult createTeststepResultInstance(ActionScript actionTestscript, TeststepActionExecutor teststepActionExecutor) {
        TeststepResult teststepResult = null
        if (actionTestscript) {
            teststepResult = createTeststepResult(actionTestscript.getResultType())
        } else {
            teststepResult = createTeststepResult(teststepActionExecutor.getResultType())
        }
        teststepResult
    }

    protected TeststepActionExecutor findTeststepActionExecutor(ActionScript actionTestscript, Teststep teststep) {
        TeststepActionExecutor teststepActionExecutor = null
        if (actionTestscript) {
            teststepActionExecutor = createScriptTeststepActionExecutor(teststep, actionTestscript, teststepActionExecutor)
        } else {
            teststepActionExecutor = findTeststepActionExecutorBean(teststep)
        }
        teststepActionExecutor
    }

    protected void doExecute(TeststepActionExecutor teststepActionExecutor, Teststep teststep, TeststepInput teststepInput, TeststepResult teststepResult) {
        try {
            teststepActionExecutor.execute(teststep, teststepInput, teststepResult);
            teststep = dataManager.reload(teststep, 'teststep-view')
            teststep.result = teststepResult
            teststep.executed = true
            teststep.executedAt = timeSource.currentTimestamp()

            dataManager.commit(teststep)
        }
        catch (JsonTeststepInputParseException e) {
            log.error("Error while executing teststep", e)
            teststep = reloadTeststepResult(teststep, teststepResult)
            saveTeststepResult(e, teststepResult, teststepInput as JsonTeststepInput)
            saveTeststep(teststep)
        }
        catch (Exception e) {
            log.error("Error while executing teststep", e)
            teststep = reloadTeststepResult(teststep, teststepResult)
            saveTeststepResult(e, teststepResult)
            saveTeststep(teststep)
        }
    }

    protected Teststep reloadTeststepResult(Teststep teststep, TeststepResult teststepResult) {
        teststep = dataManager.reload(teststep, 'teststep-view')
        teststep.result = teststepResult
        teststep
    }

    protected void saveTeststep(Teststep teststep) {
        teststep.executed = true
        teststep.executedAt = timeSource.currentTimestamp()

        dataManager.commit(teststep)
    }

    protected void saveTeststepResult(JsonTeststepInputParseException e, TeststepResult teststepResult, JsonTeststepInput jsonTeststepInput) {
        teststepResult.stacktrace = ExceptionUtils.getStackTrace(e)

        teststepResult.error = """Error while parsing JSON Teststep Input:
-------------------------------
${jsonTeststepInput.input}
"""
        teststepResult.summary = "Error"
        dataManager.commit(teststepResult)
    }

    protected void saveTeststepResult(Exception e, TeststepResult teststepResult) {
        teststepResult.stacktrace = ExceptionUtils.getStackTrace(e)
        teststepResult.summary = "Error"
        teststepResult.error = "Error while executing teststep: ${e.message}"
        dataManager.commit(teststepResult)
    }

    protected GroovyScriptTeststepActionExecutor createScriptTeststepActionExecutor(Teststep teststep, ActionScript actionTestscript, TeststepActionExecutor teststepActionExecutor) {
        teststepActionExecutor = new GroovyScriptTeststepActionExecutor(
                teststep: teststep,
                actionTestscript: actionTestscript,
                dataManager: dataManager,
                scripting: scripting,
                metadata: metadata,
                groovyScriptTeststepBindingSuppliers: groovyScriptTeststepBindingSuppliers
        )
        teststepActionExecutor
    }

    protected TeststepActionExecutor findTeststepActionExecutorBean(Teststep teststep) {
        TeststepActionExecutor result = null
        def teststepActionExecutors = AppBeans.getAll(TeststepActionExecutor)

        teststepActionExecutors.find { k, v ->
            if (v.supports(teststep.getAction())) {
                result = v
            }
        }

        result
    }


    protected TeststepResult createTeststepResult(String resultType) {
        TeststepResult teststepResult = metadata.create(resultType)
        teststepResult
    }


}