# 서버 시작하기

```bash
$ git clone https://github.com/sangminlee98/sample-server.git
```
```bash
$ cd sample-server
```
```bash
$ docker-compose up -d
```
# API 명세
![스크린샷 2023-06-01 오후 10 12 50](https://github.com/sangminlee98/sample-server/assets/83197138/82f306a0-4c56-4d9b-a89d-c67a86ec352d)

## 1. getTodos
  ### Request

  - URL: `api/todos`
  - Method: `GET`

  ### Response

  ```json
  {
    "todos": [
      {
        "id": 1,
        "title": "할일 1",
        "done": false
      },
      {
        "id": 2,
        "title": "할일 2",
        "done": true
      },
      {
        "id": 3,
        "title": "할일 3",
        "done": false
      },
    ]
  }
  ```
  
## 2. createTodo 
  
  ### Request

  - URL: `api/todos`
  - Method: `POST`
  - Body:
      - `title: string`

  ### Response

  ```json
  {
    "id": 1,
    "title": "할일 1",
    "done": false
  }
  ```
  
## 3. updateTodo

  ### Request

  - URL: `api/todos/:id`
  - Method: `PUT`

  ### Response

  ```json
  {
    "id": 1,
    "title": "할일 1",
    "done": false
  }
  ```
  
## 4. deleteTodo

  ### Request

  - URL: `api/todos/:id`
  - Method: `DELETE`

  ### Response

  ```json
  없음
  ```
