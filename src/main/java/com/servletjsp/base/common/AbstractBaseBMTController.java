package com.servletjsp.base.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.servletjsp.base.common.constants.SystemErrorCodeConstants;
import com.servletjsp.base.common.expceitons.AbstractApplicationRuntimeException;
import com.servletjsp.base.common.expceitons.ApplicationCheckException;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;

/**
 * AbstractBaseBMTController
 * 
 * @author Admin
 *
 */
public class AbstractBaseBMTController extends AbstractBaseBMTSessionBean {

    private ValueResult<?> valueResult;

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
                throw new ApplicationCheckException(SystemErrorCodeConstants.SYSTEM, e);
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new ApplicationCheckException(SystemErrorCodeConstants.SYSTEM, e);
        }

    }

    /**
     * Find action method
     * 
     * @param actionName     The action name
     * @param chanceTypeCode The chance type code
     * @return {@link Method}
     */
    private Method findMethod(String actionName, String chanceTypeCode) {
        /*
         * In case of system Crud type not defined, throw an system Chance Type
         * exception
         */
        if (StringUtils.isBlank(chanceTypeCode)) {
            throw new ApplicationCheckException(SystemErrorCodeConstants.SYSTEM,
                    new IllegalArgumentException("Crud type code invalid"));
        }

        List<Method> _listOfMethodApplyForRegin = findMethodApplyForChanceType(chanceTypeCode);
        for (Method _method : _listOfMethodApplyForRegin) {
            _method.setAccessible(true);
            Annotation _annotation = _method.getAnnotation(NController.class);
            if (_annotation != null) {
                String _actionName = ((NController) _annotation).action();
                if (StringUtils.isNotBlank(_actionName) && _actionName.equalsIgnoreCase(actionName))
                    ;
                return _method;
            }
        }

        /* In case of action method not found, throw an system exception */
        throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM,
                new IllegalArgumentException("Action method not found"));
    }

    /**
     * Find method apply for CHANCE type
     * @param chanceTypeCode The chance type code
     * @return List<Method>
     */
    private List<Method> findMethodApplyForChanceType(String chanceTypeCode) {
        List<Method> _listOfActionMethods = new ArrayList<Method>();
        if (StringUtils.isBlank(chanceTypeCode)) {
            return _listOfActionMethods;
        }

        List<Method> _listOfDelcaredMethods = new ArrayList<Method>(Arrays.asList(getClass().getMethods()));
        for (Method _method : _listOfDelcaredMethods) {
            if (_method.isAnnotationPresent(NController.class)) {
                Annotation _annotation = _method.getAnnotation(NController.class);
                if (_annotation != null) {
                    String _applyForCrudType = ((NController) _annotation).action();
                    if (StringUtils.isNotBlank(_applyForCrudType)
                            && _applyForCrudType.equalsIgnoreCase(chanceTypeCode)) {
                        _listOfDelcaredMethods.add(_method);
                    }
                }
            }
        }
        return _listOfActionMethods;
    }

    public ValueResult<?> getValueResult() {
        return valueResult;
    }

    public void setValueResult(ValueResult<?> valueResult) {
        this.valueResult = valueResult;
    }

}
