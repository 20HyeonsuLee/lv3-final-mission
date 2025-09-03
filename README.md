# 아키텍처/운영 요약
1.	DB와 WAS 서버 분리
- DB 서버와 WAS 서버를 별도의 인스턴스로 분리하여 운영
    - 특정 서버 장애 발생 시 전파되지 않도록 설계
    - 예: WAS 서버가 다운되더라도 DB 서버는 정상 동작하여 서비스 전체 장애를 방지
2.	HTTPS 적용
- SSL 인증서를 적용하여 모든 통신을 HTTPS로 암호화
    - 데이터 전송 시 기밀성·무결성 보장
3.	CI/CD 파이프라인 최적화
- AWS CodePipeline + CodeBuild 기반으로 빌드/배포 구성
- GitHub Actions를 사용할 경우, 우테코 환경 특성상 Self-Hosted Runner 가 필요 → 빌드 전용 EC2 인스턴스 운영 필수
- CodePipeline/CodeBuild를 이용함으로써 별도의 빌드용 EC2가 불필요 → 비용 절감 효과
  📉 예시 추정 절감 효과:
    - 빌드 전용 EC2 (예: t4g.small, vCPU 2, RAM 2GB): 약 20~25 USD/월 (약 27,000 ~ 33,000원/월)
    - CodeBuild 사용 시 실행 시간만 과금 → 빌드 빈도가 많지 않은 경우 훨씬 저렴
    - 따라서 한 달에 약 3만 원 전후 절감 가능
