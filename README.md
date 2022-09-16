# 원티드 프리온보딩 Android 코스 사전과제
# News 앱 만들기
- dev 브랜치에 기능 개발 완료
# Feature 🌟
### 1. News 목록
<img height="400" src="https://user-images.githubusercontent.com/31344894/190093320-f1f8a2dd-5b8e-4741-9ee7-a19f19e20d4f.png"> <img height="400" src="https://user-images.githubusercontent.com/31344894/190093840-1a365b44-4cad-4100-95ee-8d8328aa5086.png"> <img height="400" src="https://user-images.githubusercontent.com/31344894/190093959-bc0adab2-1c52-4535-97d3-3027546bd6f0.png">
- RecyclerView를 사용한 뉴스 리스트 출력 
- 기사 클릭 시, 상세 기사 화면으로 이동
- Room DB를 사용한 기사 저장 기능 

### 2. News 카테고리 
<img height="400" src="https://user-images.githubusercontent.com/31344894/190094670-8dc2f26e-a560-4237-a98a-c9aaad3a09a4.png"> <img height="400" src="https://user-images.githubusercontent.com/31344894/190094784-43669208-08b2-4d6b-9ca4-f149cacc7f0d.png">
- 카테고리 버튼 클릭 시, 카테고리별 기사 리스트를 출력하는 화면으로 이동
- 기사 클릭 시, 상세 기사 화면으로 이동 (News 목록과 동일)

### 3. Saved 목록
<img height="400" src="https://user-images.githubusercontent.com/31344894/190095517-a3f2b731-f63e-4f4f-a0e0-4bf7b07393fb.png">

- 저장한 기사 목록 출력


# Tech Stack 🛠️ 
### Language
- Kotlin
### Architecture
- MVVM (DataBinding, ViewBinding)
### DataBase
- RoomDB
### DI
- Hilt
### Image Library
- Glide
### Network
- Retrofit2
- Coroutine

# Architecture 💡
### adapter
- RecyclerView의 Adapter, ViewHolder 구성 
### data
- Entity, Dao, Repository, DataSource 구성
- Repository는 LocalDataSource, RemoteDataSource의 진입점 
- DataSource: Network, Database 접근 함수 분리
### di
- DatabaseModule: Room Database와 Dao를 제공 
- NetworkModule: Retrofit과 Okhttp 제공 
### model
- Article, News Data class 
### network
- News api 인터페이스 정의 
### ui
- Activity, Fragment 구성
- MainActivity는 Bottom Navigation을 관리하며, 네비게이션은 CategoryFragment, SavedFragment, TopNewsFragment를 열 수 있음
- ArticleDetailActivity는 뉴스 상세 화면을 담당
- CategoryListActivity는 특정 카테고리 별 뉴스 리스트 화면 담당 
### util
- VerticalItemDecorator: RecyclerView item의 수직 여백 설정을 위한 클래스 
- NetworkResult: Network 응답 handling을 위한 추상 클래스 
- BaseDiffUtil: RecyclerView 성능 향상을 위한 Utility 클래스 (기존의 리스트와 비교해 업데이트가 필요한 item만 필터링)
### viewmodel
- Network, Database 접근 기능을 UI 코드와 분리하기 위함 



