INSERT INTO venues (venue_name, location)
VALUES ('Gocheok Sky Dome', 'Seoul'),
       ('Kyocera Dome', 'Osaka'),
       ('The Forum', 'Los Angeles'),
       ('O2 Arena', 'London'),
       ('Rajamangala Stadium', 'Bangkok'),
       ('Mercedes-Benz Arena', 'Berlin'),
       ('Tokyo Dome', 'Tokyo'),
       ('Prudential Center', 'New Jersey'),
       ('Allianz Park', 'São Paulo'),
       ('SM Mall of Asia Arena', 'Manila');

INSERT INTO attendees (attendee_name, email)
VALUES ('Kim Min-ji', 'minji.k@kmail.com'),
       ('Park Seo-jun', 'seojun.p@kmail.com'),
       ('Sato Haruto', 'haruto.s@jmail.com'),
       ('Emily Chen', 'emily.c@fanmail.com'),
       ('Lee Felix', 'felix.l@kmail.com'),
       ('Sarah Jenkins', 'sarah.j@webmail.com'),
       ('Choi Yuna', 'yuna.c@kmail.com'),
       ('Miguel Santos', 'miguel.s@latam.com'),
       ('Chloe Dupont', 'chloe.d@frances.com'),
       ('Aria Grande', 'aria.g@fanmail.com');

INSERT INTO events (event_name, event_date, venue_id)
VALUES ('BTS: Yet To Come Tour', '2026-05-15', 1),
       ('BLACKPINK: Pink Venom World Tour', '2026-06-20', 3),
       ('Stray Kids: 5-STAR Live', '2026-07-10', 2),
       ('TWICE: Ready To Be Tour', '2026-08-05', 7),
       ('NewJeans: Bunnies Fan Meeting', '2026-08-22', 10),
       ('IVE: Show What I Have', '2026-09-12', 5),
       ('SEVENTEEN: Follow to Seoul', '2026-10-01', 4),
       ('LE SSERAFIM: Flame Rises', '2026-10-15', 8),
       ('TXT: Act: Sweet Mirage', '2026-12-05', 6),
       ('KCON 2026 Grand Finale', '2026-12-18', 9);

INSERT INTO event_attendee (event_id, attendee_id)
VALUES (1, 1),
       (1, 5),
       (1, 7),
       (2, 4),
       (2, 9),
       (3, 3),
       (3, 7),
       (4, 3),
       (4, 10),
       (5, 1);


SELECT a.attendee_name, e.event_name, v.venue_name, v.location
FROM event_attendee ea
         JOIN attendees a ON ea.attendee_id = a.attendee_id
         JOIN events e ON ea.event_id = e.event_id
         JOIN venues v ON e.venue_id = v.venue_id;