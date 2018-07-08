package de.diedavids.testery.web.teststep.table.tablevalueteststepresult;

import java.io.Serializable;
import java.util.List;

public interface ImportData extends Serializable {

    List<DataRow> getRows();
    List<String> getColumns();

}