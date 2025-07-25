## 📎Category
JPA 무한 Depth를 가지는 카테고리 구조 생성

##  📌요구사항
1. 카테고리는 자기참조 형태로 설계되어 있습니다.
2. 카테고리 객체는 미노출과 노출 정보를 가집니다.

## 💡사용한 기술
백엔드는 Springboot,JPA로 개발하였으며 프론트는 최소한의 노출만 표시하도록 Thymeleaf를 사용하여 SSR방식으로 제작하였습니다.
<br>
## 💡카테고리 DB 구조
![image](https://github.com/HunDeveloper16/category/assets/56526225/0357649d-eff7-4709-9fc2-18f437aa9059)
<br>
id와 depth, upCategory는 모두 Bigint type을 가지며 나머지는 Varchar(255) type을 가집니다.

## 결과 화면
![image](https://github.com/HunDeveloper16/category/assets/56526225/47d88697-94b8-4a61-a809-81ba333603ac)


## 🔧목록에 관한 처리
<h2>1. 노출</h2>
    <p>
        1-1. 최상위 카테고리 객체가 만약 미노출이라면 아래에 있는 객체들은 동일하게 미노출이기 때문에, DB에서 조회시 부모가 Null인 값을 가져옵니다.
    </p>
    <p>
        1-2. 화면에서 fragment 조각으로 나누어 재귀 호출합니다.
    </p>


<h2>2. 미노출</h2>
    <p>
        2-1. 상위 카테고리는 노출인데 하위 카테고리는 미노출일 수 있으므로, 먼저 미노출인 데이터를 모두 가져옵니다.
    </p>
    <p>
        2-2. 미노출인 부모는 미노출인 하위 객체를 가지고 있기 때문에 나중에 화면에서 재귀 호출하여 목록을 띄우게 되면 동일한 데이터가 노출됩니다.
    </p>
    <p>
        2-3. 따라서 DepthFirstSearch 원리가 적용된 재귀 함수로 자식까지 모두 탐색하여 중복된 데이터를 추출합니다.
    </p>
    <p>
        2-4. 중복된 데이터를 제외한 새로운 목록 객체를 화면으로 전달합니다.
    </p>
    <p>
        2-5. 화면에서 fragment 조각으로 나누어 재귀 호출합니다.
    </p>
