package de.diedavids.testery.entity.testcase;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.CaseConversion;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s (%s)|name,code")
@Table(name = "TESTERY_TESTSUITE")
@Entity(name = "testery$Testsuite")
public class Testsuite extends StandardEntity {
    private static final long serialVersionUID = -395314342331930279L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @CaseConversion
    @Length(min = 1, max = 20)
    @NotNull
    @Column(name = "CODE", nullable = false, length = 20)
    protected String code;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "testsuite")
    protected List<Testcase> testcases;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }


}