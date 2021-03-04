
 aws codepipeline 및 jenkins를 사용하여 spring 프로젝트 자동 빌드 후 배포 실행
 
    실행 결과: 배포 완료 후 바로 애플리케이션 톰캣 자동 작동하여 서비스 시작
    
        1. CodePipeline (Codecommit-CodeBuild-CodeDeploy) 사용
        
        2. Jenkins (Codecommit-Jenkins maven 빌드 -CodeDeploy) 사용
        
        3. Jenkins (Codecommit-CodeBuild -CodeDeploy) 사용

CodeSeries 실행시 slack 알림 설정

       1. AWS Lamda에서 CloudWatch Event를 트리거하여 slack으로 보내주는 방식(codepipeline 트리거)
       
        - lamda 함수 생성시 node.js를 사용하였음.
        
        - 참고 코드 index.js   
        

       2. AWS Lamda에서 AWS SNS를 연동하여 slack으로 보내주는 방식(codedeploy 트리거)

        - lambda 함수 생성시 python 사용하였음.

        - 참고코드 lambda_function.py

        - 스크립트 테스트 lambda_function_testevent.json