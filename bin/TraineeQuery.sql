use FAMS
GO

--insert roles
INSERT INTO [role] VALUES('MANAGER'),('DELIVERY_MANAGER'),('CLASS_ADMIN'),
('REC'),('TRAINER'),('SYSTEM_ADMIN')
go
--all password : 123456
INSERT INTO [account] VALUES
('Hoang Minh Duong','$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.','duong123'),
('Phan Minh Tuan','$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.','tuan123'),
('Nguyen Tung Linh','$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.','linh123'),
('Dang Minh Hai','$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.','hai123'),
('Le Van Duc','$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.','duc123'),
('Tran Van Nam','$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.','nam123')
go
--insert role to account
INSERT INTO account_role VALUES(1,1),
(1,2),(1,3),(2,3),(3,5),(4,4),(5,1),(5,3),(6,1)
go

INSERT [dbo].[classes] ([class_name], [sill], [status], [location], [end_date], [start_date]) 
VALUES 
(N'HN18_FR_JAVA02', N'JAVA', N'planning', N'danang', NULL, NULL),
(N'HN19_FR_ANDROID04', N'ANDROID', N'planned', N'hanoi', NULL, NULL),
(N'HN19_FR_ANDROID05', N'ANDROID', N'planned', N'hanoi', NULL, NULL),
(N'HN18_FR_ANDROID01', N'ANDROID', N'planned', N'hanoi', NULL, NULL),
(N'HN18_FR_ANDROID02', N'ANDROID', N'planning', N'hanoi', NULL, NULL),
(N'HN18_FR_ANDROID03', N'ANDROID', N'inprogress', N'hcm', NULL, NULL),
(N'HN18_FR_ANDROID04', N'ANDROID', N'inprogress', N'hanoi', NULL, NULL),
(N'HN19_FR_JAVA08', N'JAVA', N'inprogress', N'danang', NULL, NULL),
(N'HN19_FR_JAVA09', N'JAVA', N'planned', N'hanoi', NULL, NULL),
(N'HN19_FR_PHP05', N'PHP', N'planning', N'hanoi', NULL, NULL),
(N'HN19_FR_PHP06', N'PHP', N'inprogress', N'hanoi', NULL, NULL),
(N'HN19_FR_ANDROID08', N'ANDROID', N'planning', N'hanoi', NULL, NULL),
(N'HN20_FR_JAVA01', N'JAVA', N'planning', N'hanoi', NULL, NULL),
(N'HN20_FR_JAVA02', N'JAVA', N'planning', N'hcm', NULL, NULL),
(N'HN20_FR_JAVA03', N'JAVA', N'planned', N'hanoi', NULL, NULL),
(N'HN20_FR_PHP03', N'PHP', N'planned', N'hanoi', NULL, NULL),
(N'HN20_FR_ANDROID01', N'ANDROID', N'planning', N'hanoi', NULL, NULL),
(N'HN20_FR_ANDROID02', N'ANDROID', N'planning', N'hanoi', NULL, NULL),
(N'HN20_FR_PHP01', N'PHP', N'inprogress', N'hanoi', NULL, NULL),
(N'HN20_FR_PHP02', N'PHP', N'inprogress', N'hcm', NULL, NULL),
(N'HN20_FR_FRONT-END01', N'FRONT-END', N'inprogress', N'danang', NULL, NULL),
(N'HN20_FR_FRONT-END02', N'FRONT-END', N'planning', N'hanoi', NULL, NULL),
(N'HN20_FR_FRONT-END03', N'FRONT-END', N'inprogress', N'hanoi', NULL, NULL),
(N'HN20_FR_FRONT-END04', N'FRONT-END', N'planned', N'hanoi', NULL, NULL),
(N'HN20_FR_FRONT-END05', N'FRONT-END', N'inprogress', N'danang', NULL, NULL),
(N'HN20_FR_FRONT-END06', N'FRONT-END', N'planning', N'danang', NULL, NULL),
(N'HN19_FR_C/C++01', N'C/C++', N'planned', N'hanoi', NULL, NULL),
(N'HN20_FR_C/C++01', N'C/C++', N'inprogress', N'danang', NULL, NULL),
(N'HN20_FR_C/C++02', N'C/C++', N'inprogress', N'hanoi', NULL, NULL),
(N'HN20_FR_C/C++03', N'C/C++', N'planning', N'hcm', NULL, NULL),
(N'HN20_FR_C/C++04', N'C/C++', N'planning', N'danang', NULL, NULL)
GO

INSERT INTO dbo.university(university_name) 
	VALUES 
		('FPT'),
		('HUST'), 
		('HUS'),
		('HAU'),
		('HANU')
GO

INSERT INTO dbo.faculty(faculty_name)
	VALUES 
		('CNTT'), 
		('DTVT'),
		('DDT'),
		('KHMT')
GO

update dbo.trainee set active = 1

INSERT INTO dbo.trainee(account, active, allocation_status, allowance_group, date_of_birth, email, faculty_id, gender, history, [name], phone, skill, [status], tpbaccount, traineeid, [type], salary_paid, university_id, class_id)
	VALUES 
	('LinhNT', 1, 'Not allocated', 'Dev-N', '1992-01-12', 'linhnt@gmail.com', 2, 1, 'changed at', 'Nguyen Tung Linh', '0913304304', 'JAVA', 'Waiting for Allocation', '113311', 'DEV001', 'Trainee', 1, 1, 2),
	('LinhNT2', 1, 'Not allocated', 'Dev-N', '1993-01-12', 'linhnt2@gmail.com', 2, 0, 'changed at 2', 'Nguyen Tung Li', '0923304304', 'C#', 'Waiting for Allocation', '113312', 'DEV002', 'Trainee', 1, 2, 2),
	('TuanPM', 1, 'Not allocated', 'Dev-N', '1996-06-22', 'tuanpm@gmail.com', 4, 0, 'changed at 3', 'Phan Minh Tuan', '0933304304', 'JAVA', 'Waiting for Allocation', '113313', 'DEV003', 'Trainee', 1, 3, 3),
	('AnhLQ', 1, 'Not allocated', 'Dev-N', '1998-12-25', 'anhlq@gmail.com', 1, 1, 'changed at 4', 'Le Quang Anh', '0313264773', 'PHP', 'Waiting for Allocation', '113314', 'DEV004', 'Trainee', 1, 4, 4),
	('MinhLV', 1, 'Not allocated', 'Dev-N', '1998-09-30', 'minhlv@gmail.com', 2, 1, 'changed at 5', 'Le Van Minh', '0771956113', 'JAVA', 'Waiting for Allocation', '113315', 'DEV005', 'Trainee', 1, 3, 5),
	('LinhLTM', 1, 'Not allocated', 'Dev-N', '1998-11-11', 'linhltm@gmail.com', 3, 1, 'changed at', 'Le Thi My Linh', '0174123453', 'JAVA', 'Allocated', '123456', 'DEV006', 'Trainee', 1, 4, 6),
	('ThanhDT', 1, 'Not allocated', 'Dev-N', '1998-08-01', 'thanhdt@gmail.com', 3, 0, 'changed at 2', 'Doan Trung Thanh', '0321123470', 'C#', 'Allocated', '654871', 'DEV007', 'Trainee', 1, 1, 7),
	('LapNT3', 1, 'Not allocated', 'Dev-N', '1994-01-12', 'lapnt3@gmail.com', 1, 0, 'changed at 3', 'Nguyen Thi Lap', '0933304304', 'PYTHON', 'Waiting for Allocation', '234777', 'DEV008', 'Trainee', 1, 1, 8),
	('TienNV', 1, 'Not allocated', 'Dev-N', '1992-08-22', 'tiennv@gmail.com', 1, 1, 'changed at 4', 'Nguyen Van Tien', '0117444000', 'PHP', 'Allocated', '333333', 'DEV009', 'Trainee', 1, 2, 9),
	('ThaiNV', 1, 'Not allocated', 'Dev-N', '1998-12-15', 'thainv@gmail.com', 3, 1, 'changed at 5', 'Nguyen Van Thai', '0953304304', 'JAVA', 'Waiting for Allocation', '224378', 'DEV010', 'Trainee', 1, 2, 10),
	('ThangLV', 1, 'Not allocated', 'Dev-N', '1998-03-03', 'thanglv@gmail.com', 4, 1, 'changed at', 'Le Van Thang', '0369252001', 'JAVA', 'Allocated', '978971', 'DEV011', 'Trainee', 1, 3, 11),
	('DuongHM', 1, 'Not allocated', 'Dev-N', '1998-08-24', 'duonghm@gmail.com', 4, 0, 'changed at 2', 'Hoang Minh Duong', '0923304304', 'C#', 'Allocated', '375841', 'DEV012', 'Trainee', 1, 1, 12),
	('ThanhCT', 1, 'Not allocated', 'Dev-N', '1998-04-15', 'thanhct@gmail.com', 4, 0, 'changed at 3', 'Chu Tuan Thanh', '0933304304', 'JAVA', 'Waiting for Allocation', '333411', 'DEV013', 'Trainee', 1, 1, 13),
	('PhuongLX', 1, 'Not allocated', 'Dev-N', '1994-05-24', 'phuongpx@gmail.com', 2, 1, 'changed at 4', 'Pham Xuan Phuong', '0984111010', 'PHP', 'Allocated', '224117', 'DEV014', 'Trainee', 1, 2, 14),
	('LongNV', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'longnv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Long', '0774320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV015', 'Trainee', 1, 3, 15),
	('LongNV1', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'longnv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Long', '0775320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV016', 'Trainee', 1, 3, 16),
	('NamTV', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'namtv@gmail.com', 1, 1, 'changed at 5', 'Tran Van Nam', '0797320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV017', 'Trainee', 1, 3, 4),
	('LienNT', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'liennt@gmail.com', 1, 1, 'changed at 5', 'Nguyen Thi Lien', '0777320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV018', 'Trainee', 1, 3, 18),
	('DuongNN', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'dungnn@gmail.com', 1, 1, 'changed at 5', 'Nguyen Ngoc Duong', '0777330320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV019', 'Trainee', 1, 3, 11),
	('LuanNV', 1, 'Not allocated', 'Dev-N', '1994-05-24', 'luannv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Luan', '0477320420', 'JAVA', 'Waiting for Allocation', '100147', 'DEV020', 'Trainee', 1, 3, 2),
	('DuanND', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'duannd@gmail.com', 1, 1, 'changed at 5', 'Nguyen Duc Duan', '0747320520', 'JAVA', 'Waiting for Allocation', '100147', 'DEV021', 'Trainee', 1, 3, 21),
	('HuanNV', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'huannv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Huan', '0737325320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV022', 'Trainee', 1, 3, 22),
	('DucNV', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'ducnv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Duc', '0732320355', 'JAVA', 'Waiting for Allocation', '100147', 'DEV023', 'Trainee', 1, 3, 23),
	('TuanNV', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'tuannv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Tuan', '0237320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV024', 'Trainee', 1, 3, 8),
	('TrinhNT', 1, 'Not allocated', 'Dev-N', '1998-08-24', 'trinhnt@gmail.com', 1, 1, 'changed at 5', 'Nguyen Thi Trinh', '0877320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV025', 'Trainee', 1, 3, 4),
	('LinhNT', 1, 'Not allocated', 'Dev-N', '1998-12-15', 'linhnt@gmail.com', 1, 1, 'changed at 5', 'Nguyen Thuy Linh', '0772320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV026', 'Trainee', 1, 3, 7),
	('TuyenNV', 1, 'Not allocated', 'Dev-N', '1992-08-22', 'tuyennv@gmail.com', 1, 1, 'changed at 5', 'Nguyen Van Tuyen', '0727327720', 'JAVA', 'Waiting for Allocation', '100147', 'DEV027', 'Trainee', 1, 3, 3),
	('DinhNT', 1, 'Not allocated', 'Dev-N', '1994-01-12', 'dinhnt@gmail.com', 1, 1, 'changed at 5', 'Nguyen Thi Dinh', '0727320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV028', 'Trainee', 1, 3, 3),
	('TuanNN', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'tuannn@gmail.com', 1, 1, 'changed at 5', 'Nguyen Ngoc Tuan', '0737320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV029', 'Trainee', 1, 3, 9),
	('HuyenNT', 1, 'Not allocated', 'Dev-N', '1994-01-12', 'huyennt@gmail.com', 1, 1, 'changed at 5', 'Nguyen Thanh Huyen', '0167320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV030', 'Trainee', 1, 3, 16),
	('CucNT', 1, 'Not allocated', 'Dev-N', '1996-01-12', 'cucnt@gmail.com', 1, 1, 'changed at 5', 'Nguyen Thi Cuc', '0377320320', 'JAVA', 'Waiting for Allocation', '100147', 'DEV031', 'Trainee', 1, 3, 8)
GO

INSERT [dbo].[candidate] ([status], [trainee_id], [location], [remarks]) 
VALUES 
( N'test-fail', 2, N'danang', NULL),
(N'new', 3, N'hcm', NULL),
(N'test-fail', 4, N'hanoi', NULL),
(N'transferred', 5, N'hanoi', NULL),
(N'test-pass', 6, N'danang', NULL),
(N'new', 7, N'hcm', NULL),
(N'interview-pass', 8, N'hcm', NULL),
(N'interview-fail', 9, N'hanoi', NULL),
(N'new', 10, N'danang', NULL),
(N'transferred', 11, N'hanoi', NULL),
(N'transferred', 12, N'hcm', NULL),
(N'new', 13, N'danang', NULL),
(N'transferred', 14, N'hanoi', NULL),
(N'interview-pass', 15, N'hcm', NULL),
(N'new', 16, N'hanoi', NULL),
(N'new', 17, N'hanoi', NULL),
(N'transferred', 18, N'danang', NULL),
(N'new', 19, N'hcm', NULL),
(N'test-pass', 20, N'hanoi', NULL),
(N'new', 21, N'hanoi', NULL),
(N'transferred', 22, N'hanoi', NULL),
(N'test-pass', 23, N'danang', NULL),
(N'transferred', 24, N'hanoi', NULL),
(N'test-fail', 25, N'hanoi', NULL),
(N'new', 26, N'danang', NULL),
(N'new', 27, N'hanoi', NULL),
(N'test-pass', 28, N'hanoi', NULL),
( N'test-fail', 29, N'hanoi', NULL)
GO
