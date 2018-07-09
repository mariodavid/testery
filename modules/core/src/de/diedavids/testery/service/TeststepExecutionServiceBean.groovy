package de.diedavids.testery.service

import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.global.*
import de.diedavids.testery.core.GroovyScriptTeststepActionExecutor
import de.diedavids.testery.data.SimpleDataLoader
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.teststep.Teststep
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
    void executeTeststep(Teststep teststep) {

        ActionScript actionTestscript = simpleDataLoader.loadByReference(ActionScript, "action", teststep.action, "actionScript-view")

        TeststepActionExecutor teststepActionExecutor = findTeststepActionExecutor(teststep, actionTestscript);


        TeststepResult teststepResult = createTeststepResult(actionTestscript)
        try {
            teststepActionExecutor.execute(teststep, teststepResult);
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
            dataManager.commit(teststepResult)

            teststep.executed = true
            teststep.executedAt = timeSource.currentTimestamp()

            dataManager.commit(teststep)
        }

    }


    protected TeststepResult createTeststepResult(ActionScript actionTestscript) {
        TeststepResult teststepResult = metadata.create(actionTestscript.resultType)

        teststepResult
    }


    private TeststepActionExecutor findTeststepActionExecutor(Teststep teststep, ActionScript actionScript) {

        if (actionScript) {
            new GroovyScriptTeststepActionExecutor(
                    teststep: teststep,
                    actionTestscript: actionScript,
                    dataManager: dataManager,
                    scripting: scripting,
                    metadata: metadata
            )

        }

        else {

            def teststepActionExecutors = AppBeans.getAll(TeststepActionExecutor)

            TeststepActionExecutor executor = null
            teststepActionExecutors.find { k, v ->
                if (v.supports(teststep.getAction())) {
                    executor = v
                }
            }

            executor
        }




    }
}