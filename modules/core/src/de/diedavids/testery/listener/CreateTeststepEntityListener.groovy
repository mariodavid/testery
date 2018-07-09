package de.diedavids.testery.listener

import com.haulmont.cuba.core.app.UniqueNumbersAPI
import org.springframework.stereotype.Component
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener
import com.haulmont.cuba.core.EntityManager
import de.diedavids.testery.entity.teststep.Teststep
import java.lang.Math
import javax.inject.Inject

@Component("testery_CreateTeststepEntityListener")
class CreateTeststepEntityListener implements BeforeInsertEntityListener<Teststep> {



    @Inject
    protected UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    void onBeforeInsert(Teststep entity, EntityManager entityManager) {
        entity.position = generatePosition(entity.testcase.testcaseId)
    }

    protected Integer generatePosition(String testcaseId) {
        def domain = (testcaseId + "_POSITIONS").replace("-","_")
        def position = uniqueNumbersAPI.getNextNumber(domain)

        Math.toIntExact(position)
    }

}