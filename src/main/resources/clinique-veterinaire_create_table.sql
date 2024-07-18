USE CLINIQUE_VETERINAIRE;
GO

-- Suppression des tables si elles existent déjà
DROP TABLE IF EXISTS PersonnelRoles;
DROP TABLE IF EXISTS Agendas;
DROP TABLE IF EXISTS Animaux;
DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS Personnels;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Races;
GO

-- Création de la table Races
CREATE TABLE Races (
                       Race NVARCHAR(20) NOT NULL,
                       Espece NVARCHAR(20) NOT NULL,
                       PRIMARY KEY (Race, Espece)
);
GO

-- Création de la table Clients
CREATE TABLE Clients (
                         CodeClient INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
                         Nom NVARCHAR(20) NOT NULL,
                         Adresse1 NVARCHAR(30) NOT NULL,
                         Adresse2 NVARCHAR(30) NULL,
                         Ville NVARCHAR(25) NOT NULL,
                         CodePostal CHAR(6) NOT NULL,
                         NumTel CHAR(15) NULL,
                         Assurance NVARCHAR(30) NOT NULL,
                         Email NVARCHAR(100) NOT NULL,
                         Remarque NVARCHAR(250) NOT NULL,
                         Archive BIT NOT NULL DEFAULT 0
);
GO

-- Création de la table Animaux
CREATE TABLE Animaux (
                         CodeAnimal INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
                         NomAnimal NVARCHAR(30) NOT NULL,
                         Sexe CHAR(1) NOT NULL,
                         Couleur NVARCHAR(20) NOT NULL,
                         Race NVARCHAR(20) NOT NULL,
                         Espece NVARCHAR(20) NOT NULL,
                         CodeClient INT NOT NULL,
                         Tatouage NVARCHAR(10) NOT NULL,
                         Antecedents NVARCHAR(250) NOT NULL,
                         Archive BIT NOT NULL DEFAULT 0
);
GO

-- Création de la table Roles
CREATE TABLE Roles (
                       CodeRole NVARCHAR(3) PRIMARY KEY,
                       Description NVARCHAR(50) NOT NULL
);
GO

-- Insertion des descriptions de rôles dans la table Roles
INSERT INTO Roles (CodeRole, Description) VALUES ('DIR', 'Directeur');
INSERT INTO Roles (CodeRole, Description) VALUES ('VET', 'Vétérinaire');
INSERT INTO Roles (CodeRole, Description) VALUES ('AST', 'Assistant');
INSERT INTO Roles (CodeRole, Description) VALUES ('SEC', 'Secrétaire');
GO

-- Création de la table Personnels
CREATE TABLE Personnels (
                            CodePers INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
                            Nom NVARCHAR(30) NOT NULL,
                            MotPasse NVARCHAR(68) NOT NULL,
                            Archive BIT NOT NULL DEFAULT 0
);
GO

-- Création de la table PersonnelRoles
CREATE TABLE PersonnelRoles (
                                CodePers INT NOT NULL,
                                CodeRole NVARCHAR(3) NOT NULL,
                                PRIMARY KEY (CodePers, CodeRole)
);
GO

-- Création de la table Agendas
CREATE TABLE Agendas (
                         CodeVeto INT NOT NULL,
                         DateRdv DATE NOT NULL,
                         CodeAnimal INT NOT NULL
);
GO

-- Ajout des clés étrangères pour la table Animaux
ALTER TABLE Animaux ADD
    CONSTRAINT FK_ANIMAUX_CLIENTS FOREIGN KEY (CodeClient) REFERENCES Clients(CodeClient),
    CONSTRAINT FK_ANIMAUX_RACES FOREIGN KEY (Race, Espece) REFERENCES Races(Race, Espece);
GO

-- Ajout des clés étrangères pour la table Agendas
ALTER TABLE Agendas ADD
    CONSTRAINT FK_AGENDAS_PERSONNELS FOREIGN KEY (CodeVeto) REFERENCES Personnels(CodePers),
    CONSTRAINT FK_AGENDAS_ANIMAUX FOREIGN KEY (CodeAnimal) REFERENCES Animaux(CodeAnimal);
GO

-- Ajout des clés étrangères pour la table PersonnelRoles
ALTER TABLE PersonnelRoles ADD
    CONSTRAINT FK_PERSOROLE_PERSONNELS FOREIGN KEY (CodePers) REFERENCES Personnels(CodePers),
    CONSTRAINT FK_PERSOROLE_ROLES FOREIGN KEY (CodeRole) REFERENCES Roles(CodeRole);
GO
