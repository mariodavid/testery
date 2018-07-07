package de.diedavids.testery.entity.testaction;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;

@Table(name = "TESTERY_ACTION_SCRIPT")
@Entity(name = "testery$ActionScript")
public class ActionScript extends StandardEntity {
    private static final long serialVersionUID = 5570751958403463966L;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACTION_ID")
    protected Testaction action;

    @NotNull
    @Column(name = "RESULT_TYPE", nullable = false)
    protected String resultType;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SCRIPT_ID")
    protected Testscript script;

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultType() {
        return resultType;
    }


    public void setScript(Testscript script) {
        this.script = script;
    }

    public Testscript getScript() {
        return script;
    }


    public Testaction getAction() {
        return action;
    }

    public void setAction(Testaction action) {
        this.action = action;
    }




}