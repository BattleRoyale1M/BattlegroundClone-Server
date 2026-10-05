package com.ultimate.the_anomaly_record.domain.session.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GameSession {
    private String sessionId;
    private String hostName;
    private String ip;
    private int port;
    private String mapName;
    private int maxPlayers;
    private int currentPlayers;
    private LocalDateTime createdAt;
}
