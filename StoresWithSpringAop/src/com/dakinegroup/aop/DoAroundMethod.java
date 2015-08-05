package com.dakinegroup.aop;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DoAroundMethod implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("****SPRING AOP****: DoAroundMethod: Method Name"+
                    methodInvocation.getMethod().getName());
        System.out.println("****SPRING AOP****: DAM Args: " + 
                    Arrays.toString(methodInvocation.getArguments()));
        System.out.println("****SPRING AOP***: DAM: Executing before..");
        
        try{
            Object result = methodInvocation.proceed();
            System.out.println("****SPRING AOP****: DAM: After method invocation");
            return result;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
