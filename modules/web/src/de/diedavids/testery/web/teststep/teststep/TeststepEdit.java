package de.diedavids.testery.web.teststep.teststep;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupPickerField;
import de.diedavids.testery.entity.testaction.TestactionCategory;
import de.diedavids.testery.entity.teststep.Teststep;

import javax.inject.Named;
import java.util.Map;

public class TeststepEdit extends AbstractEditor<Teststep> {


    @WindowParam
    TestactionCategory testactionCategory;

    @Named("fieldGroup.action")
    protected LookupPickerField actionField;

    @Named("fieldGroup.input")
    protected LookupPickerField inputField;



    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        actionField.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChanged(ValueChangeEvent e) {
                getItem().setInput(null);
            }
        });
    }
}