# S3 업로드 방식 성능 테스트

이 프로젝트는 Amazon S3에 파일을 업로드할 때,  
**Multipart 방식과 Stream 방식의 성능을 비교 테스트**하기 위한 스크립트를 포함하고 있습니다.

## 📁 폴더 구조

- `test-files/` : 업로드 테스트용 샘플 파일 (예: 1MB 파일)
- `multipart_upload.js` : `multipart/form-data` 방식 업로드 테스트 스크립트
- `stream_upload.js` : 스트림 기반 업로드 테스트 스크립트
- `upload-1mb-100.js` : 1MB 파일 100개를 동시에 업로드하는 테스트 스크립트
- `README.md` : 이 문서

## 🚀 실행 방법

[k6](https://k6.io/) 를 사용해 부하 테스트를 실행할 수 있습니다.

### 예시

```bash
k6 run upload-1mb-100.js
```

### 자세한 테스트 배경과 성능 비교 결과는 아래 블로그를 참고하세요:
👉 [체험일지 이미지 등록 성능 개선 – Presigned URL (1)](https://velog.io/@joonchoi/%EC%B2%B4%ED%97%98-%EC%9D%BC%EC%A7%80-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EB%93%B1%EB%A1%9D-%EC%84%B1%EB%8A%A5-%EA%B0%9C%EC%84%A0)
