package com.servletjsp.base.web.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.servletjsp.base.ejb.constants.ApplicationParameterConstants;

/**
 * 
 * @author Admin
 *
 */
public class AbstractWebBaseBackingBean implements Serializable {

    private static final long   serialVersionUID = 1L;

    private static final String BEAN_SUFFIX      = "Bean";

    private static final String VIEW_SOURCE      = "view/";
    private static final String VIEW_EXTENSION   = ".view.json";

    private boolean             isInit           = true;

    /** Screen Id. */
    protected String            screenId;

    private ResourceBundle      viewProperties;
    private JSONObject          view;

    protected WebProperty       webProperty;;

    public AbstractWebBaseBackingBean() {
        String _classNameUpper = this.getClass().getSimpleName().toUpperCase();
        screenId = _classNameUpper.substring(0, _classNameUpper.length() - BEAN_SUFFIX.length());

        if (viewProperties == null) {
            viewProperties = ResourceBundle.getBundle(ApplicationParameterConstants.BUNDLE_NAME_VIEW);
        }
    }

    public void init() {
        if (isInit) {
            initView();
            initHelper();
            initValidator();
            reset();
            isInit = false;
        }
    }

    /**
     * Init reset
     */
    private void reset() {
        // For overriding.

    }

    /**
     * Init validator
     */
    public void initValidator() {
        // For overriding.

    }

    /**
     * Init helper
     */
    public void initHelper() {
        // For overriding.

    }

    /**
     * Init view metadata.
     */
    private void initView() {
        File _file = null;
        FileReader _reader = null;
        try {
            ClassLoader _classLoader = this.getClass().getClassLoader();
            try {
                _file = new File(_classLoader.getResource(VIEW_SOURCE + screenId + VIEW_EXTENSION).getFile());
            } catch (NullPointerException e) {
                // nop
            }

            if (_file != null) {
                _reader = new FileReader(_file);
                view = (JSONObject) new JSONParser().parse(_reader);
            }
        } catch (IOException | ParseException e) {
            // nop
        } finally {
            if (_reader != null) {
                try {
                    _reader.close();
                } catch (IOException e) {
                    // nop
                }
            }
        }
    }

    /**
     * Bean Validation<br>
     * For Overriding if need.
     * 
     * @param locale           Locale
     * @param actionMethodName String
     * @return True mean valid, otherwise false.
     */
    public boolean validate(Locale locale, String actionMethodName) {
        return true;
    }
}
