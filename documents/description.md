# 1. 패키지 
```
band_user
    ├─external
    │  ├─oauth
    │  ├─redis
    │  └─s3
    ├─jwt
    └─user
        └─form
```

# 2. 도메인

| 도메인 | 속성 | 타입 | 설명 | 비고 |  
|--------|------|------|------|------|
|User    |      |      |      |      |
|        |id|Long|User Id||
|        |username|String|username|User 추적키|
|        |email|String|이메일||
|        |phNum|String|전화 번호||
|        |image|String|이미지 리소스 url||
|        |name|String|이름||
|        |gender|String|성별|'male' or 'female'|
|        |birthYear|Integer|출생년도||
|        |decription|Stirng|회원 설명||
|        |createdAt|Instant|회원 생성일||


| 도메인 | 속성 | 타입 | 설명 | 비고 |  
|--------|------|------|------|------|
|refreshToken|      |      |      |      |
|            |id|Long|refreshToken Id||
|            |username|String|username||
|            |token|String|token 밸류|JWT|



# 3. 주요 컴포넌트

| 컴포넌트 | 설명 | 비고 |  
|----------|------|------|
|OauthAuthenticator|Oauth 인증 목적 utils||
|UserInfo|Oauth 유저 정보 저장||
|RedisSercie|Redis 접근 목적 utils||
|S3Service|S3 접근 목적 utils||
|JwtUtils|JWT 생성, 인증 목적 utils||
|UserController|User 관련 엔드포인트||
|UserService|User 관련 비즈니스 로직 수행||
|UserRepository|User 관련 DB 접근||



# 4. ERD
![band_user](https://github.com/user-attachments/assets/efd8e7b8-8485-4a1a-aafa-79e7f7f81318)
