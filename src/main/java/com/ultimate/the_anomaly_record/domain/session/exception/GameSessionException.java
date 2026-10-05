package com.ultimate.the_anomaly_record.domain.session.exception;

import com.ultimate.the_anomaly_record.global.exception.BaseErrorCode;
import com.ultimate.the_anomaly_record.global.exception.GeneralException;

public class GameSessionException extends GeneralException {
    public GameSessionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
