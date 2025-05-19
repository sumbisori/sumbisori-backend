import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 4,         // 4명의 가상 사용자
    iterations: 4,  // 각 VU당 1회 요청
};

const HOST = 'https://<your-api-host>/api';
const COOKIE = 'ACCESS=<your-jwt-access-token>;';

// 4개 파일 미리 불러오기
const files = [
    open('./test-files/1gb_1.bin', 'b'),
    open('./test-files/1gb_2.bin', 'b'),
    open('./test-files/1gb_3.bin', 'b'),
    open('./test-files/1gb_4.bin', 'b'),
];

export default function () {
    const vuIndex = __VU - 1; // VU는 1부터 시작
    const file = files[vuIndex];
    const fileName = `1gb_${vuIndex + 1}.bin`;

    const res = http.post(`${HOST}/api/files/stream`, file, {
        headers: {
            'Content-Type': 'application/octet-stream',
            'file-name': fileName,
            'Cookie': COOKIE,
        },
        timeout: '15m',
    });

    check(res, {
        'is status 200': (r) => r.status === 200,
    });

    console.log(`VU ${__VU} 응답 코드: ${res.status}`);
    sleep(1);
}
