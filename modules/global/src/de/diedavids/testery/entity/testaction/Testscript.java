package de.diedavids.testery.entity.testaction;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "TESTERY_TESTSCRIPT")
@Entity(name = "testery$Testscript")
public class Testscript extends StandardEntity {
    private static final long serialVersionUID = -3571278772599836439L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @NotNull
    @Lob
    @Column(name = "SCRIPT", nullable = false)
    protected String script;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }


}