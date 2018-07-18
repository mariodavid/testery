package de.diedavids.testery.entity.testaction;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.CaseConversion;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Lob;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

@NamePattern("%s|name")
@Table(name = "TESTERY_TESTACTION")
@Entity(name = "testery$Testaction")
public class Testaction extends StandardEntity {
    private static final long serialVersionUID = -3710544235254631479L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @CaseConversion
    @NotNull
    @Column(name = "CODE", nullable = false)
    protected String code;

    @Lob
    @Column(name = "DESRCIPTION")
    protected String desrciption;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected TestactionCategory category;

    public void setCategory(TestactionCategory category) {
        this.category = category;
    }

    public TestactionCategory getCategory() {
        return category;
    }


    public void setDesrciption(String desrciption) {
        this.desrciption = desrciption;
    }

    public String getDesrciption() {
        return desrciption;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}