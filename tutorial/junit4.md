# 유닛 테스트 작성 방법
IntelliJ에서 JUnit4를 설정하는 방법과 테스트 코드를 작성하는 방법에 대해 알아보겠습니다.

### 1. 테스트 패키지 설정 방법

* 최상위 폴더에서 tests 라는 이름의 패키지를 생성합니다. (어떤 이름도 괜찮습니다만 일반적으로 tests라는 이름을 사용합니다.)
* src폴더 우클릭 후 Open Module Settings를 클릭합니다.
* 새로 생성한 tests 패키지를 클릭하고 Mark as에서 초록색 폴더인 Tests를 클릭합니다.
* 아래 사진과 같이 tests 패키지가 초록색으로 변한 것을 확인합니다.

![image](https://user-images.githubusercontent.com/49297157/117138822-80ce0200-ade6-11eb-98cb-b8d585e5304b.png)

### 2. 테스트 파일 설정 방법
테스트 코드를 작성하기 위해 간단한 JUnitTest라는 클래스를 생성하였습니다.
* Class 이름에 마우스 커서를 올리면 나타나는 노란색 전구를 클릭합니다.
* Create Test를 클릭합니다.
* 아래와 같은 창에서 JUnit4를 클릭하고 Fix 버튼을 눌러 설치를 완료합니다.
* 테스트 코드를 작성할 함수를 선택하고 OK를 클릭합니다.

![image](https://user-images.githubusercontent.com/49297157/117138953-a4914800-ade6-11eb-890e-20e4fcf46271.png)

아래와 같이 초록색 패키지인 tests 안에 [ClassName+Test]라는 클래스가 생성된 것을 확인합니다.

![image](https://user-images.githubusercontent.com/49297157/117139032-b83cae80-ade6-11eb-89a6-5a471a8cb078.png)

### 테스트 코드 작성 방법

#### Annotation
* @BeforeClass : 테스트 수행을 시작할 때 1번만 실행됩니다.
* @Before : 각 유닛 테스트 메소드가 실행되기 전에 무조건 실행됩니다.
* @Test : 유닛 테스트를 진행할 메소드를 나타냅니다.
* @Ignore : 유닛 테스트를 무시합니다.
* @After : 각 유닛 테스트 메소드가 실행된 후 무조건 실행됩니다.
* @AfterClass : 테스트 수행이 완료될 때 1번만 실행됩니다.

#### Function 
첫 번째 인자는 개발자가 예상한 값(Expected Output), 두 번째 인자는 실제 테스트를 통해 객체가 반환해준 값(Actual Result)이 들어갑니다. 두 값을 비교하여 테스트의 성공 여부를 결정합니다.
인자값이 하나인 경우에는 실제 테스트를 통해 객체가 반환해주는 값을 넣습니다.
* assertEquals(Type, Type) : 객체 a와b의 값이 같은지 확인
* assertArrayEquals(a,b) : 배열 a와b가 일치함을 확인 
* assertSame(a,b) : 객체 a와b가 같은 객체임을 확인 
* assertTrue(a) : a가 참인지 확인 
* assertNotNull(a) : a객체가 null이 아님을 확인

이외에도 다양한 함수가 존재하므로 아래 링크에서 더 자세한 내용을 볼 수 있습니다.

http://junit.sourceforge.net/javadoc/org/junit/Assert.html


```
import org.junit.*;

import static org.junit.Assert.*;

public class JUnitTestTest {
public static JUnitTest jUnitTest;

    @BeforeClass
    public static void makeInstance() throws Exception {
        System.out.println("BeforeClass : Cola(20)");
        jUnitTest = new JUnitTest("Cola", 20);
    }

    @Before
    public void beforeTest() throws Exception {
        System.out.println("Before");
    }

    @Test
    public void getTitle() throws Exception{
        assertEquals("Cola", jUnitTest.getTitle());
    }

    @Test
    public void setTitle() throws Exception{
        jUnitTest.setTitle("Soda");
    }

    @Test
    public void getCount() throws Exception{
        assertEquals(10, jUnitTest.getCount());
    }

    @Test
    public void setCount() throws Exception{
        jUnitTest.setCount(50);
    }

    @After
    public void printAfter() throws Exception{
        System.out.println("After");
    }

    @AfterClass
    public static void printAfterClass() throws Exception{
        System.out.println("AfterClass");
    }
}
```

위의 테스트 코드를 실행하면 아래와 같은 결과를 얻을 수 있습니다.

![image](https://user-images.githubusercontent.com/49297157/117147202-fb4f4f80-adef-11eb-9894-07afb5e15c70.png)

[Click to see difference] 를 클릭하면 Expected Output과 Actual Output을 보다 쉽게 비교할 수 있습니다.

![image](https://user-images.githubusercontent.com/49297157/117146507-3b620280-adef-11eb-84d9-35f4b541f829.png)

코드에서 setCount(50) 함수를 실행하였으므로, 현재 count의 값은 50으로 설정되어 있습니다. 하지만 getCount()안에 있는 
assertEquals 함수에서 Expected Output을 10이라고 설정하였기 때문에 테스트에 실패한 것을 볼 수 있습니다.
