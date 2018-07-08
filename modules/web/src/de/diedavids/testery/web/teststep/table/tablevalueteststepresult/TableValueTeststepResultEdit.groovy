package de.diedavids.testery.web.teststep.table.tablevalueteststepresult

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.BoxLayout
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.testery.entity.teststep.table.TableValueTeststepResult

import javax.inject.Inject

class TableValueTeststepResultEdit extends AbstractEditor<TableValueTeststepResult> {

    @Inject
    BoxLayout expectedTableContainer

    @Inject
    BoxLayout actualTableContainer

    @Inject
    ComponentsFactory componentsFactory


    @Override
    protected void postInit() {
        super.postInit()
        displayTable(item.expectedTable, expectedTableContainer)
        displayTable(item.actualTable, actualTableContainer)
    }

    protected void displayTable(String dataToConvert, BoxLayout containerToUse) {
        createDynamicTableCreator().createTable(convertToImportData(dataToConvert), containerToUse)
    }

    private DynamicTableCreator createDynamicTableCreator() {
        new DynamicTableCreator(
            dsContext: dsContext,
            frame: frame,
            componentsFactory: componentsFactory
        )
    }


    protected ImportData convertToImportData(String dataToConvert) {
        new JsonImportDataConverter().convert(dataToConvert)
    }
}