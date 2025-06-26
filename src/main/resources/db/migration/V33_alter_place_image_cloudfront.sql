UPDATE place
SET image_url = REPLACE(image_url, 'https://d35m5peyvblzpd.cloudfront.net', '')
WHERE image_url LIKE 'https://d35m5peyvblzpd.cloudfront.net/%';
