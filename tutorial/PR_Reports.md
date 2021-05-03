# Pull Request 및 GitHub Pages 리포트 확인
예를 들어 `feat/add-some-feature`라는 브랜치에서 새로운 기능을 만들어 `dev` 브랜치로 코드를 합치는 과정입니다.

```shell
$ git add .
$ git commit -m "Feat: Add some features"
$ git push -u
// 업스트림에 처음하는 푸시일 경우 아래 명령어
$ git push --set-upstream origin feat/add-some-feature
```

작업한 브랜치의 소스코드를 푸시한 후 Pull Request를 만듭니다.

![image](https://user-images.githubusercontent.com/50787869/116845897-1ff5cc80-ac22-11eb-8f06-b21a0e25c39c.png)

Pull Request가 생성되면 CTIP 환경에서 빌드, 유닛 테스트 및 정적 분석 등을 수행하게 되며 푸시한 코드에 대한 결과를 확인할 수 있습니다. 
현재 이 과정에서 2분 정도의 시간이 소요되고 있는데 프로젝트의 규모가 커질수록 조금 더 걸릴 수 있습니다.

![image](https://user-images.githubusercontent.com/50787869/116846049-72cf8400-ac22-11eb-9f67-2189ee2d39c2.png)

CTIP 환경에서 검사가 끝나게 되면 다음 화면처럼 **All checks have passed**라고 표시되면 소스 코드가 테스트를 통과한 것입니다. 
현재 `main`, `dev` 브랜치에는 최소 한명 이상이 리뷰를 해줘야 병합을 할 수 있게 설정되어 있습니다. 
리뷰는 저희(SQA)가 처리를 하며 리뷰에서 문제가 없을 시 Pull Request를 올린 사람이 직접 병합을 하면 됩니다.

CTIP 환경에서 테스트한 결과는 [리포트 페이지](https://sys09270883.github.io/ctip/)에서 확인할 수 있습니다. 
리포트 페이지의 링크는 리포지토리 README.md에도 있습니다.

![image](https://user-images.githubusercontent.com/50787869/116845837-eb821080-ac21-11eb-91d7-7faecb981a18.png)
