INSERT INTO RoleType(name) VALUES('applicant') 
INSERT INTO RoleType(name) VALUES('recruiter')

INSERT INTO Locale(id, lang_code) VALUES(1,'sv')
INSERT INTO Locale(id, lang_code) VALUES(2,'en')

INSERT INTO Job(id) VALUES(1)
INSERT INTO Job(id) VALUES(2)

INSERT INTO Job_Localized(id, jobname, job, locale) VALUES(1, 'Korvgrillning', 1, 1)
INSERT INTO Job_Localized(id, jobname, job, locale) VALUES(2, 'Sausage grilling', 1, 2)
INSERT INTO Job_Localized(id, jobname, job, locale) VALUES(3, 'Karusellreparatör', 2, 1)
INSERT INTO Job_Localized(id, jobname, job, locale) VALUES(4, 'Carousel operator', 2, 2)

INSERT INTO Status(id) VALUES(1)
INSERT INTO Status(id) VALUES(2)
INSERT INTO Status(id) VALUES(3)

INSERT INTO Status_Localized(id, statusname, locale, status) VALUES(1, 'Ej granskad', 1, 1)
INSERT INTO Status_Localized(id, statusname, locale, status) VALUES(2, 'Not reviewed', 2, 1)
INSERT INTO Status_Localized(id, statusname, locale, status) VALUES(3, 'Accepterad', 1, 2)
INSERT INTO Status_Localized(id, statusname, locale, status) VALUES(4, 'Hired', 2, 2)
INSERT INTO Status_Localized(id, statusname, locale, status) VALUES(5, 'Avslagen', 1, 3)
INSERT INTO Status_Localized(id, statusname, locale, status) VALUES(6, 'Fired', 2, 3)

INSERT INTO Competence(id) VALUES(1)
INSERT INTO Competence(id) VALUES(2)

INSERT INTO Competence_Localized(id, competencename, competence, locale) VALUES(1,'Korvgrillning',1,1)
INSERT INTO Competence_Localized(id, competencename, competence, locale) VALUES(2,'Sausage grilling',1,2)
INSERT INTO Competence_Localized(id, competencename, competence, locale) VALUES(3,'Karusellreparatör',2,1)
INSERT INTO Competence_Localized(id, competencename, competence, locale) VALUES(4,'Carousel Operator',2,2)

INSERT INTO Person(username, email, fname, password, ssn, surname) VALUES ('borg', 'greta@borg.kth.se', 'Greta', 'wl9nk23a', '19740212-1521', 'Borg')

INSERT INTO Person(username, email, fname, password, ssn, surname) VALUES ('strand', 'per@strand.kth.se', 'Per', 'wl9nk23a', '19671212-1211', 'Strand')

INSERT INTO UserRole(id, roletype, person) VALUES(1, 'applicant', 'strand')
INSERT INTO UserRole(id, roletype, person) VALUES(2, 'recruiter', 'borg')

INSERT INTO Application(id, date_made, job, person, status) VALUES (1, '2014-02-22 10:00', 1, 'strand', 1)

INSERT INTO Availability(id, from_date, to_date, application) VALUES (1, '2014-02-23', '2014-05-25', 1)
INSERT INTO Availability(id, from_date, to_date, application) VALUES (2, '2014-07-10', '2014-08-10', 1)

INSERT INTO Competence_Profile(id, years_of_experience, application, competence) VALUES (1, 3.5, 1, 1)
INSERT INTO Competence_Profile(id, years_of_experience, application, competence) VALUES (2, 2.0, 1, 2)