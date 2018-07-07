package de.diedavids.testery.entity.teststep;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Lob;

@Entity(name = "testery$SingleValueTeststepResult")
public class SingleValueTeststepResult extends TeststepResult {
    private static final long serialVersionUID = 3662542397647159169L;

    @Lob
    @Column(name = "EXPECTED_VALUE")
    protected String expectedValue;

    @Lob
    @Column(name = "ACTUAL_VALUE")
    protected String actualValue;

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualValue() {
        return actualValue;
    }


}