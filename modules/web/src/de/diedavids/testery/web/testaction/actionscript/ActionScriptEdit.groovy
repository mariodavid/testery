package de.diedavids.testery.web.testaction.actionscript

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.FieldGroup
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.testery.entity.testaction.ActionScript
import de.diedavids.testery.entity.teststep.result.TeststepResult
import de.diedavids.testery.web.metadata.MetadataSelector

import javax.inject.Inject

public class ActionScriptEdit extends AbstractEditor<ActionScript> {

    private static final String ENTITY_CLASS_FIELD_NAME = 'resultType'
    @Inject
    FieldGroup fieldGroup

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    MetadataSelector metadataSelector


    @Inject
    Datasource<ActionScript> actionScriptDs

    @Override
    void init(Map<String, Object> params) {
        initEntityClassField()
    }



    private void initEntityClassField() {
        FieldGroup.FieldConfig entityClassFieldConfig = fieldGroup.getField(ENTITY_CLASS_FIELD_NAME)

        LookupField lookupField = componentsFactory.createComponent(LookupField)

        lookupField.setDatasource(actionScriptDs, ENTITY_CLASS_FIELD_NAME)
        lookupField.setOptionsMap(metadataSelector.getEntitiesLookupFieldOptions(TeststepResult))


        entityClassFieldConfig.setComponent(lookupField)
    }

}