package com.ultimate.the_anomaly_record.domain.session.service;

import com.ultimate.the_anomaly_record.domain.session.dto.GameSessionRequest;
import com.ultimate.the_anomaly_record.domain.session.dto.GameSessionResponse;
import com.ultimate.the_anomaly_record.domain.session.exception.GameSessionErrorCode;
import com.ultimate.the_anomaly_record.domain.session.exception.GameSessionException;
import com.ultimate.the_anomaly_record.domain.session.model.GameSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameSessionService {

    private static final int DEFAULT_MAX_PLAYERS = 4;

    // 세션은 매치 하나 생명주기만큼만 의미 있는 휘발성 데이터라 DB 없이 메모리에 보관
    private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();

    public GameSessionResponse.SessionInfo registerSession(GameSessionRequest.RegisterRequest request) {
        GameSession session = GameSession.builder()
                .sessionId(UUID.randomUUID().toString())
                .hostName(request.getHostName())
                .ip(request.getIp())
                .port(request.getPort())
                .mapName(request.getMapName())
                .maxPlayers(request.getMaxPlayers() != null ? request.getMaxPlayers() : DEFAULT_MAX_PLAYERS)
                .createdAt(LocalDateTime.now())
                .build();

        sessions.put(session.getSessionId(), session);
        log.info("[Session] 등록: {} ({}:{})", session.getHostName(), session.getIp(), session.getPort());

        return toResponse(session);
    }

    public List<GameSessionResponse.SessionInfo> getOpenSessions() {
        return sessions.values().stream()
                .sorted(Comparator.comparing(GameSession::getCreatedAt).reversed())
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void closeSession(String sessionId) {
        GameSession removed = sessions.remove(sessionId);
        if (removed == null) {
            throw new GameSessionException(GameSessionErrorCode.SESSION_NOT_FOUND);
        }
        log.info("[Session] 종료: {}", sessionId);
    }

    private GameSessionResponse.SessionInfo toResponse(GameSession session) {
        return GameSessionResponse.SessionInfo.builder()
                .sessionId(session.getSessionId())
                .hostName(session.getHostName())
                .ip(session.getIp())
                .port(session.getPort())
                .mapName(session.getMapName())
                .maxPlayers(session.getMaxPlayers())
                .createdAt(session.getCreatedAt())
                .build();
    }
}
