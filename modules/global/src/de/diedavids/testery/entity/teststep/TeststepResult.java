package de.diedavids.testery.entity.teststep;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Lob;

@NamePattern("%s|summary")
@Table(name = "TESTERY_TESTSTEP_RESULT")
@Entity(name = "testery$TeststepResult")
public class TeststepResult extends StandardEntity {
    private static final long serialVersionUID = 3645154529605300892L;

    @NotNull
    @Column(name = "SUMMARY", nullable = false)
    protected String summary;

    @Lob
    @Column(name = "ERROR")
    protected String error;

    @Lob
    @Column(name = "STACKTRACE")
    protected String stacktrace;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getStacktrace() {
        return stacktrace;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }


}