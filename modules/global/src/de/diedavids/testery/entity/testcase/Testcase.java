package de.diedavids.testery.entity.testcase;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import de.diedavids.testery.entity.teststep.Teststep;

@Listeners("testery_CreateTestcaseEntityListener")
@NamePattern("%s|testcaseId")
@Table(name = "TESTERY_TESTCASE")
@Entity(name = "testery$Testcase")
public class Testcase extends StandardEntity {
    private static final long serialVersionUID = 7816755366823834471L;

    @NotNull
    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TESTSUITE_ID")
    protected Testsuite testsuite;

    @NotNull
    @Column(name = "TESTCASE_ID", nullable = false)
    protected String testcaseId;

    @Column(name = "NAME")
    protected String name;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @NotNull
    @Column(name = "SUCCESSFUL_", nullable = false)
    protected Boolean successful = false;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "testcase")
    protected List<Teststep> steps;

    @Lob
    @Column(name = "COMMENT_")
    protected String comment;

    public void setSteps(List<Teststep> steps) {
        this.steps = steps;
    }

    public List<Teststep> getSteps() {
        return steps;
    }


    public void setTestsuite(Testsuite testsuite) {
        this.testsuite = testsuite;
    }

    public Testsuite getTestsuite() {
        return testsuite;
    }


    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}