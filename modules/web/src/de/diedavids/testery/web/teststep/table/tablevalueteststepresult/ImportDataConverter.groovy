package de.diedavids.testery.web.teststep.table.tablevalueteststepresult

interface ImportDataConverter {

    ImportData convert(String content)
    ImportData convert(File file)
}