package de.diedavids.testery.entity.testaction;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "TESTERY_TESTACTION_CATEGORY")
@Entity(name = "testery$TestactionCategory")
public class TestactionCategory extends StandardEntity {
    private static final long serialVersionUID = -8982514570052488956L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "CODE")
    protected String code;

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