USE CLINIQUE_VETERINAIRE;
GO


-- Insertion des personnes dans la table Personnels
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('BOSAPIN Edmond', 'password123', 0); --mots de passe
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('DE CAJOU Benoît', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('MALALANICH Mélanie', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('AIMONE Anne', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('TOURNE Sylvain', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('SWITAUME Guillaume', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('MONFILS Thibaut', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('ELABETE Isabelle', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('DE JEU Odette', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('ABONDIEU Elisabeth', 'password123', 0);
INSERT INTO Personnels (Nom, MotPasse, Archive) VALUES ('HISSON Marie Paule', 'password123', 0);
GO

-- Insertion des rôles dans la table PersonnelRoles
-- Pour M. BOSAPIN Edmond
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'BOSAPIN Edmond'), 'DIR');
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'BOSAPIN Edmond'), 'VET');

-- Pour M. DE CAJOU Benoît
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'DE CAJOU Benoît'), 'DIR');
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'DE CAJOU Benoît'), 'VET');

-- Pour Mlle MALALANICH Mélanie
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'MALALANICH Mélanie'), 'VET');

-- Pour Mme AIMONE Anne
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'AIMONE Anne'), 'VET');

-- Pour M. TOURNE Sylvain
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'TOURNE Sylvain'), 'VET');

-- Pour M. SWITAUME Guillaume
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'SWITAUME Guillaume'), 'AST');

-- Pour M. MONFILS Thibaut
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'MONFILS Thibaut'), 'AST');

-- Pour Mlle ELABETE Isabelle
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'ELABETE Isabelle'), 'AST');

-- Pour Mme DE JEU Odette
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'DE JEU Odette'), 'SEC');

-- Pour Mme ABONDIEU Elisabeth
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'ABONDIEU Elisabeth'), 'SEC');

-- Pour Mlle HISSON Marie Paule
INSERT INTO PersonnelRoles (CodePers, CodeRole) VALUES ((SELECT CodePers FROM Personnels WHERE Nom = 'HISSON Marie Paule'), 'SEC');
GO
