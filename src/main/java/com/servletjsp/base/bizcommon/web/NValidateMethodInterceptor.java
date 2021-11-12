package com.servletjsp.base.bizcommon.web;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.servletjsp.base.common.NValidateMethod;
import com.servletjsp.base.common.Operator;
import com.servletjsp.base.web.common.AbstractWebBaseBackingBean;

@Interceptor
@Dependent
@NValidateMethod
public class NValidateMethodInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Operator          operator;

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {

        AbstractWebBaseBackingBean _backingBean = (AbstractWebBaseBackingBean) invocationContext.getTarget();

        if (_backingBean.validate(operator.obtainLocale(), invocationContext.getMethod().getName())) {
            // Valid.
            return invocationContext.proceed();
        } else {
            return null;
        }
    }

}
