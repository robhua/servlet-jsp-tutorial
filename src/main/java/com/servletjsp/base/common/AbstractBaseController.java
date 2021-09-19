package com.servletjsp.base.common;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.servletjsp.base.common.constants.SystemErrorCodeConstatns;
import com.servletjsp.base.common.expceitons.AbstractApplicationRuntimeException;
import com.servletjsp.base.common.expceitons.ApplicationCheckException;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;

/**
 * AbstractBaseController
 * 
 * @author Admin
 *
 */
public abstract class AbstractBaseController implements Serializable {

    private static final long serialVersionUID = 1L;

    private ValueResult<?>    valueResult;

    /**
     * Do service
     * 
     * @param requestDTO {@link AbstractBaseRequestDTO}
     */
    public void doService(AbstractBaseRequestDTO requestDTO) {
        try {
            Method _method = findMethod(requestDTO.getActionName(), requestDTO.getChanceTypeCode());
            if (_method != null) {
                _method.invoke(this, requestDTO);
            }
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AbstractApplicationRuntimeException) {
                throw (AbstractApplicationRuntimeException) e.getCause();
            } else {
                throw new ApplicationCheckException(SystemErrorCodeConstatns.SYSTEM, e);
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new ApplicationCheckException(SystemErrorCodeConstatns.SYSTEM, e);
        }
    }

    /**
     * Find action method
     * 
     * @param actionName     The action name
     * @param chanceTypeCode The Chance Type Code
     * @return {@link Method}
     */
    private Method findMethod(String actionName, String chanceTypeCode) {
        /*
         * In case of system Chance Type not defined, throw an system Chance Type
         * Exception
         */
        if (StringUtils.isBlank(chanceTypeCode)) {
            throw new ApplicationCheckException(SystemErrorCodeConstatns.SYSTEM_LOGIC,
                    new IllegalArgumentException("Chance Type Code Invalid"));
        }

        List<Method> _listOfMethodApplyForRegion = findMethodApplyForChanceType(chanceTypeCode);
        for (Method _method : _listOfMethodApplyForRegion) {
            _method.setAccessible(true);
            Annotation _annotation = _method.getAnnotation(NController.class);
            if (_annotation != null) {
                String _actionName = ((NController) _annotation).action();
                if (StringUtils.isBlank(_actionName) && _actionName.equalsIgnoreCase(actionName)) {
                    return _method;
                }
            }

        }

        /* In case of action method not found, throw a system exception */
        throw new ApplicationSystemException(SystemErrorCodeConstatns.SYSTEM_LOGIC,
                new IllegalArgumentException("Action method not found"));
    }

    /**
     * Find method apply for Chance Type Code;
     * 
     * @param chanceTypeCode The Chance Type Code
     * @return List<Method>
     */
    private List<Method> findMethodApplyForChanceType(String chanceTypeCode) {
        List<Method> _listOfActionMethods = new ArrayList<Method>();
        if (StringUtils.isNotBlank(chanceTypeCode)) {
            List<Method> _listOfDeclaredMethods = new ArrayList<Method>(Arrays.asList(getClass().getDeclaredMethods()));
            for (Method _method : _listOfDeclaredMethods) {
                if (_method.isAnnotationPresent(NController.class)) {
                    Annotation _annotation = _method.getAnnotation(NController.class);
                    if (_annotation != null) {
                        String _applyForChanceType = ((NController)_annotation).apply();
                        if (StringUtils.isNotBlank(_applyForChanceType)) {
                            _listOfActionMethods.add(_method);
                        }
                    }
                }

            }
        }
        return _listOfActionMethods;
    }

    /**
     * @return the valueResult
     */
    public ValueResult<?> getValueResult() {
        return valueResult;
    }

    /**
     * @param valueResult the valueResult to set
     */
    public void setValueResult(ValueResult<?> valueResult) {
        this.valueResult = valueResult;
    }

}
