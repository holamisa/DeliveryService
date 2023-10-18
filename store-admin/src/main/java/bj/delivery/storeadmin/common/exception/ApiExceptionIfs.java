package bj.delivery.storeadmin.common.exception;

import bj.delivery.storeadmin.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
