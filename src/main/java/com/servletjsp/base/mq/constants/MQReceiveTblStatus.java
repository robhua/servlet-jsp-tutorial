package com.servletjsp.base.mq.constants;

public enum MQReceiveTblStatus {
    SUCCESS("1"), ERROR("E"), NO_TARGET("2");

    final String status;

    private MQReceiveTblStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
