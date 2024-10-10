
# API 

## 서버 상태 
### GET /user/heahth_check


## ▶로그인
### GET /user/login
```
header : {  
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
### GET /user/refresh
```
header :{
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
### GET /user/logout
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
