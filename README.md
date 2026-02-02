### API 명세서

Server_Url = localhost:8080

| 기능       | Method | URL | 설명|
|----------|--------|--------------------|--------------|
| 일정 등록    | POST   | /api/scheduler| 일정을 등록합니다. |
| 일정 조회    | GET    | /api/scheduler/{id} | 하나의 일정을 조회합니다. |
| 일정 전체 조회 | GET    | /api/scheduler | 모든 일정리스트를 조회합니다. |
| 일정 수정    | PATCH    | /api/scheduler/{id} | 특정 일정의 정보를 수정합니다. |
| 일정 삭제    | DELETE | /api/scheduler/{id} | 특정 일정을 삭제합니다. |


----------------------------------------------------------------------------------------------------------------------------------------------------------------

## 1. 일정 등록
```
curl -X POST http://{Server_Url}/api/scheduler \
  -H "Content-Type: application/json" \
  -d '{ 
      	"title": "제목",
        "content": "내용",
        "poster": "작성자",
        "password": "비밀번호" 
      }'
```

Request Header
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| Content-Type | String | 필수 | application/json |

Path Parameter
없음

Request Body
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| title | String | 필수 | 일정 제목 |
| content | String | 필수 | 일정 내용 |
| poster | String | 필수 | 작성자 |
| password | String | 필수 | 비밀번호 |

Response
status code 
| 코드 | 설명 |
|------|-----|
| 201 | 생성 완료 |
| 400 | 잘못된 요청 |
| 500 | 서버 오류 |

body : 성공
```
  {
    "message" : "성공적으로 생성 되었습니다." ,
    "id" : 1 
  }
```

body : 실패( 잘못된 값 입력)
```
  {
    "message" : "필수값이 누락되었습니다." 
  }
```


body : 실패( 내부 서버 에러)
```
  {
    "message" : "서버 내부 에러." 
  }
```

----------------------------------------------------------------------------------------------------------------------------------------------------------------

## 2. 일정 조회
```
curl -X GET http://{Server_Url}/api/scheduler/{id} \
  -H "Accept: application/json" \
```

Path Parameter
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| id | String | 필수 | 조회 일정 id |

Request Body
없음.

Response
status code 
| 코드 | 설명 |
|------|-----|
| 200 | 조회 완료 |
| 400 | 파라미터 형식 오류 |
| 404 | 해당 일정 없음 |
| 500 | 서버 오류 |

body : 성공
```
  {
    "message" : "성공적으로 조회 되었습니다." ,
    "data" : {
        "id" : 1,
        "title" : "일정 제목",
        "content" : "내용",
        "poster" : "작성자",
        "created_at" : "생성일자",
        "updated_at" : "수정일자"
    }
 }
```

body : 실패
```
  {
    "message" : "잘못된 id 입력" 
  }
  {
    "message": "id는 숫자여야 합니다."
  }
```


body : 실패( 내부 서버 에러)
```
  {
    "message" : "서버 내부 에러." 
  }
```

----------------------------------------------------------------------------------------------------------------------------------------------------------------

## 3. 일정 전체 조회
```
curl -X GET http://{Server_Url}/api/scheduler \
  -H "Accept: application/json" \
```

Path Parameter
없음

Request Body
없음.

Response
status code 
| 코드 | 설명 |
|------|-----|
| 200 | 조회 완료 |
| 400 | 잘못된 요청 |
| 500 | 서버 오류 |

body : 성공
```
  {
    "message" : "성공적으로 조회 되었습니다." ,
    "data" : [
      {
        "id" : 1,
        "title" : "일정 제목",
        "content" : "내용",
        "poster" : "작성자",
        "created_at" : "생성일자",
        "updated_at" : "수정일자"
      }
    ]
 }
```

body : 실패
```
  {
    "message" : "잘못된 요청 형식" 
  }
```


body : 실패( 내부 서버 에러)
```
  {
    "message" : "서버 내부 에러." 
  }
```

----------------------------------------------------------------------------------------------------------------------------------------------------------------

## 4. 일정 수정
```
curl -X PATCH http://{Server_Url}/api/scheduler/{id} \
  -H "Content-Type: application/json" \
  -H "X-Scheduler-Password: 비밀번호"
  -d '{ 
      	"title": "제목", 
        "poster": "작성자 명", 
      }'
```

Request Header
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| Content-Type | String | 필수 | application/json |
| X-Scheduler-Password | String | 필수 | 일정 비밀번호 |

Path Parameter
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| id | String | 필수 | 일정 id |

Request Body
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| title | String | poster 없을 시 필수 | 일정 제목 |
| poster | String | title 없을 시 필수 | 작성자 |

Response
status code 
| 코드 | 설명 |
|------|-----|
| 200 | 수정 완료 |
| 400 | 잘못된 요청 |
| 403 | 잘못된 비밀번호 |
| 404 | 잘못된 ID입력 |
| 500 | 서버 오류 |

body : 성공
```
  {
    "message" : "성공적으로 수정 되었습니다." ,
    "data" : {
        "id" : 1,
        "title" : "수정된 일정 제목",
        "content" : "내용",
        "poster" : "수정된 작성자",
        "created_at" : "생성일자",
        "updated_at" : "수정일자"
    }
 }
```

body : 실패 400
```
  {
    "message" : "잘못된 요청 형식" 
  }
```

body : 실패 잘못된 비밀 번호 입력 403
```
  {
    "message" : "잘못된 비밀 번호" 
  }
```
body : 실패 잘못된 id 입력 404

```
  {
    "message" : "유효하지 않은 id" 
  }
```

body : 실패( 내부 서버 에러) 500
```
  {
    "message" : "서버 내부 에러." 
  }
```

----------------------------------------------------------------------------------------------------------------------------------------------------------------

## 5. 일정 삭제
```
curl -X DELETE http://{Server_Url}/api/scheduler/{id} \
  -H "Accept: application/json" \
  -H "X-Scheduler-Password: 비밀번호"
```

Request Header
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| X-Scheduler-Password | String | 필수 | 일정 비밀번호 |

Path Parameter
| 파라미터       | 타입 | 필수 여부 | 설명 |
|----------|--------|--------------------|----|
| id | String | 필수 | 일정 id |

Response
status code 
| 코드 | 설명 |
|------|-----|
| 200 | 삭제 완료 |
| 400 | 잘못된 요청 |
| 403 | 잘못된 비밀번호 |
| 404 | 잘못된 ID입력 |
| 500 | 서버 오류 |

body : 성공
```
  {
    "message" : "성공적으로 삭제 되었습니다." ,
    "id" : 1
 }
```

body : 실패 400
```
  {
    "message" : "잘못된 요청 형식" 
  }
```

body : 실패 잘못된 비밀 번호 입력 403
```
  {
    "message" : "잘못된 비밀 번호" 
  }
```
body : 실패 잘못된 id 입력 404

```
  {
    "message" : "유효하지 않은 id" 
  }
```

body : 실패( 내부 서버 에러) 500
```
  {
    "message" : "서버 내부 에러." 
  }
```
