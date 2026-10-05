package com.ultimate.the_anomaly_record.domain.session.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GameSessionRequest {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegisterRequest {

        @NotBlank
        private String hostName;

        @NotBlank
        private String ip;

        @Min(1)
        @Max(65535)
        private int port;

        private String mapName;

        private Integer maxPlayers;
    }
}
