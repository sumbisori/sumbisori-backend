import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 4,          // 동시에 4명의 사용자
    iterations: 4,   // 각 사용자당 1회 실행
};

// 실제 API 호스트 주소로 교체하세요
const HOST = 'https://<your-api-host>/api';

// 필요 시 실제 액세스 토큰이 포함된 쿠키로 교체하세요
const COOKIE = 'ACCESS=<your-jwt-access-token>;';

const files = [
    { name: '1gb_1.bin', data: open('./test-files/1gb_1.bin', 'b') },
    { name: '1gb_2.bin', data: open('./test-files/1gb_2.bin', 'b') },
    { name: '1gb_3.bin', data: open('./test-files/1gb_3.bin', 'b') },
    { name: '1gb_4.bin', data: open('./test-files/1gb_4.bin', 'b') },
];

export default function () {
    const idx = __VU - 1; // VU 1 => index 0
    const file = files[idx];

    const formData = {
        file: http.file(file.data, file.name),
    };

    const res = http.post(`${HOST}/api/files/multipart-file`, formData, {
        // headers: {
        //     'Cookie': COOKIE,
        // },
        timeout: '15m',
    });

    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    console.log(`VU ${__VU} 업로드 완료. 응답 코드: ${res.status}`);
    sleep(1);
}
