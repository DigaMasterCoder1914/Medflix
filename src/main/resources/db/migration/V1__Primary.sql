CREATE TABLE ADMIN(

    ID_ADMIN BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,

    Nome VARCHAR(50) UNIQUE,
    Role VARCHAR(50) ,
    Senha VARCHAR(500),
    Status BOOLEAN DEFAULT TRUE


);


CREATE TABLE USUARIO(

                      ID_USUARIO BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,

                      Email VARCHAR(50) UNIQUE NOT NULL,
                      Role VARCHAR(50) ,
                      Senha VARCHAR(500) NOT NULL,
                      Status BOOLEAN DEFAULT TRUE


);

CREATE TABLE USUARIO_MEDICO(

                        ID_USUARIO_MEDICO BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,

                        Crm VARCHAR(50) UNIQUE NOT NULL,
                        Role VARCHAR(50) NOT NULL,
                        Senha VARCHAR(500) NULL,
                        Nome VARCHAR(50) NULL UNIQUE,
                        Status BOOLEAN  NOT NULL ,
                        Chave_De_Acesso VARCHAR(500)

);
