package de.diedavids.testery.web.testcase.testcase;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import de.diedavids.testery.entity.testcase.Testcase;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class TestcaseBrowse extends AbstractLookup {


    @Named("testcasesTable.create")
    protected CreateAction testcasesTableCreate;


    @Inject
    protected GroupTable<Testcase> testcasesTable;

    @Override
    public void init(Map<String, Object> params) {
        testcasesTableCreate.setWindowId("testery$Testcase.create");
        testcasesTableCreate.setOpenType(WindowManager.OpenType.DIALOG);
        testcasesTableCreate.setAfterCommitHandler(entity -> {
            showNotification("Testcase created...", NotificationType.TRAY);
            openEditor(entity, WindowManager.OpenType.THIS_TAB);
        });
    }


    @Override
    public void ready() {
        testcasesTable.sort("testcaseId", Table.SortDirection.DESCENDING);
    }

}