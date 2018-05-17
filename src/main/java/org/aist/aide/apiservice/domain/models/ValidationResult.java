package org.aist.aide.apiservice.domain.models;

public interface ValidationResult {
    String getError();
    boolean isValid();
}
