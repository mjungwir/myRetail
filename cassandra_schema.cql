DROP KEYSPACE IF EXISTS my_retail;
CREATE KEYSPACE my_retail WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND DURABLE_WRITES = true;

use my_retail;

DROP table IF EXISTS pricing_data;
create table pricing_data (
tcin VARCHAR,
currentPrice DECIMAL,
currencyCode VARCHAR
PRIMARY KEY((tcin)));

INSERT INTO pricing_data (tcin, currentPrice, currencyCode) VALUES ('13860428', 13.49, 'USD');
INSERT INTO pricing_data (tcin, currentPrice, currencyCode) VALUES ('54456119', 4.99, 'USD');
INSERT INTO pricing_data (tcin, currentPrice, currencyCode) VALUES ('13264003', 2.29, 'USD');

-- Gives a nice output to debug inserts
select * from pricing_data;