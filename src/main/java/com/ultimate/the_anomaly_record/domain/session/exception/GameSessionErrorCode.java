package com.ultimate.the_anomaly_record.domain.session.exception;

import com.ultimate.the_anomaly_record.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameSessionErrorCode implements BaseErrorCode {
    SESSION_NOT_FOUND("GS001", "해당 세션을 찾을 수 없습니다.");

    private final String code;
    private final String message;
}
