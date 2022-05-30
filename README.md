# Standard Spring boot Framework (ver 0.1)

## ● setup
1. GIT Clone
2. File Name Replace "easyoops" > "{project name}"
3. com/{project}/{project}Application 실행
4. DB(H2) 자동 구성 및 스키마 자동 세팅.

## ● structure
![image](https://user-images.githubusercontent.com/13517312/170916737-aa3627dc-cc0b-4626-988a-b397589b7824.png)

- biz : 업무 개발
- common : 공통 (framework, util)
- ext : 외부 연동 개발 (provider)

## ● swagger UI
1. 설정 : SwaggerConfig.java
2. URL : http://localhost:8080/swagger-ui/

## ● H2 Database
1. mock memory databace 설정이 되어 있으며, 사용 DB 전환시 아래 편집.
   - application.properties
   - DataSourceConfig.java

2. URL : http://localhost:8080/h2/

## ● application architecture
1. 구조 정도만 잡아 두었으며, 업무 상황에 맞게 맞춤형 구성 필요
   - aspect
   - security
   - filter
   - interceptor
   - exception
   - provider

# ※ ver 0.1 로컬 환경 구성 기준
