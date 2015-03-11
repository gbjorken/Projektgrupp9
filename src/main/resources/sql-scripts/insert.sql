INSERT INTO RoleType(SELECT 'applicant' AS name FROM RoleType WHERE name = 'applicant' HAVING count(*)=0)
INSERT INTO RoleType(SELECT 'recruiter' AS name FROM RoleType WHERE name = 'recruiter' HAVING count(*)=0)

INSERT INTO Locale(SELECT 1 AS id, 'sv' AS lang_code FROM Locale WHERE id = 1 AND lang_code = 'sv' HAVING count(*)=0)
INSERT INTO Locale(SELECT 2 AS id, 'en' AS lang_code FROM Locale WHERE id = 2 AND lang_code = 'en' HAVING count(*)=0)

INSERT INTO Job(SELECT 1 AS id FROM Job WHERE id = 1 HAVING count(*)=0)
INSERT INTO Job(SELECT 2 AS id FROM Job WHERE id = 2 HAVING count(*)=0)

INSERT INTO Status(SELECT 1 AS id FROM Status WHERE id = 1 HAVING count(*)=0)
INSERT INTO Status(SELECT 2 AS id FROM Status WHERE id = 2 HAVING count(*)=0)
INSERT INTO Status(SELECT 3 AS id FROM Status WHERE id = 3 HAVING count(*)=0)

INSERT INTO Competence(SELECT 1 AS id FROM Competence WHERE id = 1 HAVING count(*)=0)
INSERT INTO Competence(SELECT 2 AS id FROM Competence WHERE id = 2 HAVING count(*)=0)

INSERT INTO Job_Localized(SELECT 1 AS id, 'Korvgrillning' AS jobname, 1 AS job, 1 AS locale FROM Job_Localized WHERE id = 1 AND 'Korvgrillning' = jobname AND 1 = job AND 1 = locale HAVING count(*)=0)
INSERT INTO Job_Localized(SELECT 2 AS id, 'Sausage grilling' AS jobname, 1 AS job, 2 AS locale FROM Job_Localized WHERE id = 2 AND 'Sausage grilling' = jobname AND 1 = job AND 2 = locale HAVING count(*)=0)
INSERT INTO Job_Localized(SELECT 3 AS id, 'Karusellreparatör' AS jobname, 2 AS job, 1 AS locale FROM Job_Localized WHERE id = 3 AND 'Karusellreparatör' = jobname AND 2 = job AND 1 = locale HAVING count(*)=0)
INSERT INTO Job_Localized(SELECT 4 AS id, 'Carousel operator' AS jobname, 2 AS job, 2 AS locale FROM Job_Localized WHERE id = 4 AND 'Carousel operator' = jobname AND 2 = job AND 2 = locale HAVING count(*)=0)

INSERT INTO Status_Localized(SELECT 1 AS id, 'Ej granskad' AS statusname, 1 AS locale, 1 AS status FROM Status_Localized WHERE id = 1 AND 'Ej granskad' = statusname AND 1 = locale AND 1 = status HAVING count(*)=0)
INSERT INTO Status_Localized(SELECT 2 AS id, 'Not reviewed' AS statusname, 2 AS locale, 1 AS status FROM Status_Localized WHERE id = 2 AND 'Not reviewed' = statusname AND 2 = locale AND 1 = status HAVING count(*)=0)
INSERT INTO Status_Localized(SELECT 3 AS id, 'Accepterad' AS statusname, 1 AS locale, 2 AS status FROM Status_Localized WHERE id = 3 AND 'Accepterad' = statusname AND 1 = locale AND 2 = status HAVING count(*)=0)
INSERT INTO Status_Localized(SELECT 4 AS id, 'Hired' AS statusname, 2 AS locale, 2 AS status FROM Status_Localized WHERE id = 4 AND 'Hired' = statusname AND 2 = locale AND 2 = status HAVING count(*)=0)
INSERT INTO Status_Localized(SELECT 5 AS id, 'Avslagen' AS statusname, 1 AS locale, 3 AS status FROM Status_Localized WHERE id = 5 AND 'Avslagen' = statusname AND 1 = locale AND 3 = status HAVING count(*)=0)
INSERT INTO Status_Localized(SELECT 6 AS id, 'Fired' AS statusname, 2 AS locale, 3 AS status FROM Status_Localized WHERE id = 6 AND 'Fired' = statusname AND 2 = locale AND 3 = status HAVING count(*)=0)

INSERT INTO Competence_Localized(SELECT 1 AS id, 'Korvgrillning' AS competencename, 1 AS competence, 1 AS locale FROM Competence_Localized WHERE id = 1 AND 'Korvgrillning' = competencename AND 1 = competence AND 1 = locale HAVING count(*)=0)
INSERT INTO Competence_Localized(SELECT 2 AS id, 'Sausage grilling' AS competencename, 1 AS competence, 2 AS locale FROM Competence_Localized WHERE id = 2 AND 'Sausage grilling' = competencename AND 1 = competence AND 2 = locale HAVING count(*)=0)
INSERT INTO Competence_Localized(SELECT 3 AS id, 'Karusellreparatör' AS competencename, 2 AS competence, 1 AS locale FROM Competence_Localized WHERE id = 3 AND 'Karusellreparatör' = competencename AND 2 = competence AND 1 = locale HAVING count(*)=0)
INSERT INTO Competence_Localized(SELECT 4 AS id, 'Carousel operator' AS competencename, 2 AS competence, 2 AS locale FROM Competence_Localized WHERE id = 4 AND 'Carousel operator' = competencename AND 2 = competence AND 2 = locale HAVING count(*)=0)

INSERT INTO Person(SELECT 'borg' AS username, 'greta@borg.kth.se' AS email, 'Greta' AS fname, '21c0cb53a963b6c31ecc8874b852d63dee062e44567fed5fb811f4e947f06bf2' AS password, '19740212-1521' AS ssn, 'Borg' AS surname FROM Person WHERE 'borg' = username AND 'greta@borg.kth.se' = email AND 'Greta' = fname AND '21c0cb53a963b6c31ecc8874b852d63dee062e44567fed5fb811f4e947f06bf2' = password AND '19740212-1521' = ssn AND 'Borg' = surname HAVING count(*)=0)
INSERT INTO Person(SELECT 'strand' AS username, 'per@strand.kth.se' AS email, 'Per' AS fname, 'f6303dd648fce7887051bef3a5f997d7b9fcc1f2d9d3c8a7f4e1bf4f54968c02' AS password, '19671212-1211' AS ssn, 'Strand' AS surname FROM Person WHERE 'strand' = username AND 'per@strand.kth.se' = email AND 'Per' = fname AND 'f6303dd648fce7887051bef3a5f997d7b9fcc1f2d9d3c8a7f4e1bf4f54968c02' = password AND '19671212-1211' = ssn AND 'Strand' = surname HAVING count(*)=0)

INSERT INTO UserRole(SELECT 1 AS id, 'applicant' AS roletype, 'strand' AS person FROM UserRole WHERE id = 1 AND 'applicant' = roletype AND 'strand' = person HAVING count(*)=0)
INSERT INTO UserRole(SELECT 2 AS id, 'recruiter' AS roletype, 'borg' AS person FROM UserRole WHERE id = 2 AND 'recruiter' = roletype AND 'borg' = person HAVING count(*)=0)

INSERT INTO Application(SELECT 1 AS id, '2014-02-22 10:00' AS date_made, 1 AS job, 'strand' AS person, 1 AS status FROM Application WHERE 1 = id AND '2014-02-22 10:00' = date_made AND 1 = job AND 'strand' = person AND 1 = status HAVING count(*)=0)

INSERT INTO Availability(SELECT 1 AS id, '2014-02-23' AS from_date, '2014-05-25' AS to_date, 1 AS application FROM Availability WHERE 1 = id AND '2014-02-23' = from_date AND '2014-05-25' = to_date AND 1 = application HAVING count(*)=0)
INSERT INTO Availability(SELECT 2 AS id, '2014-07-10' AS from_date, '2014-08-10' AS to_date, 1 AS application FROM Availability WHERE 2 = id AND '2014-07-10' = from_date AND '2014-08-10' = to_date AND 1 = application HAVING count(*)=0)

INSERT INTO Competence_Profile(SELECT 1 AS id, 3.5 AS years_of_experience, 1 AS application, 1 AS competence FROM Competence_Profile WHERE 1 = id AND 3.5 = years_of_experience AND 1 = application AND 1 = competence HAVING count(*)=0)
INSERT INTO Competence_Profile(SELECT 2 AS id, 2.0 AS years_of_experience, 1 AS application, 2 AS competence FROM Competence_Profile WHERE 2 = id AND 2.0 = years_of_experience AND 1 = application AND 2 = competence HAVING count(*)=0)