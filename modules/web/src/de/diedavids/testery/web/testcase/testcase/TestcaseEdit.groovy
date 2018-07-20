package de.diedavids.testery.web.testcase.testcase

import com.haulmont.chile.core.datatypes.DatatypeRegistry
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.EntityStates
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.UserSessionSource
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.components.actions.CreateAction
import com.haulmont.cuba.gui.data.CollectionDatasource
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.declarativecontrollers.web.editor.AnnotatableAbstractEditor
import de.diedavids.cuba.attachable.web.WithAttachments
import de.diedavids.testery.entity.testcase.Testcase
import de.diedavids.testery.entity.teststep.Teststep
import de.diedavids.testery.service.TeststepExecutionService

import javax.inject.Inject
import javax.inject.Named

@WithAttachments
class TestcaseEdit extends AnnotatableAbstractEditor<Testcase> {


    @Inject
    CollectionDatasource<Teststep, UUID> stepsDs

    @Inject
    Table<Teststep> stepsTable

    @Inject
    Datasource<Testcase> testcaseDs

    @Inject
    TeststepExecutionService teststepExecutionService

    @Inject
    Metadata metadata

    @Inject
    EntityStates entityStates

    @Inject
    DataManager dataManager

    @Inject
    UserSessionSource userSessionSource

    @Inject
    DatatypeRegistry datatypes

    @Inject
    ComponentsFactory componentsFactory

    @Named("stepsTable.create")
    CreateAction stepsTableCreate

    @Override
    void init(Map<String, Object> params) {
        super.init(params)



        stepsTableCreate.setAfterCommitHandler(new CreateAction.AfterCommitHandler() {
            @Override
            void handle(Entity entity) {
                commit()
            }
        })

        stepsTable.addGeneratedColumn("Result", new Table.ColumnGenerator<Teststep>() {
            @Override
            Component generateCell(Teststep entity) {

                if (entity.result) {

                    LinkButton resultLinkButton = componentsFactory.createComponent(LinkButton.class)
                    resultLinkButton.focusable = false

                    if (entity.result.error || entity.result.stacktrace) {
                        resultLinkButton.icon = "font-icon:WARNING"
                    }
                    resultLinkButton.caption = entity.result.summary

                    resultLinkButton.action = new BaseAction("showError") {
                        @Override
                        void actionPerform(Component component) {
                            super.actionPerform(component)
                            openEditor(entity.result, WindowManager.OpenType.DIALOG)
                        }
                    }

                    return resultLinkButton

                }
            }
        })
    }

    @Override
    protected void postInit() {
        super.postInit()
        stepsTableCreate.setWindowParams([testactionCategory: item.testsuite.testactionCategory])
        stepsTable.sort("position", Table.SortDirection.ASCENDING)
    }


    void openTeststepResult(Teststep item, String columnId) {
        openEditor(item.result, WindowManager.OpenType.DIALOG)
    }

    void executeTeststep() {
        Teststep teststep = stepsTable.getSingleSelected()
        if (teststep.getExecuted() == null || !teststep.getExecuted()) {
            teststepExecutionService.executeTeststep(teststep)
            testcaseDs.refresh()
        } else {
            showNotification("Teststep already executed", Frame.NotificationType.ERROR)
        }

    }


    void resetTeststep() {
        def teststepToReset = stepsTable.singleSelected

        def resultToRemove = teststepToReset.result
        if (resultToRemove) {
            dataManager.remove(resultToRemove)
        }
        teststepToReset.executedAt = null
        teststepToReset.executed = false
        teststepToReset.result = null


        commit()

    }
}