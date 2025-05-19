package com.purplemango.gms.aop;

import com.purplemango.gms.config.MultiTenantMongoDBFactory;
import com.purplemango.gms.repository.MongoBaseRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class DataOperationAspect {
    private String databaseName;
    private String activeProfile;

    public DataOperationAspect(@Value("${spring.data.mongodb.database}")String databaseName,
                               @Value("${spring.profiles.active}") String activeProfile) {
        this.databaseName = databaseName;
        this.activeProfile = activeProfile;
    }

    @Before(value = "@within(com.purplemango.gms.aop.operations.BeforeGlobalMongoOperation)")
    public void beforeGlobalMongoOperation(JoinPoint joinPoint) {
        String databaseName = String.format("%s-%s", activeProfile, ((MongoBaseRepository) joinPoint.getTarget()).getDatabaseName());
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
    }

    @Before(value = "@within(com.purplemango.gms.aop.operations.BeforeTenantMongoOperation)")
    public void beforeTenantMongoOperation(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        if(arguments.length==0){
            return;
        }
        Object object = arguments[0];
        if(object instanceof String || object instanceof MongoBaseRepository.QueryParameters){
            String targetName = String.format("%s", activeProfile);
            ((MongoBaseRepository) joinPoint.getTarget()).setTargetName(targetName);
        }else{
            try {
                Method method =  object.getClass().getMethod("tenant", null);
                String tenantName = (String) method.invoke(object, null);
                String targetName = String.format("%s-%s", activeProfile, tenantName);
                ((MongoBaseRepository) joinPoint.getTarget()).setTargetName(targetName);
                MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(targetName);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Before(value = "@within(com.purplemango.gms.aop.operations.BeforeDefaultMongoOperation) || " + "@annotation(com.purplemango.gms.aop.operations.BeforeDefaultMongoOperation)")
    public void beforeDefaultMongoOperation(JoinPoint joinPoint) {MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);}


}
