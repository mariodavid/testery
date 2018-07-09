package de.diedavids.testery.web.teststep.result.tablevalueteststepresult

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.BoxLayout
import com.haulmont.cuba.gui.components.TabSheet
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.testery.entity.teststep.result.TableValueTeststepResult

import javax.inject.Inject

class TableValueTeststepResultEdit extends AbstractEditor<TableValueTeststepResult> {

    @Inject
    BoxLayout expectedTableContainer

    @Inject
    BoxLayout actualTableContainer


    @Inject
    TabSheet expectedActualTabSheet

    @Inject
    ComponentsFactory componentsFactory


    @Override
    protected void postInit() {
        super.postInit()
        def tableCreated = []
        tableCreated << displayTable(item.expectedTable, expectedTableContainer , "expectedTableTab")
        tableCreated << displayTable(item.actualTable, actualTableContainer, "actualTableTab")

        if (tableCreated.every {!it}) {
            expectedActualTabSheet.visible = false
        }

    }

    protected boolean displayTable(String dataToConvert, BoxLayout containerToUse, String tabId) {
        def importData = convertToImportData(dataToConvert)
        if (importData) {
            createDynamicTableCreator().createTable(importData, containerToUse)
            true
        }
        else {
            expectedActualTabSheet.getTab(tabId).visible = false
            false
        }
    }

    private DynamicTableCreator createDynamicTableCreator() {
        new DynamicTableCreator(
                dsContext: dsContext,
                frame: frame,
                componentsFactory: componentsFactory
        )
    }


    protected ImportData convertToImportData(String dataToConvert) {
        if (dataToConvert) {
            new JsonImportDataConverter().convert(dataToConvert)
        }
        else {
            null
        }
    }
}