UPDATE place
SET image_url = REPLACE(image_url, 'https://d35m5peyvblzpd.cloudfront.net', '')
WHERE image_url LIKE 'https://d35m5peyvblzpd.cloudfront.net/%';

UPDATE icon
SET url = REPLACE(url, 'https://d35m5peyvblzpd.cloudfront.net', '')
WHERE url LIKE 'https://d35m5peyvblzpd.cloudfront.net/%';
