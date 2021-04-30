INSERT INTO employee (first_name,last_name,birth_date,hire_date,gender,status,phone,"version") VALUES
	 ('Arjun','Mahoney','1996-09-01','1970-01-01','M','A',NULL,NULL),
	 ('Iman','Wills','1980-01-01','2020-01-01','M','A','a',NULL),
	 ('Arjun','Mahoney','1996-09-01','1970-01-01','M','A','333',24);

INSERT INTO address (employee,from_date,to_date,address) VALUES
	 (1,'2020-02-01','2020-04-30','405 Wisconsin Ave Lynn Haven, Florida(FL), 32444'),
	 (1,'2020-05-01',NULL,'6001 Farm To Market Rd, Cattaraugus, New York(NY), 14719'),
	 (2,'2020-02-01','2020-04-30','405 Wisconsin Ave Lynn Haven, Florida(FL), 32444'),
	 (2,'2020-05-01',NULL,'6001 Farm To Market Rd, Cattaraugus, New York(NY), 14719');

INSERT INTO salary (employee,from_date,to_date,salary) VALUES
	 (2,'2020-05-01',NULL,160000),
	 (2,'2020-01-01','2020-04-30',150000);