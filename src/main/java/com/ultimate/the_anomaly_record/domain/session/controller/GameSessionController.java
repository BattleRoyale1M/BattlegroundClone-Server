package com.ultimate.the_anomaly_record.domain.session.controller;

import com.ultimate.the_anomaly_record.domain.session.dto.GameSessionRequest;
import com.ultimate.the_anomaly_record.domain.session.dto.GameSessionResponse;
import com.ultimate.the_anomaly_record.domain.session.service.GameSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/sessions")
@RequiredArgsConstructor
@Slf4j
public class GameSessionController {

    private final GameSessionService gameSessionService;

    /**
     * 세션(방) 등록 - 호스트가 "호스트하기" 클릭 시 호출
     */
    @Operation(
            summary = "세션 등록 API",
            description = "호스트의 IP/Port를 등록해 방목록에 노출합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping
    public ResponseEntity<GameSessionResponse.SessionInfo> registerSession(
            @RequestBody @Valid GameSessionRequest.RegisterRequest request) {
        return ResponseEntity.ok(gameSessionService.registerSession(request));
    }

    /**
     * 세션(방) 목록 조회 - 참가자가 접속할 방을 고를 때 호출
     */
    @Operation(
            summary = "세션 목록 조회 API",
            description = "현재 참가 가능한 세션(방) 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping
    public ResponseEntity<List<GameSessionResponse.SessionInfo>> getOpenSessions() {
        return ResponseEntity.ok(gameSessionService.getOpenSessions());
    }

    /**
     * 세션 참가 - 참가자가 방목록에서 선택해 접속을 시도할 때 호출 (인원수 추적)
     */
    @Operation(
            summary = "세션 참가 API",
            description = "참가자가 세션에 들어갈 때 인원수를 1 증가시킵니다. 정원이 가득 찼으면 실패합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "참가 성공"),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 세션 또는 정원 초과")
    })
    @PostMapping("/{sessionId}/join")
    public ResponseEntity<GameSessionResponse.SessionInfo> joinSession(@PathVariable String sessionId) {
        return ResponseEntity.ok(gameSessionService.joinSession(sessionId));
    }

    /**
     * 세션 퇴장 - 참가자가 접속을 포기/종료했을 때 호출 (인원수 추적)
     */
    @Operation(
            summary = "세션 퇴장 API",
            description = "참가자가 세션에서 나갈 때 인원수를 1 감소시킵니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "퇴장 성공"),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 세션")
    })
    @PostMapping("/{sessionId}/leave")
    public ResponseEntity<GameSessionResponse.SessionInfo> leaveSession(@PathVariable String sessionId) {
        return ResponseEntity.ok(gameSessionService.leaveSession(sessionId));
    }

    /**
     * 세션 종료 - 매치 시작/호스트 종료 시 목록에서 제거
     */
    @Operation(
            summary = "세션 종료 API",
            description = "매치 시작 또는 호스트 종료 시 세션을 목록에서 제거합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "종료 성공"),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 세션")
    })
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Map<String, String>> closeSession(@PathVariable String sessionId) {
        gameSessionService.closeSession(sessionId);
        return ResponseEntity.ok(Map.of("message", "세션 종료 완료"));
    }
}
