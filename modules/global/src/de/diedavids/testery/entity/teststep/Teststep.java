package de.diedavids.testery.entity.teststep;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import de.diedavids.testery.entity.testaction.Testaction;
import com.haulmont.chile.core.annotations.Composition;
import de.diedavids.testery.entity.testcase.Testcase;

import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import de.diedavids.testery.entity.teststep.result.TeststepResult;
import de.diedavids.testery.entity.teststep.input.TeststepInput;

@Listeners("testery_CreateTeststepEntityListener")
@Table(name = "TESTERY_TESTSTEP")
@Entity(name = "testery$Teststep")
public class Teststep extends StandardEntity {
    private static final long serialVersionUID = -2879052888945210447L;

    @Lookup(type = LookupType.DROPDOWN, actions = {"open"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TESTCASE_ID")
    protected Testcase testcase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INPUT_ID")
    protected TeststepInput input;

    @NotNull
    @Column(name = "POSITION_", nullable = false)
    protected Integer position;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACTION_ID")
    protected Testaction action;

    @NotNull
    @Column(name = "EXECUTED", nullable = false)
    protected Boolean executed = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXECUTED_AT")
    protected Date executedAt;

    @NotNull
    @Column(name = "SUCCESSFUL_", nullable = false)
    protected Boolean successful = false;

    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "RESULT_ID")
    protected TeststepResult result;

    @Lob
    @Column(name = "COMMENT_")
    protected String comment;

    public void setInput(TeststepInput input) {
        this.input = input;
    }

    public TeststepInput getInput() {
        return input;
    }


    public TeststepResult getResult() {
        return result;
    }

    public void setResult(TeststepResult result) {
        this.result = result;
    }



    public Testaction getAction() {
        return action;
    }

    public void setAction(Testaction action) {
        this.action = action;
    }





    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }


    public void setTestcase(Testcase testcase) {
        this.testcase = testcase;
    }

    public Testcase getTestcase() {
        return testcase;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecutedAt(Date executedAt) {
        this.executedAt = executedAt;
    }

    public Date getExecutedAt() {
        return executedAt;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}