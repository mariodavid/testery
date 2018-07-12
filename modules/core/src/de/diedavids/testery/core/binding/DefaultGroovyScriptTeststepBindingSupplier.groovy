package de.diedavids.testery.core.binding

import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.TimeSource
import de.diedavids.testery.data.SimpleDataLoader
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.entity.teststep.input.JsonTeststepInput
import de.diedavids.testery.entity.teststep.input.TeststepInput
import de.diedavids.testery.entity.teststep.result.JsonTeststepResult
import de.diedavids.testery.entity.teststep.result.TableValueTeststepResult
import de.diedavids.testery.entity.teststep.result.TeststepResult
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

import javax.inject.Inject
import javax.sql.DataSource


@Slf4j
@Component("testery_DefaultGroovyScriptTeststepBindingSupplier")
class DefaultGroovyScriptTeststepBindingSupplier implements GroovyScriptTeststepBindingSupplier {

    @Inject
    SimpleDataLoader simpleDataLoader

    @Inject
    Persistence persistence

    @Inject
    DataManager dataManager

    @Inject
    TimeSource timeSource

    @Inject
    Metadata metadata


    @Override
    Map<String, Object> getBinding(Teststep teststep, TeststepInput teststepInput, TeststepResult teststepResult, ActionScript actionTestscript) {


        Closure createJsonResult = { Map params ->

            JsonTeststepResult jsonTeststepResult = (JsonTeststepResult) teststepResult
            jsonTeststepResult.summary = params.summary as String

            jsonTeststepResult.expectedValue = mapAsJsonMap(params.expected)
            jsonTeststepResult.actualValue = mapAsJsonMap(params.actual)

        }

        Closure createTableResult = { Map params ->

            TableValueTeststepResult tableValueTeststepResult = (TableValueTeststepResult) teststepResult
            tableValueTeststepResult.summary = params.summary as String

            if (params.expected) {
                tableValueTeststepResult.expectedTable = mapAsJsonList(params.expected)
            }
            if (params.actual) {
                tableValueTeststepResult.actualTable = mapAsJsonList(params.actual)
            }
        }


        Closure getBean = { String name ->
            AppBeans.get(name)
        }

        Closure getSql = { String name = null ->
            def dataSource = name ? persistence.getDataSource(name) : persistence.getDataSource()
            new Sql(dataSource)
        }

        [
                dataManager     : dataManager,
                persistence     : persistence,
                simpleDataLoader: simpleDataLoader,
                metadata        : metadata,
                teststep        : teststep,
                input           : getTeststepInput(teststepInput),
                actionTestscript: actionTestscript,
                result          : teststepResult,
                jsonResult      : createJsonResult,
                tableResult     : createTableResult,
                getBean         : getBean,
                getSql          : getSql
        ]
    }


    def getTeststepInput(TeststepInput teststepInput) {

        if (teststepInput instanceof JsonTeststepInput) {
            try {
                new JsonSlurper().parseText(teststepInput.input)
            }
            catch (Exception e) {
                log.error("Could not parse Teststep Input as JSON", e)
                throw new JsonTeststepInputParseException("Could not parse Teststep Input as JSON", e);
            }
        } else {
            teststepInput
        }
    }


    protected String mapAsJsonMap(def data) {

        try {
            if (data instanceof Map) {
                JsonOutput.prettyPrint(JsonOutput.toJson(data))
            } else {
                data
            }
        }
        catch (Exception e) {
            log.warn("Could not parse response as JSON", e)
            data.toString()
        }

    }

    protected String mapAsJsonList(def data) {

        try {
            if (data instanceof Map) {
                JsonOutput.prettyPrint(JsonOutput.toJson(data))
            } else {
                data
            }
        }
        catch (Exception e) {
            log.warn("Could not parse response as JSON", e)
            data.toString()
        }

    }

}