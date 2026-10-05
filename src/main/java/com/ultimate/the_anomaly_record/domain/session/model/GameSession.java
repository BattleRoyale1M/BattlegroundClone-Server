package com.ultimate.the_anomaly_record.domain.session.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class
GameSession {
    private String sessionId;
    private String hostName;
    private String ip;
    private int port;
    private String mapName;
    private int maxPlayers;
    private LocalDateTime createdAt;
}
