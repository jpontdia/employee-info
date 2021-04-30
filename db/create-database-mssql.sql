CREATE TABLE [employee] (
	id bigint NOT NULL identity(1,1),
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	birth_date date NOT NULL,
	hire_date date NOT NULL,
	phone varchar(100),
	gender varchar(1) NOT NULL DEFAULT 'N',
	status varchar(1) NOT NULL,
	version int,
  CONSTRAINT [PK_EMPLOYEE] PRIMARY KEY CLUSTERED
  (
  [id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)

CREATE TABLE [salary] (
	employee bigint NOT NULL,
	from_date date NOT NULL,
	to_date date,
	salary money NOT NULL,
    CONSTRAINT PK_salary PRIMARY KEY (employee,from_date)
)

CREATE TABLE [address] (
	employee bigint NOT NULL,
	from_date date NOT NULL,
	to_date date,
	address varchar(255) NOT NULL,
    CONSTRAINT [PK_ADDRESS] PRIMARY KEY (employee,from_date)
)


ALTER TABLE [salary] WITH CHECK ADD CONSTRAINT [salary_fk0] FOREIGN KEY ([employee]) REFERENCES [employee]([id])
ON UPDATE CASCADE

ALTER TABLE [salary] CHECK CONSTRAINT [salary_fk0]


ALTER TABLE [address] WITH CHECK ADD CONSTRAINT [address_fk0] FOREIGN KEY ([employee]) REFERENCES [employee]([id])
ON UPDATE CASCADE

ALTER TABLE [address] CHECK CONSTRAINT [address_fk0]

