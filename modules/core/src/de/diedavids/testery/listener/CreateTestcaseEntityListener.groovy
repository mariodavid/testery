package de.diedavids.testery.listener

import com.haulmont.cuba.core.app.UniqueNumbersAPI
import org.springframework.stereotype.Component
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener
import com.haulmont.cuba.core.EntityManager
import de.diedavids.testery.entity.testcase.Testcase

import javax.inject.Inject

@Component("testery_CreateTestcaseEntityListener")
class CreateTestcaseEntityListener implements BeforeInsertEntityListener<Testcase> {

    @Inject
    protected UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    void onBeforeInsert(Testcase entity, EntityManager entityManager) {
        entity.testcaseId = generateTestcaseId(entity.testsuite.code)
    }

    protected String generateTestcaseId(String testsuiteCode) {
        def testcaseNumber = uniqueNumbersAPI.getNextNumber("TESTCASE_ID")

        "$testsuiteCode-$testcaseNumber"
    }
}