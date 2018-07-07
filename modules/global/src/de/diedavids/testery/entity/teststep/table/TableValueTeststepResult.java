package de.diedavids.testery.entity.teststep.table;

import javax.persistence.Entity;
import de.diedavids.testery.entity.teststep.TeststepResult;
import javax.persistence.Column;
import javax.persistence.Lob;

@Entity(name = "testery$TableValueTeststepResult")
public class TableValueTeststepResult extends TeststepResult {
    private static final long serialVersionUID = 6452557141481082457L;

    @Lob
    @Column(name = "EXPECTED_TABLE")
    protected String expectedTable;

    @Lob
    @Column(name = "ACTUAL_TABLE")
    protected String actualTable;

    public void setExpectedTable(String expectedTable) {
        this.expectedTable = expectedTable;
    }

    public String getExpectedTable() {
        return expectedTable;
    }

    public void setActualTable(String actualTable) {
        this.actualTable = actualTable;
    }

    public String getActualTable() {
        return actualTable;
    }


}