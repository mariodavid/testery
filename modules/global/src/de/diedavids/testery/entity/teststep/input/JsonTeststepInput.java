package de.diedavids.testery.entity.teststep.input;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "TESTERY_JSON_TESTSTEP_INPUT")
@Entity(name = "testery$JsonTeststepInput")
public class JsonTeststepInput extends TeststepInput {
    private static final long serialVersionUID = -5093547956378187866L;

    @Lob
    @Column(name = "INPUT_")
    protected String input;

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }


}