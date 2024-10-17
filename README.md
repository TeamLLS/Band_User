
# API 

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
  username: 유저 네임 , (String)
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
  username: 유저 네임 , (String)
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
