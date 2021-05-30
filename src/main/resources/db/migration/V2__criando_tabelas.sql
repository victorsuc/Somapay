
CREATE TABLE IF NOT EXISTS SOMAPAY.EMPRESA (
	ID SERIAL PRIMARY KEY,
	NOME VARCHAR(50) NOT NULL,
	CNPJ VARCHAR(14) NOT NULL
);

CREATE TABLE IF NOT EXISTS SOMAPAY.FUNCIONARIO (
	ID SERIAL PRIMARY KEY,
	CPF VARCHAR(11) NOT NULL,
	NOME VARCHAR(50) NOT NULL,
	SALARIO DECIMAL(12,2) NOT NULL,
	DATA_NASCIMENTO DATE NOT NULL,
	DATA_ADMISSAO DATE NOT NULL,
	CARGO VARCHAR(30) NOT NULL,
	EMPRESA_FK INT REFERENCES SOMAPAY.EMPRESA (ID) NOT NULL
);

CREATE TABLE IF NOT EXISTS SOMAPAY.CONTA_BANCARIA (
	ID SERIAL PRIMARY KEY,
	SALDO DECIMAL(12,2) NOT NULL DEFAULT 0,
	EMPRESA_FK INT REFERENCES SOMAPAY.EMPRESA (ID),
	FUNCIONARIO_FK INT REFERENCES SOMAPAY.FUNCIONARIO(ID)
);

CREATE TABLE IF NOT EXISTS SOMAPAY.MOVIMENTACAO_CONTA_BANCARIA(
	ID SERIAL PRIMARY KEY,
	OPERACAO VARCHAR(10) NOT NULL,
	VALOR DECIMAL(12,2) NOT NULL,
	DATA TIMESTAMP NOT NULL,
	CONTA_BANCARIA_FK INT REFERENCES SOMAPAY.CONTA_BANCARIA (ID) NOT NULL
);