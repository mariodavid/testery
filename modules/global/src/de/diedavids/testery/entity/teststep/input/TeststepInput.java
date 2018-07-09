package de.diedavids.testery.entity.teststep.input;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import de.diedavids.testery.entity.testaction.Testaction;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|name")
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TESTERY_TESTSTEP_INPUT")
@Entity(name = "testery$TeststepInput")
public class TeststepInput extends StandardEntity {
    private static final long serialVersionUID = -4237913578967559823L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTACTION_ID")
    protected Testaction testaction;

    public void setTestaction(Testaction testaction) {
        this.testaction = testaction;
    }

    public Testaction getTestaction() {
        return testaction;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}