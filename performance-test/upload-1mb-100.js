import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter } from 'k6/metrics';

export let multipartSuccess = new Counter('multipart_success');
export let streamSuccess    = new Counter('stream_success');

export let options = {
    vus: 100,
    iterations: 100,
    maxDuration: '2m',
};

const HOST = 'https://<your-api-host>/api';
const COOKIE = 'ACCESS=<your-jwt-access-token>;';

const tenMB = open('./test-files/10mb_1.bin', 'b');

export default function () {
    const mode = __ENV.MODE || 'multipart';  // 기본값 multipart

    if (mode === 'multipart') {
        const form = { file: http.file(tenMB, '10mb.bin') };
        const res = http.post(`${HOST}/api/files/multipart-file`, form, {
            headers: { 'Cookie': COOKIE },
            timeout: '2m',
        });
        if (check(res, { 'mp 200': (r) => r.status === 200 })) {
            multipartSuccess.add(1);
        }
    }

    if (mode === 'stream') {
        const res = http.post(`${HOST}/api/files/stream`, tenMB, {
            headers: {
                'Content-Type': 'application/octet-stream',
                'file-name': '1mb.bin',
                'Cookie': COOKIE,
            },
            timeout: '2m',
        });
        if (check(res, { 'st 200': (r) => r.status === 200 })) {
            streamSuccess.add(1);
        }
    }

    sleep(0.1);
}
