# Git Flow 소개 및 사용 예시
`Git-flow`에는 5가지 종류의 branch가 존재합니다. 항상 유지되는 `main branch`들(master, develop)과 일정 기간 동안만 유지되는 `sub branch`들(feature, release, hotfix)이 있습니다.


* **master** : 제품으로 출시될 수 있는 branch
* **develop** : 다음 출시 버전을 개발하는 branch
* **feature** : 기능을 개발하는 branch
* **release** : 이번 출시 버전을 준비하는 branch
* **hotfix** : 출시 버전에서 발생한 버그를 수정하는 branch

![image](https://user-images.githubusercontent.com/49297157/116959015-4fb2dc00-acd7-11eb-92f1-2c1c7ee1111d.png)


- 처음에는 `master`와 master로부터 만들어진 `develop` branch가 존재합니다. 
- `develop branch`에는 상시로 *bug*를 수정한 commit들이 추가됩니다. 
- 새로운 기능 추가 작업이 있는 경우 `develop branch`에서 `feature branch`를 생성합니다. 
- `feature branch`는 언제나 `develop branch`로부터 시작하게 됩니다.
- 기능 추가 작업이 완료되었다면 `feature branch`는 `develop branch`로 *merge*됩니다.
- *QA*를 하기 위해 `develop branch`에서부터 `release branch`를 생성합니다.
- QA를 진행하면서 발생한 *bug*들은 `release branch`에서 수정됩니다.
- QA를 무사히 통과했다면 `release branch`를 `master`와 `develop branch`로 *merge*합니다.
- 마지막으로 출시된 `master branch`에서 *version tag*를 추가합니다.
