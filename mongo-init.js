db = db.getSiblingDB('pricing_db');
db.createUser({ user: 'my_retail_user', pwd: 'api1234', roles: [{ role: 'readWrite', db: 'pricing_db' }]});
db.createCollection('pricing_data');
db.pricing_data.insert({ tcin: '13860428', currentPrice: 13.49, currencyCode: 'USD'});
db.pricing_data.insert({ tcin: '54456119', currentPrice: 4.99, currencyCode: 'USD'});
db.pricing_data.insert({ tcin: '13264003', currentPrice: 2.29, currencyCode: 'USD'});
