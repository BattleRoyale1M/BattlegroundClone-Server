## 🛠 Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)


## ⚙️ WorkFlow

```mermaid
sequenceDiagram
    participant H as 호스트 (UE5)
    participant S as Spring Boot 서버
    participant C as 클라이언트 (UE5)

    H->>S: 세션 등록 (포트, 맵, 최대 인원)
    C->>S: 세션 목록 조회
    S-->>C: 방 목록 (호스트 이름, 현재 인원)
    C->>S: 세션 참가
    S-->>C: 접속 정보
    C->>H: 호스트에 접속
    C->>S: 퇴장 (인원수 감소)
    H->>S: 세션 삭제
```


## 🔌 API

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/sessions` | 세션 등록 |
| GET | `/api/sessions` | 세션 목록 조회 |
| POST | `/api/sessions/{id}/join` | 세션 참가 |
| POST | `/api/sessions/{id}/leave` | 세션 퇴장 |
| DELETE | `/api/sessions/{id}` | 세션 삭제 |


## Plan

- [ ] 호스트 비정상 종료 시 남는 방 정리 (하트비트 / 만료 처리)
- [ ] 조건 기반 자동 매칭 (게임모드, 지역, MMR 등)
- [ ] 매칭 대기열을 Redis로 관리해 빠른 조회·삭제
- [ ] 매칭 성립 알림을 WebSocket 푸시로 전달
- [ ] Dedicated Server 할당 방식 결정 (Agones vs 직접 관리)
