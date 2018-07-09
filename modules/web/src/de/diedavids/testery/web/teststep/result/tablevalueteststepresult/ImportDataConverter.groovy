package de.diedavids.testery.web.teststep.result.tablevalueteststepresult

interface ImportDataConverter {

    ImportData convert(String content)
    ImportData convert(File file)
}