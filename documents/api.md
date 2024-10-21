# API 

 
| API | 설명 | DB |  
|-----|------|----|
|로그인|서비스 로그인 or 회원가입 진행|User & refreshToken 생성|      
|access token 재발급|로그인 연장 목적 토큰 재발급|refreshToken 삭제 & 생성|
|로그아웃|서비스 로그아웃|refreshToken 삭제|
|내 프로필 조회|로그인 User 정보 반환||
|프로필 조회|대상 User 정보반환||
|프로필 변경|로그인 유저 프로필 정보 변경|User 변경|


## 서버 상태 
### GET /user/heahth_check


## ▶로그인
### POST /user/login
```
header: {  
  token: Bearer ${Oauth accessToken value},
  provider: ${oauth provider} (=kakao)     
}      
```

### 응답
```
body: {  
  accessToken: ${accessToken value}
  refreshToken: ${refreshToken value}
}
```


## ▶accessToken 재발급 
### POST /user/refresh
```
header: {
  token: ${refreshToken value}
}
```

### 응답
```
body: {  
  accessToken: ${accessToken value}
  refreshToken: ${refreshToken value}
}
```


## ▶로그아웃
### DELETE /user/logout
```
header: {
  accessToken: ${accessToken value}
}
```


## ▶로그인 테스트 
### GET /user/authorize_test
```
header: {
  accessToken: ${accessToken value}
}
```

### 응답
```
body: {  
  username: ${username}
}
```


## ▶내 프로필 조회
### GET /user/profile/me
```
header: {
  accessToken: ${accessToken value}
}
```

## 응답
```
body: {
  userId: 사용자 id (Long)
  username: 유저 네임, (String)
  name: 사용자 이름, (String)
  age: 사용자 나이, (Integer)
  gender: 사용자 성별, (String)
  phNum: 사용자 번호, (String)
  email: 사용자 email, (String)
  description: 사용자 소개, (String)
  image: 사용자 이미지 url (String)
}
```


## ▶프로필 조회
### GET /user/profile/{대상 username}
```
header: {
  accessToken: ${accessToken value}
}
```

### 응답
```
  userId: 사용자 id (Long)
  username: 유저 네임, (String)
  name: 사용자 이름, (String)
  age: 사용자 나이, (Integer)
  gender: 사용자 성별, (String)
  phNum: 사용자 번호, (String)
  email: 사용자 email, (String)
  description: 사용자 소개, (String)
  image: 사용자 이미지 url (String)
}
```


## ▶프로필 변경
### PATCH /user/profile
```
header: {
  accessToken: ${accessToken value}
}

form-data: {
  email: 변경 email, (String)
  emailChanged: email 변경 여부, (Boolean, true or false) 
  phNum: 변경 번호, (String)
  phNumChanged: 번호 변경 여부, (Boolean, true or false) 
  description: 변경 설명, (String)
  descriptionChanged: 설명 변경 여부, (Boolean, true or false) 
  image: 변경 이미지, (이미지 파일)
  imageChanged: 이미지 변경 여부 (Boolean, true or false) 
}
```

### 응답
