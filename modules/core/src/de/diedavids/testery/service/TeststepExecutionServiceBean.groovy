package de.diedavids.testery.service

import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.global.*
import com.haulmont.cuba.core.global.validation.RequiredView
import de.diedavids.testery.core.GroovyScriptTeststepActionExecutor
import de.diedavids.testery.data.SimpleDataLoader
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.teststep.Teststep
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

    @Override
    void executeTeststep(@RequiredView("teststep-view") Teststep teststep) {

        ActionScript actionTestscript = simpleDataLoader.loadByReference(ActionScript, "action", teststep.action, "actionScript-view")
        TeststepInput teststepInput = dataManager.reload(teststep.input, "input-view")

        TeststepActionExecutor teststepActionExecutor = null

        TeststepResult teststepResult = null
        if (actionTestscript) {
            teststepActionExecutor = createScriptTeststepActionExecutor(teststep, actionTestscript, teststepActionExecutor)
            teststepResult = createTeststepResult(actionTestscript.getResultType())
        }
        else {
            teststepActionExecutor = findTeststepActionExecutorBean(teststep)
            teststepResult = createTeststepResult(teststepActionExecutor.getResultType())
        }

        try {
            teststepActionExecutor.execute(teststep, teststepInput, teststepResult);
            teststep = dataManager.reload(teststep, 'teststep-view')
            teststep.result = teststepResult
            teststep.executed = true
            teststep.executedAt = timeSource.currentTimestamp()

            dataManager.commit(teststep)
        }
        catch (Exception e) {
            log.error("Error while executing teststep", e)
            teststep = dataManager.reload(teststep, 'teststep-view')
            teststep.result = teststepResult

            teststepResult.stacktrace = ExceptionUtils.getStackTrace(e)
            teststepResult.summary = "Error"
            dataManager.commit(teststepResult)

            teststep.executed = true
            teststep.executedAt = timeSource.currentTimestamp()

            dataManager.commit(teststep)
        }

    }

    protected GroovyScriptTeststepActionExecutor createScriptTeststepActionExecutor(Teststep teststep, ActionScript actionTestscript, TeststepActionExecutor teststepActionExecutor) {
        teststepActionExecutor = new GroovyScriptTeststepActionExecutor(
                teststep: teststep,
                actionTestscript: actionTestscript,
                dataManager: dataManager,
                scripting: scripting,
                metadata: metadata
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