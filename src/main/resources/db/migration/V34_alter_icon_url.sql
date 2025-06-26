UPDATE icon
SET url = REPLACE(url, 'https://d35m5peyvblzpd.cloudfront.net', '')
WHERE url LIKE 'https://d35m5peyvblzpd.cloudfront.net/%';
