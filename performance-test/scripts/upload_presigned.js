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
const PRESIGNED_API = `${HOST}/api/files/test/presigned-url`;

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

    // 1. Presigned URL 요청
    const presignedRes = http.post(PRESIGNED_API, JSON.stringify({
        fileName: file.name,
        contentType: 'application/octet-stream',
        fileSize: file.data.byteLength,
    }), {
        headers: {
            'Content-Type': 'application/json',
            'Cookie': COOKIE,
        },
        timeout: '2m',
    });

    check(presignedRes, {
        'presigned URL 응답 상태 200': (r) => r.status === 200,
    });

    const presignedUrl = presignedRes.body.trim();

    // 2. 실제 업로드 (PUT 요청)
    const putRes = http.put(presignedUrl, file.data, {
        headers: {
            'Content-Type': 'application/octet-stream',
        },
        timeout: '15m',
    });

    check(putRes, {
        'S3 업로드 상태 200': (res) => {
            const success = res.status === 200;
            if (!success) {
                console.log(`❌ 업로드 실패 (VU ${__VU}) 상태 코드: ${res.status}`);
                console.log(`응답 바디: ${res.body}`);
            }
            return success;
        }
    });

    console.log(`VU ${__VU} 완료: ${putRes.status}`);
    sleep(1);
}
