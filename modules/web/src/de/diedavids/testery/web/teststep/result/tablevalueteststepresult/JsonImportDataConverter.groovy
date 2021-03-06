package de.diedavids.testery.web.teststep.result.tablevalueteststepresult

import groovy.json.JsonSlurper

class JsonImportDataConverter extends AbstractTextBasedImportDataConverter<Object> {


    @Override
    protected void doConvert(Object entries, ImportData result) {
        entries.each {
            result.columns = getColumns(it)
            addToTableData(result, it as Map<String, Object>)
        }
    }

    @Override
    protected Object parse(String content) {
        new JsonSlurper().parseText(content)
    }

    private List<String> getColumns(it) {
        new ArrayList(it.keySet())
    }

}
