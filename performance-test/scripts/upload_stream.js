import http from 'k6/http';
import { check, sleep } from 'k6';

// export let options = {
//     vus: 4,
//     iterations: 4,
// };
export let options = {
    vus: 1,          // 동시에 4명의 사용자
    iterations: 1,   // 각 사용자당 1회 실행
};

const HOST = 'https://api.dev.sumbisori.site';
const COOKIE = 'ACCESS=실제값;';

const files = [
    { name: '1gb_1.bin', data: open('../test-files/1gb_1.bin', 'b') },
    { name: '1gb_2.bin', data: open('../test-files/1gb_2.bin', 'b') },
    { name: '1gb_3.bin', data: open('../test-files/1gb_3.bin', 'b') },
    { name: '1gb_4.bin', data: open('../test-files/1gb_4.bin', 'b') },
];

export default function () {
    // const idx = __VU - 1;
    const idx = __ITER; // 현재 iteration에 따라 0~3
    const file = files[idx];
    const fileName = file.name;

    const contentLength = file.data instanceof ArrayBuffer
        ? file.data.byteLength
        : file.data.length;

    const res = http.post(`${HOST}/api/files/stream`, file.data, {
        headers: {
            'Cookie': COOKIE,
            'file-name': fileName,
            'Content-Type': 'application/octet-stream',
            'Content-Length': contentLength.toString(),
        },
        timeout: '30m',
    });

    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    console.log(`VU ${__VU} 업로드 완료. 응답 코드: ${res.status}`);
    sleep(1);
}
