BEGIN TRANSACTION;

INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('Anytime Kitchen', 'Korean', '23 W 32nd St 3rd floor', 'New York', 'NY', '10001', '+16466697733', 
 'https://lh5.googleusercontent.com/p/AF1QipNpfbHoAhsY7urCfe-ephp9eSr1wItM9CVSW3NJ=w408-h544-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '05:00PM', '01:00AM' from restaurant where name = 'Anytime Kitchen';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '05:00PM', '01:00AM' from restaurant where name = 'Anytime Kitchen';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '05:00PM', '01:00AM' from restaurant where name = 'Anytime Kitchen';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '05:00PM', '01:00AM' from restaurant where name = 'Anytime Kitchen';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '05:00PM', '01:00AM' from restaurant where name = 'Anytime Kitchen';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '05:00PM', '03:30AM' from restaurant where name = 'Anytime Kitchen';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '05:00PM', '03:30AM' from restaurant where name = 'Anytime Kitchen';


INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('Oscar Wilde', 'American', '45 W 27th St', 'New York', 'NY', '10001', '+12122133066', 
 'https://lh5.googleusercontent.com/p/AF1QipOmKb5oVBu1jp3k7_iKWSM1v6p0HC_YoATyjhw0=w408-h306-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '11:00AM', '01:00AM' from restaurant where name = 'Oscar Wilde';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '04:00PM', '01:00AM' from restaurant where name = 'Oscar Wilde';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '04:00PM', '01:00AM' from restaurant where name = 'Oscar Wilde';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '04:00PM', '01:00AM' from restaurant where name = 'Oscar Wilde';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '04:00PM', '02:00AM' from restaurant where name = 'Oscar Wilde';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '04:00PM', '02:00AM' from restaurant where name = 'Oscar Wilde';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '11:00AM', '02:00AM' from restaurant where name = 'Oscar Wilde';

 
INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('Kahlo', 'Mexican', '525 W 29th St', 'New York', 'NY', '10001', '+12122560083',
 'https://lh5.googleusercontent.com/p/AF1QipNIY-KGqS3KI4_LtL45N0UhpzGJb8-Q995yKyHV=w426-h240-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '12:00PM', '10:00PM' from restaurant where name = 'Kahlo';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '12:00PM', '10:00PM' from restaurant where name = 'Kahlo';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '12:00PM', '10:00PM' from restaurant where name = 'Kahlo';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '12:00PM', '11:00PM' from restaurant where name = 'Kahlo';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '12:00PM', '11:00PM' from restaurant where name = 'Kahlo';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '12:00PM', '12:00AM' from restaurant where name = 'Kahlo';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '12:00PM', '12:00AM' from restaurant where name = 'Kahlo'; 
 
 
INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('biricchino', 'Italian', '260 W 29th St', 'New York', 'NY', '10001', '+12126956690',
 'https://lh5.googleusercontent.com/p/AF1QipM88pZ-Y3b2fdf7JzcJEZ-49dXMP6jKKQ_Nt6o6=w426-h240-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '12:00PM', '09:00PM' from restaurant where name = 'biricchino';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '12:00PM', '10:00PM' from restaurant where name = 'biricchino';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '12:00PM', '10:00PM' from restaurant where name = 'biricchino';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '12:00PM', '10:00PM' from restaurant where name = 'biricchino';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '12:00PM', '11:00PM' from restaurant where name = 'biricchino';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '05:00PM', '11:00PM' from restaurant where name = 'biricchino'; 


INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('The Butcher''s Daughter', 'Vegetarian', '581 Hudson St', 'New York', 'NY', '10014', '+19173882132',
 'https://lh5.googleusercontent.com/p/AF1QipPcI-c92UmJGGSR6m6xjBollRJvnAcufJGM-nqT=w408-h271-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '08:00AM', '09:00PM' from restaurant where name = 'The Butcher''s Daughter';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '08:00AM', '09:00PM' from restaurant where name = 'The Butcher''s Daughter';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '08:00AM', '09:00PM' from restaurant where name = 'The Butcher''s Daughter';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '08:00AM', '09:00PM' from restaurant where name = 'The Butcher''s Daughter';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '08:00AM', '09:00PM' from restaurant where name = 'The Butcher''s Daughter';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '08:00AM', '10:00PM' from restaurant where name = 'The Butcher''s Daughter';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '08:00AM', '10:00PM' from restaurant where name = 'The Butcher''s Daughter'; 


INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('CUCINA urbana', 'Italian', '505 Laurel St', 'San Diego', 'CA', '92101', '+16192392222',
 'https://lh5.googleusercontent.com/p/AF1QipOtl59Xvd0L-ACw--2RKsj8pcQ7HZIMUYCs2okp=w408-h272-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '04:00PM', '08:00PM' from restaurant where name = 'CUCINA urbana'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '04:00PM', '09:00PM' from restaurant where name = 'CUCINA urbana'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '04:00PM', '09:00PM' from restaurant where name = 'CUCINA urbana'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '04:00PM', '09:00PM' from restaurant where name = 'CUCINA urbana'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '04:00PM', '09:30PM' from restaurant where name = 'CUCINA urbana'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '04:00PM', '09:00PM' from restaurant where name = 'CUCINA urbana'; 


INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('Ironside Fish & Oyster', 'Seafood', '1654 India St', 'San Diego', 'CA', '92101', '+16192693033',
 'https://lh5.googleusercontent.com/p/AF1QipPNtqkMC7RHNXqg-kFlDH1TBnfnYLXhS-NQtyAH=w408-h266-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '11:00AM', '10:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '11:30AM', '10:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '11:30AM', '10:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '11:30AM', '10:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '11:30AM', '10:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '11:30AM', '11:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '11:00AM', '11:00PM' from restaurant where name = 'Ironside Fish & Oyster'; 

INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('Punch Bowl Social San Diego', 'American', '1485 E St', 'San Diego', 'CA', '92101', '+16194523352',
 'https://lh5.googleusercontent.com/p/AF1QipPAxQ1mXm9CtdMqx1UKlZhlufLVNjmY_V-LxTTR=w426-h240-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '10:00AM', '12:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '11:00AM', '12:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 3, '11:00AM', '12:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '11:00AM', '12:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '11:00AM', '12:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '11:00AM', '02:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '10:00AM', '02:00AM' from restaurant where name = 'Punch Bowl Social San Diego'; 


INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES 
('Kingfisher', 'Asian', '2469 Broadway', 'San Diego', 'CA', '92102', '+16194321014',
 'https://lh5.googleusercontent.com/p/AF1QipOGVE7T7d-UxywdzzV3fJnnAltLjrdo5SPMTrTe=w408-h272-k-no');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '05:00PM', '11:00PM' from restaurant where name = 'Kingfisher'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '05:00PM', '11:00PM' from restaurant where name = 'Kingfisher'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '05:00PM', '11:00PM' from restaurant where name = 'Kingfisher'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '05:00PM', '11:00PM' from restaurant where name = 'Kingfisher';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '05:00PM', '11:00PM' from restaurant where name = 'Kingfisher'; 
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '05:00PM', '11:00PM' from restaurant where name = 'Kingfisher';

INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES
('Lumpys Diner', 'American', '615 Railroad Ave', 'Pittsburg', 'CA', '94565', '+19254393911',
 'https://media-cdn.tripadvisor.com/media/photo-f/11/91/26/00/lumpy-s-diner.jpg');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '05:00PM', '11:00PM' from restaurant where name = 'Lumpys Diner';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '05:00PM', '11:00PM' from restaurant where name = 'Lumpys Diner';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '05:00PM', '11:00PM' from restaurant where name = 'Lumpys Diner';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '05:00PM', '11:00PM' from restaurant where name = 'Lumpys Diner';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '05:00PM', '11:00PM' from restaurant where name = 'Lumpys Diner';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '05:00PM', '11:00PM' from restaurant where name = 'Lumpys Diner';

INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES
('Smith Landing Seafood Grill', 'American', '1 Marina Plaza', 'Antioch', 'CA', '94509', '+19257754862',
 'https://media-cdn.tripadvisor.com/media/photo-s/21/fa/5f/5d/smith-s-landing-seafood.jpg');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '05:00PM', '11:00PM' from restaurant where name = 'Smith Landing Seafood Grill';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '05:00PM', '11:00PM' from restaurant where name = 'Smith Landing Seafood Grill';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '05:00PM', '11:00PM' from restaurant where name = 'Smith Landing Seafood Grill';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '05:00PM', '11:00PM' from restaurant where name = 'Smith Landing Seafood Grill';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '05:00PM', '11:00PM' from restaurant where name = 'Smith Landing Seafood Grill';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '05:00PM', '11:00PM' from restaurant where name = 'Smith Landing Seafood Grill';

INSERT INTO restaurant (name, type, street, city, state, zip, phone, img_url) VALUES
('Double Dragon', 'Chinese', '2621 Somersville Rd', 'Antioch', 'CA', '94509', '+19257531763',
 'https://media-cdn.tripadvisor.com/media/photo-s/0a/7b/4c/40/photo1jpg.jpg');
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 1, '05:00PM', '11:00PM' from restaurant where name = 'Double Dragon';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 2, '05:00PM', '11:00PM' from restaurant where name = 'Double Dragon';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 4, '05:00PM', '11:00PM' from restaurant where name = 'Double Dragon';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 5, '05:00PM', '11:00PM' from restaurant where name = 'Double Dragon';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 6, '05:00PM', '11:00PM' from restaurant where name = 'Double Dragon';
INSERT INTO hours_of_operation (restaurant_id, day_open, open_from, open_to)
SELECT restaurant_id, 7, '05:00PM', '11:00PM' from restaurant where name = 'Double Dragon';

COMMIT TRANSACTION;
