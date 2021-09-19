package com.servletjsp.base.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.servletjsp.base.common.expceitons.Information;

public class Result implements Serializable {

    private static final long       serialVersionUID = 1L;

    private final List<Information> infos            = new ArrayList<>();
    private final List<Information> warns            = new ArrayList<>();

    public void add(Result other) {
        infos.addAll(other.getInfos());
        warns.addAll(other.getWarns());
    }

    public boolean hasInfo() {
        return !infos.isEmpty();
    }

    public boolean hasWarn() {
        return !warns.isEmpty();
    }

    public List<Information> getInfos() {
        return infos;
    }

    public List<Information> getWarns() {
        return warns;
    }

}
