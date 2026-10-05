package com.ultimate.the_anomaly_record.domain.session.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class GameSessionResponse {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SessionInfo {
        private String sessionId;
        private String hostName;
        private String ip;
        private int port;
        private String mapName;
        private int maxPlayers;
        private int currentPlayers;
        private LocalDateTime createdAt;
    }
}
