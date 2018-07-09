package de.diedavids.testery.web.teststep.result.tablevalueteststepresult;

import com.haulmont.cuba.core.entity.KeyValueEntity;

import java.io.Serializable;

public interface DataRow extends Serializable {

    java.util.List<String> getColumnNames();
    java.util.Map toMap();
    KeyValueEntity toKevValueEntity();

    boolean isEmpty();

}