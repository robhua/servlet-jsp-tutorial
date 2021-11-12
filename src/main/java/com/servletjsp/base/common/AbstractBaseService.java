package com.servletjsp.base.common;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.servletjsp.base.common.constants.SystemErrorCodeConstants;
import com.servletjsp.base.common.expceitons.ApplicationCheckException;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;

public abstract class AbstractBaseService implements Serializable {

    private static final long serialVersionUID = 1L;

    private ValueResult<?>    valueResult;

    /**
     * Do service.
     * 
     * @param requestDTO {@link AbstractBaseRequestDTO}
     */
    public void doService(AbstractBaseRequestDTO requestDTO) {
        try {
            Method _method = findMethod(requestDTO.getChanceTypeCode());
            if (_method != null) {
                _method.invoke(this, requestDTO);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ApplicationCheckException(SystemErrorCodeConstants.SYSTEM, e);
        }
    }

    /**
     * Find action method
     * 
     * @param chanceTypeCode The chance tyep code
     * @return {@link Method}
     */
    private Method findMethod(String chanceTypeCode) {
        /* In case of system Chance Type not defined, throw an system Chance Type Exception */
        if (StringUtils.isBlank(chanceTypeCode)) {
            throw new ApplicationCheckException(SystemErrorCodeConstants.SYSTEM_LOGIC,
                    new IllegalArgumentException("Chance Type Code Invalid"));
        }

        List<Method> _listOfDeclaredMethods = new ArrayList<Method>(Arrays.asList(getClass().getDeclaredMethods()));
        for (Method _method : _listOfDeclaredMethods) {
            _method.setAccessible(true);
            if (_method.isAnnotationPresent(NService.class)) {
                Annotation _annotation = _method.getAnnotation(NService.class);
                if (_annotation != null) {
                    String[] _apply = ((NService) _annotation).apply();
                    if (checkMethodApply(_apply, chanceTypeCode)) {
                        return _method;
                    }
                }
            }

        }

        /* In case of action method not found, throw a system exception */
        throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM_LOGIC,
                new IllegalArgumentException("Action method not found"));
    }

    /**
     * Mehtod apply checking.
     * 
     * @param applyForChanceTypeCode List of Chance Type Code that the method
     *                               implement action.
     * @param chanceTypeCode         Selected region
     * @return True if method is apply for Selected region, otherwise false
     */
    private boolean checkMethodApply(String[] applyForChanceTypeCode, String chanceTypeCode) {
        if (applyForChanceTypeCode != null && applyForChanceTypeCode.length > 0) {
            for (String _chanceTypeCode : applyForChanceTypeCode) {
                if (chanceTypeCode.equalsIgnoreCase(_chanceTypeCode)
                        || ChanceTypeCode.ALL.getChanceTypeCode().equals(_chanceTypeCode)) {
                    return true;
                }
            }
        }
        return (applyForChanceTypeCode != null && applyForChanceTypeCode.length == 0);
    }

    public ValueResult<?> getValueResult() {
        return valueResult;
    }

    public void setValueResult(ValueResult<?> valueResult) {
        this.valueResult = valueResult;
    }
}
