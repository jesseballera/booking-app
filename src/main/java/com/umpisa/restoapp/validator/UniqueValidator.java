package com.umpisa.restoapp.validator;

import com.umpisa.restoapp.annotations.Unique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE)
public class UniqueValidator implements ConstraintValidator<Unique, Serializable> {
    @NotNull
    EntityManager entityManager;
    String[] fields;
    String primaryKey;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        this.primaryKey = constraintAnnotation.primaryKey();
    }

    @Override
    public boolean isValid(Serializable target, ConstraintValidatorContext context) {
        log.info("start validation...");
        if(entityManager != null) {
            Class entityClass = target.getClass();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<?> root = criteriaQuery.from(entityClass);
            List<Predicate> predicates = new ArrayList(fields.length + 1);

            try {
                PropertyDescriptor desc = new PropertyDescriptor(primaryKey, entityClass);
                Method readMethod = desc.getReadMethod();
                Object propertyValue = readMethod.invoke(target);
                Predicate predicate = criteriaBuilder.notEqual(root.get(primaryKey), propertyValue);
                predicates.add(predicate);

                for (int i = 0; i < fields.length; i++) {
                    String propertyName = fields[i];
                    desc = new PropertyDescriptor(propertyName, entityClass);
                    readMethod = desc.getReadMethod();
                    propertyValue = readMethod.invoke(target);
                    predicate = criteriaBuilder.equal(root.get(propertyName), propertyValue);
                    predicates.add(predicate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
            Query typedQuery = entityManager.createQuery(criteriaQuery);
            List<Object> resultSet = typedQuery.getResultList();
            log.info("found {}", resultSet);
            return resultSet.size() == 0;
        }
        return true;
    }
}
